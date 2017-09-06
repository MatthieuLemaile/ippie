package com.mlemaile.ippie.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
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
import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.core.State;
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
    private MapperComponent mapperComponent;

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
                mapperComponent.toListDto(list), returnedList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowIAEWithNullValue () {
        serviceComponent.save(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowIAEWithNullName () {
        Component component = DatabaseObject.component1;
        component.setName(null);
        serviceComponent.save(mapperComponent.toDto(component));
    }

    @Test(expected = IllegalArgumentException.class)
    public void saveShouldThrowIAEWithEmptyName () {
        Component component = DatabaseObject.component1;
        component.setName("");
        serviceComponent.save(mapperComponent.toDto(component));
    }

    @Test
    public void saveShouldReturnTrueWhenDaoOk () {
        Component component = DatabaseObject.component9;
        Optional<Component> optComponent = Optional.of(component);
        Mockito.when(componentDao.save(component)).thenReturn(optComponent);
        boolean response = serviceComponent.save(mapperComponent.toDto(component));
        assertTrue("Service does not return true when Dao ok", response);
    }

    @Test
    public void saveShouldReturnFalseWhenDaoNok () {
        Component component = DatabaseObject.component8;
        Optional<Component> optComponent = Optional.empty();
        Mockito.when(componentDao.save(component)).thenReturn(optComponent);
        boolean response = serviceComponent.save(mapperComponent.toDto(component));
        assertFalse("Service does not return false when Dao nok", response);
    }

    @Test
    public void findOneWhouldBeOkWhenDaoOk(){
        String name = "Test";
        LocalDate introduced = LocalDate.of(2015, Month.FEBRUARY, 15);
        LocalDate discontinued = LocalDate.of(2016, Month.APRIL, 15);
        State state = DatabaseObject.state1;
        Model model = DatabaseObject.model1;
        String stateDetails = "detail about state";
        String details = "details";
        Optional<Component> compoOpt = Optional.of(
                new Component(name, introduced, discontinued, state, stateDetails, model, details));
        Mockito.when(componentDao.findOne(5L)).thenReturn(compoOpt);
        ComponentDto dto = serviceComponent.findOne(5L);
        assertEquals("Find one does not work as intended", name, dto.getName());
        assertEquals("Find one does not work as intended", introduced.toString(),
                dto.getIntroduced());
        assertEquals("Find one does not work as intended", discontinued.toString(),
                dto.getDiscontinued());
        assertEquals("Find one does not work as intended", state.getId(), dto.getStateId());
        assertEquals("Find one does not work as intended", model.getId(), dto.getModelId());
        assertEquals("Find one does not work as intended", stateDetails, dto.getStateDetails());
        assertEquals("Find one does not work as intended", details, dto.getDetails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOneShouldBeNokWhenDaoNok () {
        Mockito.when(componentDao.findOne(5L)).thenReturn(Optional.empty());
        serviceComponent.findOne(5L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findOneShouldTrowIAEWhenIdIncorrect () {
        serviceComponent.findOne(-5L);
    }
}
