package com.vis.frontend.service.impl;

import com.vis.frontend.model.request.UserRequest;
import com.vis.frontend.model.response.UserResponse;
import com.vis.frontend.service.ApiExchangeService;
import com.vis.frontend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    ApiExchangeService apiExchangeService;

    @Value("${back.api.url}")
    private String backApi;

    @Override
    public UserResponse login(HttpServletRequest httpServletRequest, UserRequest request) {
        String url = apiExchangeService.createURL(backApi + "/login");
        ResponseEntity<UserResponse> response = apiExchangeService.post(httpServletRequest, url, request, UserResponse.class, null);
        return response.getBody();
    }
}
