package com.vis.frontend.controller;

import com.vis.frontend.model.request.UserRequest;
import com.vis.frontend.model.response.UserResponse;
import com.vis.frontend.service.LoginService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController extends AbstractController<LoginService> {
    public final String ADMIN = "1";

    public final String USER = "0";


    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpServletRequest httpServletRequest) {

        UserRequest userRequest = UserRequest.builder().username(username).password(password).build();
        UserResponse response = service.login(httpServletRequest, userRequest);
        if (ObjectUtils.isEmpty(response)) {
            return "login";
        }
        httpServletRequest.getSession().setAttribute("user", response);
        if (response.getRole().equals(ADMIN)) {
            return "redirect:/admin/point";
        } else if (response.getRole().equals(USER)){
            return "redirect:/point";
        }
        return response.getView();
    }
}
