package com.vis.frontend.service;

import com.vis.frontend.model.response.EvaluationResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EvaluationService {
    List<EvaluationResponse> getListEvaluation(HttpServletRequest httpServletRequest, String employeeId);
}
