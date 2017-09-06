package com.mlemaile.ippie.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mlemaile.ippie.service.ComponentValidator;
import com.mlemaile.ippie.service.ServiceComponent;
import com.mlemaile.ippie.service.ServiceModel;
import com.mlemaile.ippie.service.ServiceState;
import com.mlemaile.ippie.service.dto.ComponentDto;
import com.mlemaile.ippie.service.dto.ModelDto;
import com.mlemaile.ippie.service.dto.StateDto;

@Controller
public class ComponentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentController.class);

    @Autowired
    private ServiceComponent serviceComponent;
    @Autowired
    private ServiceModel     serviceModel;
    @Autowired
    private ServiceState       serviceState;
    @Autowired
    private ComponentValidator componentValidator;

    @InitBinder
    protected void initBinder ( final WebDataBinder binder ) {
        // Trim String so string with blank space will be treated as empty
        // string
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(componentValidator);
    }

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
    
    @GetMapping("addComponent")
    public ModelAndView displayAddComponent (
            @ModelAttribute("ComponentDto") ComponentDto componentDto ) {
        ModelAndView model = new ModelAndView();
        List<ModelDto> models = serviceModel.findAll();
        model.addObject("models", models);
        List<StateDto> states = serviceState.findAll();
        model.addObject("states", states);
        model.setViewName("componentAdd");
        return model;
    }

    @PostMapping("addComponent")
    public ModelAndView addComponent (
            @Valid @ModelAttribute("ComponentDto") ComponentDto componentDto,
            BindingResult result ) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Error receiving component: " + componentDto);
            }
            componentDto.setStateDetails(componentDto.getStateDetails() + "ö");
            model.setViewName("componentAdd");
            model.addObject("errors", result.getAllErrors());
            List<ModelDto> models = serviceModel.findAll();
            List<StateDto> states = serviceState.findAll();
            model.addObject("states", states);
            model.addObject("models", models);
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Saving component : " + componentDto);
            }
            serviceComponent.save(componentDto);
            model.addObject("components", serviceComponent.findAll());
            model.setViewName("redirect:/componentDashboard");
        }
        return model;
    }
}
