package com.mlemaile.ippie.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

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
import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.persistence.ComponentDao;
import com.mlemaile.ippie.persistence.hql.PersistenceContext;
import com.mlemaile.ippie.service.dto.ComponentDto;
import com.mlemaile.ippie.service.mapper.MapperComponent;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContext.class)
public class ServiceComponentTest {

    @Mock
    ComponentDao componentDao;

    @Autowired
    @InjectMocks
    ServiceComponent serviceComponent;

    @Before
    public void setup () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll () {
        Component compo1 = DatabaseObject.component1;
        Component compo2 = DatabaseObject.component2;
        List<Component> list = Arrays.asList(compo1, compo2);
        Mockito.when(componentDao.findAll()).thenReturn(list);
        List<ComponentDto> returnedList = serviceComponent.findAll();
        assertEquals("The returned list does not match the expected one",
                MapperComponent.INSTANCE.toListDto(list), returnedList);
    }

}
