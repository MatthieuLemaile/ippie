package com.mlemaile.ippie.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.service.dto.StateDto;

public class MapperStateTest {

    @Test
    public void testToDto () {
        State s1 = DatabaseObject.state1;
        StateDto returned = MapperState.INSTANCE.toDto(s1);
        assertNotNull("The method is not suppose to return a null object", returned);
        compare(s1, returned);
    }

    @Test(expected = MapperException.class)
    public void toDtoShouldThrowAnexceptionWithNullObject () {
        MapperState.INSTANCE.toDto(null);
    }

    @Test
    public void testToListDto () {
        State s1 = DatabaseObject.state1;
        State s2 = DatabaseObject.state2;
        List<State> states = Arrays.asList(s1, s2);
        List<StateDto> returned = MapperState.INSTANCE.toListDto(states);
        assertNotNull(returned);
        assertEquals("The returned list is not the expected size", states.size(), returned.size());
        compare(s1, returned.get(0));
        compare(s2, returned.get(1));
    }

    private void compare ( State state, StateDto dto ) {
        assertEquals("the id is not correctly mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                state.getId(), dto.getId());
        assertEquals("the name is not correctly mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                state.getName(),
                dto.getName());
    }

}
