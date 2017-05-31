package com.mlemaile.ippie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.persistence.ModelDao;
import com.mlemaile.ippie.service.dto.ModelDto;
import com.mlemaile.ippie.service.mapper.MapperModel;

@Service
public class ServiceModel {

    @Autowired
    private ModelDao modelDao;

    public List<ModelDto> findAll () {
        return MapperModel.INSTANCE.toListDto(modelDao.findAll());
    }
}
