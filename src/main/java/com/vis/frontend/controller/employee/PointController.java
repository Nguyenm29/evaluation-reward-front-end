package com.vis.frontend.controller.employee;

import com.vis.frontend.controller.AbstractController;
import com.vis.frontend.model.response.UserResponse;
import com.vis.frontend.service.PointService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("pointControllerWeb")
public class PointController extends AbstractController<PointService> {

    @GetMapping("/point")
    public String getHomePage(HttpServletRequest httpServletRequest,  HttpSession httpSession, Model model,
                              @RequestParam(value = "isExchangeSuccess", required = false) String isExchangeSuccess,
                              @RequestParam(value = "cost", required = false) String cost) {
        UserResponse userResponse = (UserResponse) httpSession.getAttribute("user");
        float point = service.getPointReward(httpServletRequest, userResponse.getEmployeeId());
        model.addAttribute("rewardPoint", point);
        if (StringUtils.isNoneEmpty(isExchangeSuccess)) {
            model.addAttribute("isExchangeSuccess", Boolean.valueOf(isExchangeSuccess));
            model.addAttribute("cost", cost);
        }
        return "employee/home";
    }

    @GetMapping("/point/view")
    public String viewPoint() {
        return "employee/viewpoint";
    }

    @GetMapping("/point/exchange")
    public String exchange(HttpServletRequest httpServletRequest,
                           @RequestParam(value = "cost", required = false) String cost,
                           @RequestParam(value = "serviceId", required = false) String serviceId,
                           RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("isExchangeSuccess", service.exchangePoint(httpServletRequest, cost, serviceId));
        redirectAttributes.addAttribute("cost", cost);
        return "redirect:/point";
    }

}