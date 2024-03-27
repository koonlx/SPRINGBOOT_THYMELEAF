package com.example.thymeleaf.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.thymeleaf.model.Member;
import com.example.thymeleaf.repository.MemberRepository;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberRepository memberDao;

    /**
     * <pre>
     * Method : GET
     * uri : /member/add
     * Description : addMember.html 렌더링
     * </pre>
     *
     * @return addMember.html 렌더링
     */
    @GetMapping("/add")
    public String addMemberForm() {
        return "addMember";
    }

    /**
     * <pre>
     * Method : POST
     * uri : /member/add
     * Description : 사용자가 값 입력 후 요청 시 Member Table에 저장 후 addMember.html 렌더링
     * </pre>
     * 
     * @param member 사용자가 입력한 값을 매개변수로 받음.
     * @return addMember.html 렌더링
     */
    @PostMapping("/add")
    public String addMember(Member member) {
        memberDao.save(member);
        return "addMember";
    }

    /**
     * <pre>
     * Method : ALL
     * uri : /member/list
     * Description : DB에서 Member Table에 있는 모든 목록 가져오고 listMember.html 렌더링
     * </pre>
     * 
     * @param model jsp에 변수를 전달하는 역할의 매개변수
     * @return listMembers.html 렌더링
     */
    @RequestMapping("/list")
    public String listMember(Model model) {
        List<Member> members = memberDao.findAll();
        model.addAttribute("members", members);
        return "listMembers";
    }

    /**
     * <pre>
     * Method : GET
     * uri : /member/edit/{id}
     * Description : 해당하는 id의 member table에 접근해서 값 수정 후 저장
     * </pre>
     * 
     * @param id    url 경로에 있는 id
     * @param model jsp에 변수를 전달하는 역할의 매개변수
     * @return 값이 존재할 때 editMember.html 렌더링, 값이 존재하지 않을 때 memberNotFound.html 렌더링
     */
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Integer id, Model model) {
        @SuppressWarnings("null")
        Optional<Member> member = memberDao.findById(id);
        if (member.isPresent()) {
            model.addAttribute("member", member.get());
            return "editMember";
        } else
            return "memberNotFound";
    }

    /**
     * <pre>
     * Method : POST
     * uri : /member/edit
     * Description : 사용자가 수정할 값을 입력하고 전송한 데이터를 member table에 저장
     * </pre>
     * 
     * @param member 사용자가 전송한 데이터를 매개변수로 받음
     * @return /member/list로 리다이렉트
     */
    @PostMapping("/edit")
    public String editMember(Member member) {
        memberDao.save(member);
        return "redirect:/member/list";
    }

    /**
     * <pre>
     * Method : GET
     * uri : /member/del/{id}
     * Description : id에 해당하는 member table 행 삭제
     * </pre>
     * 
     * @param id url 경로에 있는 id
     * @return /member/list로 리다이렉트
     */
    @GetMapping("/del/{id}")
    public String delete(@PathVariable int id) {
        memberDao.deleteById(id);
        return "redirect:/member/list";
    }
}
