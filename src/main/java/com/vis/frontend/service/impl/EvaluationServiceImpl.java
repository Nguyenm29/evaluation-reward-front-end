package com.vis.frontend.service.impl;

import com.vis.frontend.model.response.EvaluationResponse;
import com.vis.frontend.model.response.RewardPointResponse;
import com.vis.frontend.service.ApiExchangeService;
import com.vis.frontend.service.EvaluationService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    private ApiExchangeService apiExchangeService;

    @Value("${back.api.url}")
    private String backApi;

    @Override
    public List<EvaluationResponse> getListEvaluation(HttpServletRequest httpServletRequest, String employeeId) {
        String url = apiExchangeService.createURL(backApi + "/evaluation?employeeId="+employeeId);
        ResponseEntity<?> response = apiExchangeService.getForEntity(httpServletRequest, url, null, List.class);
        return (List<EvaluationResponse>) response.getBody();
    }
}
