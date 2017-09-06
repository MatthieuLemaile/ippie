package com.mlemaile.ippie.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.persistence.StateDao;
import com.mlemaile.ippie.service.dto.StateDto;
import com.mlemaile.ippie.service.mapper.MapperState;

public class ServiceStateTest {

    @Mock
    StateDao stateDao;

    @InjectMocks
    @Autowired
    ServiceState serviceState;

    @Before
    public void setup () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllShouldWorkCorrectly () {
        State s1 = DatabaseObject.state1;
        State s2 = DatabaseObject.state2;
        List<State> states = Arrays.asList(s1, s2);
        Mockito.when(stateDao.findAll()).thenReturn(states);
        List<StateDto> stateDtos = serviceState.findAll();
        assertEquals("The find all method does not work properly",
                MapperState.INSTANCE.toListDto(states), stateDtos);
    }

    @Test
    public void findAllShouldReturnEmptyListAccordingToDao () {
        List<State> states = new ArrayList<>();
        Mockito.when(stateDao.findAll()).thenReturn(states);
        List<StateDto> stateDtos = serviceState.findAll();
        assertEquals("find All does not work properly, list is suppose to be empty", 0,
                stateDtos.size());
    }

    @Test
    public void findOneShouldBeOkWhenDaoOk () {
        State s1 = DatabaseObject.state1;
        Mockito.when(stateDao.findOne(5L)).thenReturn(Optional.of(s1));
        StateDto dto = serviceState.findOne(5L);
        assertEquals("find one should retrieve the correct object from dao",
                MapperState.INSTANCE.toDto(s1), dto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOneShouldThrowIAEWhenDaoNok () {
        Mockito.when(stateDao.findOne(2L)).thenReturn(Optional.empty());
        serviceState.findOne(2L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOneShouldThrowIAEWhenIDLessThan0 () {
        serviceState.findOne(-5L);
    }
}
