package com.mlemaile.ippie.persistence.hql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mlemaile.ippie.DatabaseObject;
import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.persistence.StateDao;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContext.class)
public class StateDaoImplTest {

    @Autowired
    StateDao stateDao;

    @Before
    public void setUp () throws Exception {
        DatabaseManagement.setUpDatabase();
    }

    @After
    public void tearDown () throws Exception {
        DatabaseManagement.tearDownDatabase();
    }

    @Test
    public void findOneShouldReturnAStateWhenExistingId(){
        State s = DatabaseObject.state1;
        Optional<State> optState = stateDao.findOne(s.getId());
        assertTrue("Find one does not work as intended", optState.isPresent());
        assertEquals("Find one does not work as intented", s, optState.get());
    }

    @Test
    public void findOneShouldReturnANullOptionalWithWrongId(){
        Optional<State> optState = stateDao.findOne(537L);
        assertFalse("Find one does not work as intended", optState.isPresent());
    }

    @Test
    public void findAllShouldReturnAllStateFromDb () {
        State s1 = DatabaseObject.state1;
        State s2 = DatabaseObject.state2;
        List<State> listState = stateDao.findAll();
        assertEquals("The find all method is not working as intended", 2, listState.size());
        assertEquals("The find all method is not working as intended", s1, listState.get(0));
        assertEquals("The find all method is not working as intended", s2, listState.get(1));
    }
}
