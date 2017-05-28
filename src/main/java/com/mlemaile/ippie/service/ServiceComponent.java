package com.mlemaile.ippie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.persistence.ComponentDao;
import com.mlemaile.ippie.service.dto.ComponentDto;
import com.mlemaile.ippie.service.mapper.MapperComponent;

@Service
public class ServiceComponent {

    @Autowired
    private ComponentDao componentDao;

    public List<ComponentDto> findAll () {
        return MapperComponent.INSTANCE.toListDto(componentDao.findAll());
    }
}
