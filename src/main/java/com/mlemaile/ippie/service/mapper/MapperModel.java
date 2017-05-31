package com.mlemaile.ippie.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.service.dto.ModelDto;

public enum MapperModel {
    INSTANCE;

    /**
     * This method map a Model to the corresponding dto.
     * @param m The model to map
     * @return The corresponding dto.
     */
    public ModelDto toDto ( Model m ) {
        ModelDto dto = new ModelDto();
        dto.setId(m.getId());
        dto.setName(m.getName());
        dto.setTypeId(m.getType().getId());
        dto.setType(m.getType().getName());
        return dto;
    }

    /**
     * This metho map a list of Model to a list of dtos.
     * @param models the List of Model to map
     * @return the mapped List
     */
    public List<ModelDto> toListDto ( List<Model> models ) {
        List<ModelDto> dtos = new ArrayList<>();
        models.forEach(m -> {
            dtos.add(toDto(m));
        });
        return dtos;
    }
}
