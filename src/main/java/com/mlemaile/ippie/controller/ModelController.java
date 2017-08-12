package com.mlemaile.ippie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mlemaile.ippie.service.ServiceModel;

@Controller
public class ModelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelController.class);

    @Autowired
    private ServiceModel serviceModel;

    @GetMapping("/modelDashboard")
    public ModelAndView displayModel () {
        ModelAndView model = new ModelAndView();
        model.setViewName("modelDashboard");
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Retrieving and displaying all models");
        }
        model.addObject("models", serviceModel.findAll());
        return model;
    }
}
