package com.mlemaile.ippie.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mlemaile.ippie.service.ServiceComponent;

@Controller
public class ComponentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentController.class);

    @Autowired
    private ServiceComponent serviceComponent;

    @GetMapping({ "componentDashboard", "/" })
    public ModelAndView DisplayComponent () {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DEBUGGING");
        }
        if (LOGGER.isWarnEnabled()) {
            LOGGER.warn("WARNING");
        }
        ModelAndView model = new ModelAndView();
        model.setViewName("componentDashboard");
        model.addObject("components", serviceComponent.findAll());
        return model;
    }
}
