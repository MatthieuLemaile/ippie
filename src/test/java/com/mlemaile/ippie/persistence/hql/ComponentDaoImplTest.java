package com.mlemaile.ippie.persistence.hql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.persistence.ComponentDao;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContext.class)
public class ComponentDaoImplTest {

    @Autowired
    private ComponentDao componentDao;

    @Before
    public void setUp () throws Exception {
        DatabaseManagement.setUpDatabase();
    }

    @After
    public void tearDown () throws Exception {
        DatabaseManagement.tearDownDatabase();
    }

    @Transactional
    @Test
    public void testSave () {
        Component c = new Component("Compo 1");
        c.setDetails("This is a test component");
        c.setModel(DatabaseObject.model1);
        c.setIntroduced(LocalDate.of(2017, Month.AUGUST, 12));
        c.setDiscontinued(LocalDate.of(2017, Month.AUGUST, 12));
        c.setState(DatabaseObject.state1);
        c.setStateDetails("Nothing to add");
        componentDao.save(c);
        Optional<Component> optCompo = componentDao.findOne(c.getId());
        assertTrue(optCompo.isPresent());
        assertEquals("Save does not work properly", c, optCompo.get());
    }

    @Transactional
    @Test
    public void saveShouldBeOkForUpdate () {
        Component c = new Component();
        c.setName("Name of test component");
        Optional<Component> optCompo = componentDao.save(c);
        assertTrue(optCompo.isPresent());
        assertEquals("Save does not update correctly", c, optCompo.get());
        c.setDetails("modified details");
        optCompo = componentDao.save(c);
        assertTrue(optCompo.isPresent());
        assertEquals("Save does not update correctly", c, optCompo.get());
    }

    @Test
    public void saveShouldReturnEmptyResultForNullValue () {
        assertFalse("Save does not return an empty result for null value.",
                componentDao.save(null).isPresent());
    }

    @Test
    public void findOneShouldRetrieveAnExistingObject () {
        Optional<Component> compoOpt = componentDao.findOne(DatabaseObject.component5.getId());
        assertTrue("Find One didn't retrieve the object", compoOpt.isPresent());
        assertEquals("Find One didnt' retrieve the right object", DatabaseObject.component5,
                compoOpt.get());
    }

    @Test
    public void findOneShouldReturnEmptyResultForNonExistentObject () {
        assertFalse("Find One does not return empty result", componentDao.findOne(-5L).isPresent());
    }

    @Test
    public void testFindAll () {
        List<Component> components = componentDao.findAll();
        assertEquals("Find all component does not work properly", 12, components.size());
        assertEquals("Find all component does not work properly",
                DatabaseObject.component1, components.get(0));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component2, components.get(1));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component3, components.get(2));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component4, components.get(3));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component5, components.get(4));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component6, components.get(5));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component7, components.get(6));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component8, components.get(7));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component9, components.get(8));
        assertEquals("Find all component does not work properly",
                DatabaseObject.component10, components.get(9));
        assertEquals("Find all component does not work properly", DatabaseObject.component11,
                components.get(10));
        assertEquals("Find all component does not work properly", DatabaseObject.component12,
                components.get(11));
    }

    @Test
    public void testDelete () {
        Component c = DatabaseObject.component9;
        componentDao.delete(c.getId());
        Optional<Component> optCompo = componentDao.findOne(c.getId());
        assertFalse("", optCompo.isPresent());
    }

}
