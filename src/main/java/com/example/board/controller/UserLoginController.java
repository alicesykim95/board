package com.example.board.controller;

import com.example.board.service.UserService;
import com.example.board.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
public class UserLoginController {

    private final UserService userService;

    // 로그인 페이지
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request) {

        HttpSession session = request.getSession();

        Object loginYn = session.getAttribute("login");

        if (loginYn != null) {
            return "board/bHome";
        }

        return "board/bLogin";
    }

    // 로그인 처리
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public int login(UserVo uv, HttpServletRequest request, Model model) throws Exception {

        HttpSession session = request.getSession();
        UserVo login = userService.loginMember(uv);

        if (login != null) {
            session.setAttribute("login", login);
            session.setAttribute("userId", login.getUserId());
            session.setMaxInactiveInterval(60 * 30);
            model.addAttribute("login", login);
            return 1;
        } else {
            return 0;
        }
    }

    // 로그아웃 페이지
    @RequestMapping(value = "/userLogout", method = RequestMethod.GET)
    public String LogoutPage(HttpServletRequest request) {

        try {
            HttpSession session = request.getSession();
            session.invalidate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "board/bLogout";
    }


}

