package com.mlemaile.ippie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.persistence.TypeDao;

@Service
public class ServiceType {

    @Autowired
    private TypeDao typeDao;

    public List<Type> findAll () {
        return typeDao.findAll();
    }
}
