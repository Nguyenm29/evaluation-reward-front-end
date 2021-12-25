package com.vis.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;

public class AbstractController<Service> {

    @Autowired
    protected Service service;

}
