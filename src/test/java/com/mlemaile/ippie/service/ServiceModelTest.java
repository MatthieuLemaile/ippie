package com.mlemaile.ippie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
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
import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.persistence.ModelDao;
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

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowIAEWhenNullValue () {
        serviceModel.save(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowIAEWhenNameIsNull () {
        ModelDto dto = new ModelDto();
        dto.setType("");
        dto.setTypeId(8);
        serviceModel.save(dto);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowIAEWhenNameEmpty () {
        ModelDto dto = new ModelDto();
        dto.setName("");
        dto.setType("");
        dto.setTypeId(8);
        serviceModel.save(dto);
    }

    @Test
    public void saveShouldReturnTrueWhenDaoOk(){
        Model model = DatabaseObject.model1;
        Optional<Model> optModel = Optional.of(model);
        Mockito.when(modelDao.save(model)).thenReturn(optModel);
        boolean response = serviceModel.save(MapperModel.INSTANCE.toDto(model));
        assertTrue("Service does not return true when Dao ok", response);
    }

    @Test
    public void saveShouldReturnFalseWhenDaoNOk () {
        Model model = DatabaseObject.model1;
        Optional<Model> optModel = Optional.empty();
        Mockito.when(modelDao.save(model)).thenReturn(optModel);
        boolean response = serviceModel.save(MapperModel.INSTANCE.toDto(model));
        assertFalse("Service does not return false when Dao nok", response);
    }

}
