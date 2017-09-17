package com.mlemaile.ippie.controller;

import java.util.ArrayList;
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

import com.mlemaile.ippie.service.ServiceType;
import com.mlemaile.ippie.service.TypeValidator;
import com.mlemaile.ippie.service.dto.TypeDto;

@Controller
public class TypeController {
    private static final Logger LOGGER        = LoggerFactory.getLogger(TypeController.class);
    private static final String PARAM_TYPE_ID = "type";

    @Autowired
    private TypeValidator typeValidator;
    @Autowired
    private ServiceType   serviceType;

    @InitBinder
    protected void initBinder ( final WebDataBinder binder ) {
        // Trim String so string with blank space will be treated as empty
        // string
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(typeValidator);
    }

    /**
     * Method retrieving and displaying all Type
     * 
     * @return A ModelAndView for the typeDashboard, with all component.
     */
    @GetMapping("/typeDashboard")
    public ModelAndView displayAllType () {
        ModelAndView model = new ModelAndView();
        model.setViewName("typeDashboard");
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Retrieving and displaying all type");
        }
        List<TypeDto> types = serviceType.findAll();
        model.addObject("types", types);
        return model;
    }

    @GetMapping("/addType")
    public ModelAndView displayAddView ( @ModelAttribute("TypeDto") TypeDto typeDto ) {
        ModelAndView model = new ModelAndView();
        model.setViewName("typeAdd");
        return model;
    }

    /**
     * Method used to save the given type
     * 
     * @param typeDto
     *            The type to save
     * @param result
     *            contain errors if validation went wrong
     * @return either the dashboard of the form for adding a type.
     */
    @PostMapping("addType")
    public ModelAndView addType ( @Valid @ModelAttribute("TypeDto") TypeDto typeDto,
            BindingResult result ) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            model.setViewName("typeAdd");
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("Error receiving type : " + typeDto);
            }
            model.addObject("errors", result.getAllErrors());
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Saving type : " + typeDto);
            }
            serviceType.save(typeDto);
            model.setViewName("redirect:/typeDashboard");
        }
        return model;

    }

    @GetMapping("editType")
    public ModelAndView displayEditType ( @ModelAttribute("TypeDto") TypeDto typeDto,
            @RequestParam(value = PARAM_TYPE_ID, required = false) String typeIdStr ) {
        ModelAndView model = new ModelAndView();
        Map<String, String> errors = new HashMap<>();
        long typeId = 0;
        try {
            if (typeIdStr != null) {
                typeId = Long.parseLong(typeIdStr);
            }
            TypeDto dto = serviceType.findOne(typeId);
            model.addObject("typeDto", dto);
            model.setViewName("typeEdit");
        } catch (IllegalArgumentException e) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Error while displaying edit type view : " + e.getMessage());
            }
            errors.put("Error", e.getMessage());
            model.setViewName("typeDashboard");
            model.addObject("errors", errors);
            List<TypeDto> types = serviceType.findAll();
            model.addObject("types", types);
        }
        return model;
    }

    @PostMapping("editType")
    public ModelAndView editType ( @Valid @ModelAttribute("typeDto") TypeDto typeDto,
            BindingResult result ) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            model.setViewName("typeEdit");
            model.addObject("errors", result.getAllErrors());
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("error while editing type " + typeDto + " errors : "
                        + result.getAllErrors());
            }
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Editing type : " + typeDto);
            }
            serviceType.save(typeDto);
            model.setViewName("redirect:/typeDashboard");
        }
        return model;
    }

    @GetMapping("deleteType")
    public ModelAndView deleteType (
            @RequestParam(value = PARAM_TYPE_ID, required = false) String typeIdStr ) {
        ModelAndView model = new ModelAndView();
        long typeId = 0L;
        try {
            if (typeIdStr != null) {
                typeId = Long.parseLong(typeIdStr);
            }
            serviceType.delete(typeId);
            model.setViewName("redirect:/typeDashboard");
        } catch (IllegalArgumentException e) {
            List<String> errors = new ArrayList<>();
            LOGGER.info("error while trying to delete type number {}.", typeIdStr);
            LOGGER.debug("Exeption is ", e);
            errors.add(e.getMessage());
            model.addObject("listStringErrors", errors);
            model.addObject("types", serviceType.findAll());
            model.setViewName("typeDashboard");
        }
        return model;
    }
}
