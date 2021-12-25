package com.vis.frontend.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractService {
    @Autowired
    protected HttpServletRequest httpRequest;

    @Autowired
    protected ApiExchangeService apiExchangeService;
}
