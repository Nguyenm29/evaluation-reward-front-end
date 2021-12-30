package com.vis.frontend.controller.employee;

import com.vis.frontend.controller.AbstractController;
import com.vis.frontend.model.response.EvaluationResponse;
import com.vis.frontend.service.EvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/evaluation")
public class EvaluationController extends AbstractController<EvaluationService> {

    @GetMapping("")
    public String viewEvaluation(Model model,
                                 HttpServletRequest httpServletRequest) {

        List<EvaluationResponse> listEvaluation = service.getListEvaluation(httpServletRequest);
        model.addAttribute("listEvaluation", listEvaluation);
        model.addAttribute("total", service.totalPoint(listEvaluation));
        return "employee/viewpoint";
    }
}
