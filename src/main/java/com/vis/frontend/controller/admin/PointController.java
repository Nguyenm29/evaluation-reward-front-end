package com.vis.frontend.controller.admin;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.vis.frontend.controller.AbstractController;
import com.vis.frontend.model.response.RewardPointResponse;
import com.vis.frontend.service.PointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
@RequestMapping("/admin/point")
public class PointController extends AbstractController<PointService> {

    @PostMapping("/cal")
    public String calculatorPoint(HttpServletRequest httpRequest,
            HttpSession session,
            @RequestParam("file") MultipartFile file, Model model,
                                  RedirectAttributes redirectAttributes) throws IOException {

        List<RewardPointResponse> listCalEmployee = service.calPoint(httpRequest, file);
        model.addAttribute("listCalEmployee", listCalEmployee);
        session.setAttribute("listCalEmployee", listCalEmployee);
        return "admin/management-point";
    }

    @GetMapping("")
    public String getCalPage() {
        return "admin/management-point";
    }

    @GetMapping("/save")
    public String save(HttpServletRequest request, Model model) {
        boolean isSaveSuccess= service.save(request);
        model.addAttribute("isSaveSuccess", isSaveSuccess);
        return "admin/management-point";
    }

}
