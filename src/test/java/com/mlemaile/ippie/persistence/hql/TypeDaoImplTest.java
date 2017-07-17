package com.mlemaile.ippie.persistence.hql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

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
import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.persistence.TypeDao;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContext.class)
public class TypeDaoImplTest {

    @Autowired
    private TypeDao typeDao;

    @Before
    public void setUp () throws Exception {
        DatabaseManagement.setUpDatabase();
    }

    @After
    public void tearDown () throws Exception {
        DatabaseManagement.tearDownDatabase();
    }

    @Test
    @Transactional
    public void saveShouldPutACorrectTypeInTheDb () {
        Type t = new Type("Essai");
        t = typeDao.save(t).get();
        assertEquals("Save does not work properly", t, typeDao.findOne(t.getId()).get());
    }

    @Test
    public void testFindOne () {
        fail("Not yet implemented");
    }

    @Test
    public void findAllShouldRetrieveAllTypeFromTheDb () {
        Type t1 = DatabaseObject.type1;
        Type t2 = DatabaseObject.type2;
        Type t3 = DatabaseObject.type3;
        List<Type> types = typeDao.findAll();
        assertEquals("Find all Types does not work properly.", 3, types.size());
        assertEquals("Find all Types does not work properly.", t1, types.get(0));
        assertEquals("Find all Types does not work properly.", t2, types.get(1));
        assertEquals("Find all Types does not work properly.", t3, types.get(2));
    }

    @Test
    public void testDelete () {
        fail("Not yet implemented");
    }

}
