package com.example.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {

    /**
     * <pre>
     * Method : ALL
     * uri : /
     * Description : 처음 웹 브라우저 켰을 때 (localhost:8080/) 으로 접근하는게 일반적이기 때문에 바로 (localhost:8080/member/list)로 이동시키는 함수
     * </pre>
     * 
     * @return - /member/list로 리다이렉트
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/member/list";
    }
}
