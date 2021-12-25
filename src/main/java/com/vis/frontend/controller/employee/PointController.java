package com.vis.frontend.controller.employee;

import com.vis.frontend.controller.AbstractController;
import com.vis.frontend.model.response.UserResponse;
import com.vis.frontend.service.PointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("pointControllerWeb")
public class PointController extends AbstractController<PointService> {

    @GetMapping("/point")
    public String getHomePage(HttpServletRequest httpServletRequest,  HttpSession httpSession, Model model) {
        UserResponse userResponse = (UserResponse) httpSession.getAttribute("user");
        float point = service.getPointReward(httpServletRequest, userResponse.getEmployeeId());
        model.addAttribute("rewardPoint", point);
        return "employee/home";
    }

    @GetMapping("/point/view")
    public String viewPoint() {
        return "employee/viewpoint";
    }
}