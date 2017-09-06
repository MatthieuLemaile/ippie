package com.mlemaile.ippie.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.persistence.StateDao;
import com.mlemaile.ippie.service.dto.StateDto;
import com.mlemaile.ippie.service.mapper.MapperState;

@Service
public class ServiceState {

    @Autowired
    StateDao stateDao;

    /**
     * This method retrieve all State in the database
     * @return
     */
    public List<StateDto> findAll () {
        return MapperState.INSTANCE.toListDto(stateDao.findAll());
    }

    /**
     * This method retrieve the state corresponding to the given id.
     * @param id the id of the state to retrieve.
     * @return the corresponding state.
     */
    public StateDto findOne ( long id ) {
        if (id < 0) {
            throw new IllegalArgumentException("The given id must be positive");
        }
        State s = stateDao.findOne(id).orElseThrow(
                () -> new IllegalArgumentException("The given state does not exists"));
        return MapperState.INSTANCE.toDto(s);
    }

}
