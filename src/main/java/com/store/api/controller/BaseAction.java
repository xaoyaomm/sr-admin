package com.store.api.controller;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;


public class BaseAction {
    protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
    
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    protected HttpSession session;
    
    protected DecimalFormat nfmt = new DecimalFormat("#.#");
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = this.request.getSession();
    }


}
