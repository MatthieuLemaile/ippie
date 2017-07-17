package com.mlemaile.ippie.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.service.dto.ModelDto;

public class MapperModelTest {

    @Test
    public void testToDto () {
        Model model = DatabaseObject.model1;
        ModelDto returned = MapperModel.INSTANCE.toDto(model);
        assertNotNull(returned);
        compare(model, returned);
    }

    @Test
    public void testToListDto () {
        Model model1 = DatabaseObject.model1;
        Model model2 = DatabaseObject.model2;
        List<Model> list = Arrays.asList(model1, model2);
        List<ModelDto> returnedList = MapperModel.INSTANCE.toListDto(list);
        assertNotNull(returnedList);
        assertEquals("The returned List is not the expected size",list.size(),returnedList.size());
        compare(model1,returnedList.get(0));
        compare(model2,returnedList.get(1));
    }

    private void compare ( Model m, ModelDto dto ) {
        assertEquals("The name is not correctly mapped", m.getName(), dto.getName());
        assertEquals("The id is not correctly mapped", m.getId(), dto.getId());
        assertEquals("The type's name is not correcty mapped", m.getType().getName(),
                dto.getType());
        assertEquals("The type's id is not correctly mapped", m.getType().getId(), dto.getTypeId());
    }

}
