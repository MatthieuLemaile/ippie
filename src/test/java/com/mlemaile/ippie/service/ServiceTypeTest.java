package com.mlemaile.ippie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.persistence.TypeDao;
import com.mlemaile.ippie.persistence.hql.PersistenceContext;
import com.mlemaile.ippie.service.dto.TypeDto;
import com.mlemaile.ippie.service.mapper.MapperType;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContext.class)
public class ServiceTypeTest {

    @Mock
    TypeDao typeDao;

    @Autowired
    @InjectMocks
    ServiceType serviceType;

    @Before
    public void setup () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll () {
        Type t1 = new Type("t1");
        Type t2 = new Type("t2");
        List<Type> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);
        Mockito.when(typeDao.findAll()).thenReturn(list);
        List<TypeDto> returnedList = serviceType.findAll();
        assertEquals("The returned list does not match the given one.",
                MapperType.INSTANCE.toListDto(list), returnedList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowAnIAEWhenDtoIsNull () {
        serviceType.save(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowAnIAEWhenDtoNameIsNull () {
        serviceType.save(new TypeDto());
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowAnIAEWhenDtoNameIsEmpty () {
        TypeDto type = new TypeDto();
        type.setName("");
        serviceType.save(type);
    }

    @Test
    public void saveShouldReturnTrueWhenDaoOk(){
        Type type = DatabaseObject.type1;
        Optional<Type> optType = Optional.of(type);
        Mockito.when(typeDao.save(type)).thenReturn(optType);
        boolean response = serviceType.save(MapperType.INSTANCE.toDto(type));
        assertTrue("The service response is not the expected one.", response);
    }

    @Test
    public void saveShouldReturnFalseWhenDaoNotOk () {
        Type type = DatabaseObject.type1;
        Optional<Type> optType = Optional.empty();
        Mockito.when(typeDao.save(type)).thenReturn(optType);
        boolean response = serviceType.save(MapperType.INSTANCE.toDto(type));
        assertFalse("The service response is not the expected one.", response);
    }

    @Test
    public void findOneShouldOkIfDaoOk () {
        String name = "Test";
        Optional<Type> typeOpt = Optional.of(new Type(name));
        Mockito.when(typeDao.findOne(5L)).thenReturn(typeOpt);
        TypeDto type = serviceType.findOne(5L);
        assertEquals("The find one method is not working as intented", name, type.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOneShouldThrowIAEWhenDaoNok () {
        Mockito.when(typeDao.findOne(5L)).thenReturn(Optional.empty());
        serviceType.findOne(5L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShouldThrowIAEWhenIdLessThanZero () {
        serviceType.findOne(-2L);
    }

}
