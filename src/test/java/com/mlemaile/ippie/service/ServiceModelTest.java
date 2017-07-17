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

import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.persistence.ModelDao;
import com.mlemaile.ippie.persistence.hql.DatabaseObject;
import com.mlemaile.ippie.persistence.hql.PersistenceContext;
import com.mlemaile.ippie.service.dto.ModelDto;
import com.mlemaile.ippie.service.mapper.MapperModel;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContext.class)
public class ServiceModelTest {

    @Mock
    ModelDao modelDao;

    @Autowired
    @InjectMocks
    ServiceModel serviceModel;

    @Before
    public void setup () {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll () {
        Model model1 = DatabaseObject.model1;
        Model model2 = DatabaseObject.model2;
        List<Model> list = Arrays.asList(model1, model2);
        Mockito.when(modelDao.findAll()).thenReturn(list);
        List<ModelDto> returnedList = serviceModel.findAll();
        assertEquals("The returned List is not the expected one.",
                MapperModel.INSTANCE.toListDto(list), returnedList);
    }

}
