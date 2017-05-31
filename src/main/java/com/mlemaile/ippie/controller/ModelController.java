package com.mlemaile.ippie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mlemaile.ippie.service.ServiceModel;

@Controller
public class ModelController {

    @Autowired
    private ServiceModel serviceModel;

    @GetMapping("/modelDashboard")
    public ModelAndView displayModel () {
        ModelAndView model = new ModelAndView();
        model.setViewName("modelDashboard");
        model.addObject("models", serviceModel.findAll());
        return model;
    }
}
