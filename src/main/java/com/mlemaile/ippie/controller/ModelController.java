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

import com.mlemaile.ippie.service.ModelValidator;
import com.mlemaile.ippie.service.ServiceModel;
import com.mlemaile.ippie.service.ServiceType;
import com.mlemaile.ippie.service.dto.ModelDto;
import com.mlemaile.ippie.service.dto.TypeDto;

@Controller
public class ModelController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModelController.class);
    private static final String PARAM_MODEL_ID = "modelId";

    @Autowired
    private ServiceModel serviceModel;
    @Autowired
    private ServiceType  serviceType;
    @Autowired
    private ModelValidator modelValidator;

    @InitBinder
    protected void initBinder ( final WebDataBinder binder ) {
        // Trim String so string with blank space will be treated as empty
        // string
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(modelValidator);
    }

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

    @GetMapping("/addModel")
    public ModelAndView displayAddView ( @ModelAttribute("ModelDto") ModelDto modelDto ) {
        ModelAndView model = new ModelAndView();
        List<TypeDto> types = serviceType.findAll();
        model.addObject("types", types);
        model.setViewName("modelAdd");
        return model;
    }

    @PostMapping("/addModel")
    public ModelAndView addModel ( @Valid @ModelAttribute("ModelDto") ModelDto modelDto,
            BindingResult result ) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("modelAdd");
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Error receiving model : " + modelDto);
            }
            List<TypeDto> types = serviceType.findAll();
            modelAndView.addObject("types", types);
            modelAndView.addObject("errors", result.getAllErrors());
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Saving model : " + modelDto);
            }
            serviceModel.save(modelDto);
            modelAndView.setViewName("redirect:/modelDashboard");
        }
        return modelAndView;
    }

    @GetMapping("editModel")
    public ModelAndView displayEditModel ( @ModelAttribute("ModelDto") ModelDto ModelDto,
            @RequestParam(value = PARAM_MODEL_ID, required = false) String modelIdStr ) {
        ModelAndView modelAndView = new ModelAndView();
        Map<String, String> errors = new HashMap<>();
        long modelId = 0;
        try {
            if (modelIdStr != null) {
                modelId = Long.parseLong(modelIdStr);
            }
            ModelDto dto = serviceModel.findOne(modelId);
            modelAndView.addObject("ModelDto", dto);
            modelAndView.setViewName("modelEdit");
            List<TypeDto> types = serviceType.findAll();
            modelAndView.addObject("types", types);
        } catch (IllegalArgumentException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Error while displaying edit model view : " + e.getMessage());
            }
            errors.put("Error", e.getMessage());
            modelAndView.setViewName("modelDashboard");
            modelAndView.addObject("errors", errors);
            List<ModelDto> models = serviceModel.findAll();
            modelAndView.addObject("models", models);
        }
        return modelAndView;
    }

    @PostMapping("editModel")
    public ModelAndView editModel ( @Valid @ModelAttribute("ModelDto") ModelDto modelDto,
            BindingResult result ) {
        ModelAndView modelAndView = new ModelAndView();
        if (result.hasErrors()) {
            modelAndView.setViewName("modelEdit");
            modelAndView.addObject("errors", result.getAllErrors());
            List<TypeDto> types = serviceType.findAll();
            modelAndView.addObject("types", types);
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("error while editing model " + modelDto + " errors : "
                        + result.getAllErrors());
            }
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Editing type : " + modelDto);
            }
            serviceModel.save(modelDto);
            modelAndView.setViewName("redirect:/modelDashboard");
        }
        return modelAndView;
    }
}
