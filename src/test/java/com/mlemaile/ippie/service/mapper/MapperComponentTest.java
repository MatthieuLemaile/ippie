package com.mlemaile.ippie.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.service.dto.ComponentDto;

public class MapperComponentTest {

    @Test
    public void testToDto () {
        Component component = DatabaseObject.component1;
        ComponentDto dto = MapperComponent.INSTANCE.toDto(component);
        compare(component, dto);
    }

    @Test
    public void testToListDto () {
        Component component1 = DatabaseObject.component1;
        Component component2 = DatabaseObject.component2;
        List<Component> list = Arrays.asList(component1, component2);
        List<ComponentDto> returnedList = MapperComponent.INSTANCE.toListDto(list);
        assertNotNull(returnedList);
        assertEquals("The returned list does not match the excpected Size", list.size(),
                returnedList.size());
        compare(component1, returnedList.get(0));
        compare(component2, returnedList.get(1));
    }

    private void compare ( Component component, ComponentDto dto ) {
        assertEquals("The name is not correctly mapped", component.getName(), dto.getName());
        assertEquals("The id is not correctly mapped", component.getId(), dto.getId());
        compareDate(component.getIntroduced(), dto.getIntroduced());
        compareDate(component.getDiscontinued(), dto.getDiscontinued());
        assertEquals("The details are not correctly mapped", component.getDetails(),
                dto.getDetails());
        assertEquals("The State's details are not correctly mapped", component.getStateDetails(),
                dto.getStateDetails());
        assertEquals("The state name is not correctly mapped", component.getState().getName(),
                dto.getState());
        assertEquals("The state id is not correctly mapped", component.getState().getId(),
                dto.getStateId());
        assertEquals("The type name is not correctly mapped",
                component.getModel().getType().getName(), dto.getType());
        assertEquals("The model name is not correctly mapped", component.getModel().getName(),
                dto.getModel());
        assertEquals("The model id is not correctly mapped", component.getModel().getId(),
                dto.getModelId());
    }

    private void compareDate(LocalDate date,String dtoDate){
        if (date == null) {
            assertNull("The introduced date is suppose to be null", dtoDate);
        }else{
            assertEquals("The returned date does not match the expected one", date.toString(),
                    dtoDate);
        }
    }

}
