package com.mlemaile.ippie.service.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.persistence.ModelDao;
import com.mlemaile.ippie.service.dto.ComponentDto;

@Service
public class MapperComponent {
    @Autowired
    private ModelDao modelDao;

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

    public Component toModel ( ComponentDto dto ) {
        if (dto == null) {
            throw new MapperException("The given dto is null");
        }
        Component c = new Component(dto.getName());
        c.setDetails(dto.getDetails());
        c.setIntroduced(LocalDate.parse(dto.getIntroduced()));
        if (dto.getDiscontinued() != null) {
            c.setDiscontinued(LocalDate.parse(dto.getDiscontinued()));
        }
        c.setId(dto.getId());
        c.setStateDetails(dto.getStateDetails());
        State state = new State(dto.getState());
        state.setId(dto.getStateId());
        c.setState(state);
        Optional<Model> modelOpt = modelDao.findOne(dto.getModelId());
        modelOpt.ifPresent(model -> c.setModel(model));
        return c;
    }
}
