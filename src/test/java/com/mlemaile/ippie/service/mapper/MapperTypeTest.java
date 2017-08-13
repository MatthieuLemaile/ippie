package com.mlemaile.ippie.service.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.service.dto.TypeDto;

public class MapperTypeTest {

    @Test
    public void testToDto () {
        Type type1 = DatabaseObject.type1;
        TypeDto returnedType = MapperType.INSTANCE.toDto(type1);
        assertNotNull(returnedType);
        compare(type1, returnedType);
    }

    @Test(expected = MapperException.class)
    public void toDtoShouldThrowMapperExceptionWithNullArgument () {
        MapperType.INSTANCE.toDto(null);
    }

    @Test
    public void testToListDto () {
        Type type1 = DatabaseObject.type1;
        Type type2 = DatabaseObject.type2;
        List<Type> list = Arrays.asList(type1, type2);
        List<TypeDto> returnedList = MapperType.INSTANCE.toListDto(list);
        assertNotNull(returnedList);
        assertEquals("The returned list is not the expected size", list.size(),
                returnedList.size());
        compare(type1, returnedList.get(0));
        compare(type2, returnedList.get(1));
    }

    @Test
    public void testToModel () {
        TypeDto dto = new TypeDto();
        dto.setName("name");
        dto.setId(5);
        Type returned = MapperType.INSTANCE.toModel(dto);
        assertNotNull(returned);
        compare(returned, dto);
    }

    @Test(expected = MapperException.class)
    public void toModelShouldThrowExceptionWhenNullArgument () {
        MapperType.INSTANCE.toModel(null);
    }

    private void compare ( Type type, TypeDto dto ) {
        assertEquals("the name is not correctly mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                type.getName(), dto.getName());
        assertEquals("The id is not correctly mapped [EXPECTED AND ACTUAL MIGHT BE REVERSED]",
                type.getId(), dto.getId());
    }

}
