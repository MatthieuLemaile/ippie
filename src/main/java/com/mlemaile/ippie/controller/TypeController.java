package com.mlemaile.ippie.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.service.ServiceType;

@Controller
public class TypeController {

    @Autowired
    ServiceType serviceType;

    @GetMapping({ "/typeDashboard", "/" })
    public ModelAndView displayAllType () {
        ModelAndView model = new ModelAndView();
        model.setViewName("typeView");
        List<Type> types = serviceType.findAll();
        model.addObject("types", types);
        return model;
    }
}
