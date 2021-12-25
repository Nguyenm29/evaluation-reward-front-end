package com.vis.frontend.controller.employee;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController {

    @GetMapping("")
    public String viewEvaluation(Model model,
                                 HttpServletRequest httpServletRequest) {

        return "employee/viewpoint";
    }
}
