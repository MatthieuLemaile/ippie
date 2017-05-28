package com.mlemaile.ippie.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.service.dto.ComponentDto;

public enum MapperComponent {
    INSTANCE;

    /**
     * This method map a single component to the coreesponding dto
     * @param c the component to map
     * @return the corresponding dto
     */
    public ComponentDto toDto ( Component c ) {
        ComponentDto dto = new ComponentDto();
        dto.setId(c.getId());
        dto.setName(c.getName());
        if (c.getIntroduced() != null) {
            dto.setIntroduced(c.getIntroduced().toString());
        }
        if (c.getDiscontinued() != null) {
            dto.setDiscontinued(c.getDiscontinued().toString());
        }
        dto.setDetails(c.getDetails());
        dto.setStateDetails(c.getStateDetails());
        dto.setState(c.getState().getName());
        dto.setStateId(c.getState().getId());
        dto.setType(c.getModel().getType().getName());
        dto.setModelId(c.getModel().getId());
        dto.setModel(c.getModel().getName());
        return dto;
    }

    public List<ComponentDto> toListDto ( List<Component> components ) {
        List<ComponentDto> dtos = new ArrayList<>();
        components.forEach(c -> {
            dtos.add(toDto(c));
        });
        return dtos;
    }
}
