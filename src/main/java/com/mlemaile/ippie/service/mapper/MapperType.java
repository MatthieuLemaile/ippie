package com.mlemaile.ippie.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.service.dto.TypeDto;

public enum MapperType {
    INSTANCE;

    /**
     * This method map a single Type to his dto.
     * @param t the Type to map.
     * @return the corresponding dto
     */
    public TypeDto toDto ( Type t ) {
        TypeDto dto = new TypeDto();
        dto.setId(t.getId());
        dto.setName(t.getName());
        return dto;
    }

    /**
     * This method map a List of Type to a List of TypeDtos.
     * @param types
     *            the List of Type to map
     * @return The List of mapped dtos.
     */
    public List<TypeDto> toListDto ( List<Type> types ) {
        List<TypeDto> dtos = new ArrayList<>();
        types.forEach(type -> {
            dtos.add(toDto(type));
        });
        return dtos;
    }

    /**
     * This method map a TypeDto to the corresponding Type.
     * @param dto
     *            the dto to map
     * @return The corresponding Type
     */
    public Type toModel ( TypeDto dto ) {
        Type type = new Type(dto.getName());
        type.setId(dto.getId());
        return type;
    }
}
