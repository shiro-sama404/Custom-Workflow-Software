package com.WorkFlowManager.project.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("httpServletRequest")
    public HttpServletRequest addHttpServletRequest(HttpServletRequest request) {
        return request;
    }
}