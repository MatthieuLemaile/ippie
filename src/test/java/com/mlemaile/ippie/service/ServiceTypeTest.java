package com.mlemaile.ippie.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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

}
