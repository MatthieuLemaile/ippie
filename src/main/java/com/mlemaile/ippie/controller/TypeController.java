package com.mlemaile.ippie.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.mlemaile.ippie.service.ServiceType;
import com.mlemaile.ippie.service.TypeValidator;
import com.mlemaile.ippie.service.dto.TypeDto;

@Controller
public class TypeController {
    @Autowired
    private TypeValidator typeValidator;

    @Autowired
    private ServiceType serviceType;

    @InitBinder
    protected void initBinder ( final WebDataBinder binder ) {
        // Trim String so string with blank space will be treated as empty
        // string
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.addValidators(typeValidator);
    }

    @GetMapping("/typeDashboard")
    public ModelAndView displayAllType () {
        ModelAndView model = new ModelAndView();
        model.setViewName("typeDashboard");
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

    @PostMapping("addType")
    public ModelAndView addType ( @Valid @ModelAttribute("TypeDto") TypeDto typeDto,
            BindingResult result ) {
        ModelAndView model = new ModelAndView();
        if (result.hasErrors()) {
            model.setViewName("typeAdd");
            model.addObject("errors", result.getAllErrors());
        } else {
            serviceType.save(typeDto);
            model.setViewName("redirect:/typeDashboard");
        }
        return model;

    }
}
