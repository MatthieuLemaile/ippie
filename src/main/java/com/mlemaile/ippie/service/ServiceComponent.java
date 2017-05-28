package com.mlemaile.ippie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.persistence.ComponentDao;

@Service
public class ServiceComponent {

    @Autowired
    private ComponentDao componentDao;

    public List<Component> findAll () {
        return componentDao.findAll();
    }
}
