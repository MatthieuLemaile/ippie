package com.mlemaile.ippie.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.mlemaile.ippie.service.ServiceComponent;
import com.mlemaile.ippie.service.ServiceModel;
import com.mlemaile.ippie.service.dto.ComponentDto;
import com.mlemaile.ippie.service.dto.ModelDto;

@Controller
public class ComponentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentController.class);

    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private ServiceModel     serviceModel;

    @GetMapping({ "componentDashboard", "/" })
    public ModelAndView DisplayComponent () {
        ModelAndView model = new ModelAndView();
        model.setViewName("componentDashboard");
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Retrieving and displaying all Component");
        }
        model.addObject("components", serviceComponent.findAll());
        return model;
    }
    
    @GetMapping("/addComponent")
    public ModelAndView displayAddComponent (
            @ModelAttribute("ComponentDto") ComponentDto componentDto ) {
        ModelAndView model = new ModelAndView();
        List<ModelDto> models = serviceModel.findAll();
        model.addObject("models", models);
        model.setViewName("componentAdd");
        return model;
    }
}
