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

    @Test(expected = MapperException.class)
    public void toDtoShouldThrowAMapperExceptionWhenNullValue () {
        MapperModel.INSTANCE.toDto(null);
    }

    @Test(expected = MapperException.class)
    public void toModelShouldThrowMapperExceptionWithNullValue () {
        MapperModel.INSTANCE.toModel(null);
    }

    @Test
    public void testToModel () {
        ModelDto dto = new ModelDto();
        dto.setName("Name Model");
        dto.setId(5);
        dto.setType("Type name");
        dto.setTypeId(1);
        Model returned = MapperModel.INSTANCE.toModel(dto);
        assertNotNull(returned);
        compare(returned, dto);
    }

    private void compare ( Model m, ModelDto dto ) {
        assertEquals("The name is not correctly mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                m.getName(), dto.getName());
        assertEquals("The id is not correctly mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                m.getId(), dto.getId());
        assertEquals(
                "The type's name is not correcty mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                m.getType().getName(),
                dto.getType());
        assertEquals(
                "The type's id is not correctly mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                m.getType().getId(), dto.getTypeId());
    }

}
