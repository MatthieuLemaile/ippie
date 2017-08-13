package com.mlemaile.ippie.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.service.dto.ModelDto;
import com.mlemaile.ippie.service.dto.TypeDto;

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
     * This method map a list of Model to a list of dtos.
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

    /**
     * This method map a dto to the corresponding model object.
     * @param dto the dto to map
     * @return the model object
     */
    public Model toModel ( ModelDto dto ) {
        if (dto == null) {
            throw new MapperException("The given dto is null");
        }
        Model m = new Model();
        TypeDto typeDto = new TypeDto();
        typeDto.setId(dto.getTypeId());
        typeDto.setName(dto.getType());
        Type type = MapperType.INSTANCE.toModel(typeDto);
        m.setType(type);
        m.setName(dto.getName());
        m.setId(dto.getId());
        return m;
    }
}
