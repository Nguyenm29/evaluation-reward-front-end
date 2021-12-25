package com.vis.frontend.service;

import com.vis.frontend.model.request.UserRequest;
import com.vis.frontend.model.response.UserResponse;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {

    public UserResponse login(HttpServletRequest httpServletRequest, UserRequest request);
}
