package com.mlemaile.ippie.persistence.hql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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

    @Test
    public void testSave () {
        fail("Not yet implemented");
    }

    @Test
    public void testFindOne () {
        fail("Not yet implemented");
    }

    @Test
    public void testFindAll () {
        List<Component> components = componentDao.findAll();
        assertEquals("Find all component does not work properly", 10, components.size());
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
    }

    @Test
    public void testDelete () {
        fail("Not yet implemented");
    }

}
