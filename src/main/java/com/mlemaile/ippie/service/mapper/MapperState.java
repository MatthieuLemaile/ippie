package com.mlemaile.ippie.service.mapper;

import java.util.ArrayList;
import java.util.List;

import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.service.dto.StateDto;

public enum MapperState {
    INSTANCE;

    /**
     * This method map a State to the corresponding dto
     * @param state the state to map
     * @return the dto mapped
     */
    public StateDto toDto ( State state ) {
        if(state == null){
            throw new MapperException("The given state is null");
        }
        StateDto dto = new StateDto();
        dto.setId(state.getId());
        dto.setName(state.getName());
        return dto;
    }

    /**
     * This method map a List of State to a List of corresponding dtos.
     * @param listState the List to map
     * @return the Mapped list
     */
    public List<StateDto> toListDto ( List<State> listState ) {
        List<StateDto> dtos = new ArrayList<>();
        listState.forEach(s -> {
            dtos.add(toDto(s));
        });
        return dtos;
    }
}
