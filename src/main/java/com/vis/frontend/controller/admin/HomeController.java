package com.vis.frontend.controller.admin;

import com.vis.frontend.service.ApiExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller(value = "adminHome")
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    private ApiExchangeService apiExchangeService;

    @GetMapping("/home")
    public String getHome() {
        return "admin/home";
    }

}
