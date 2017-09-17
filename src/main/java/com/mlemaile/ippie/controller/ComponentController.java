package com.mlemaile.ippie.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
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
    private static final String PARAM_COMPONENT_ID = "ComponentId";

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
            model.addObject("states", serviceState.findAll());
            model.addObject("models", serviceModel.findAll());
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

    @GetMapping("editComponent")
    public ModelAndView displayEditComponent (
            @ModelAttribute("ComponentDto") ComponentDto componentDto,
            @RequestParam(value = PARAM_COMPONENT_ID, required = false) String componentIdStr ) {
        ModelAndView model = new ModelAndView();
        Map<String, String> errors = new HashMap<>();
        long componentId = 0L;
        try {
            if (componentIdStr != null) {
                componentId = Long.parseLong(componentIdStr);
            }
            ComponentDto dto = serviceComponent.findOne(componentId);
            model.addObject("ComponentDto", dto);
            model.setViewName("componentEdit");
            model.addObject("models", serviceModel.findAll());
            model.addObject("states", serviceState.findAll());
        } catch (IllegalArgumentException e) {
            LOGGER.info("Error while displaying the edit component view : {}", e.getMessage());
            errors.put("errors", e.getMessage());
            model.setViewName("componentDashboard");
            model.addObject("components", serviceComponent.findAll());
        }
        return model;
    }

    @PostMapping("editComponent")
    public ModelAndView editComponent (
            @Valid @ModelAttribute("ComponentDto") ComponentDto componentDto,
            BindingResult result ) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            model.setViewName("componentEdit");
            model.addObject("errors", result.getAllErrors());
            model.addObject("models", serviceModel.findAll());
            model.addObject("states", serviceState.findAll());
            LOGGER.warn("Error while editing component. Error : {}. Component : {}",
                    result.getAllErrors(), componentDto);
        } else {
            LOGGER.info("Editing component {}", componentDto);
            serviceComponent.save(componentDto);
            model.setViewName("redirect:/componentDashboard");
        }
        return model;
    }

    @GetMapping("deleteComponent")
    public ModelAndView deleteComponent (
            @RequestParam(value = PARAM_COMPONENT_ID, required = false) String componentIdStr ) {
        ModelAndView model = new ModelAndView();
        long componentId = 0L;
        try {
            if (componentIdStr != null) {
                componentId = Long.parseLong(componentIdStr);
            }
            serviceComponent.deleteComponent(componentId);
            model.setViewName("redirect:/componentDashboard");
        } catch (IllegalArgumentException e) {
            LOGGER.info("Error while trying to delete component number {}", componentIdStr);
            LOGGER.debug("Exception is {}", e);
            model.setViewName("componentDashboard");
            model.addObject("components", serviceComponent.findAll());
        }
        return model;
    }
}
