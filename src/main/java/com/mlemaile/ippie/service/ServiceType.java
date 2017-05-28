package com.mlemaile.ippie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.persistence.TypeDao;
import com.mlemaile.ippie.service.dto.TypeDto;
import com.mlemaile.ippie.service.mapper.MapperType;

@Service
public class ServiceType {

    @Autowired
    private TypeDao typeDao;

    public List<TypeDto> findAll () {
        return MapperType.INSTANCE.toListDto(typeDao.findAll());
    }
}
