package com.mlemaile.ippie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mlemaile.ippie.service.ServiceComponent;

@Controller
public class ComponentController {

    @Autowired
    private ServiceComponent serviceComponent;

    @GetMapping({ "componentDashboard", "/" })
    public ModelAndView DisplayComponent () {
        ModelAndView model = new ModelAndView();
        model.setViewName("componentDashboard");
        model.addObject("components", serviceComponent.findAll());
        return model;
    }
}
