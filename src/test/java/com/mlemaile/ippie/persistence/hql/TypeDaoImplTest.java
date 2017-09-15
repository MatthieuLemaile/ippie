package com.mlemaile.ippie.persistence.hql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    @Transactional
    public void saveNullShouldReturnAnEmptyOptional () {
        Optional<Type> optType = typeDao.save(null);
        assertFalse("The save method does not check null object.", optType.isPresent());
    }

    @Test
    @Transactional
    public void saveWithNullNameShouldReturnAnEmptyOptional () {
        Optional<Type> optType = typeDao.save(new Type());
        assertFalse("The save method does not check null object.", optType.isPresent());
    }

    @Test
    @Transactional
    public void saveWithEmptyNameShouldReturnAnEmptyOptional () {
        Type t = new Type();
        t.setName("");
        Optional<Type> optType = typeDao.save(t);
        assertFalse("The save method does not check null object.", optType.isPresent());
    }

    @Test
    public void findOneShouldReturnATypeWhenCorrectId () {
        Type t = DatabaseObject.type1;
        Optional<Type> optType = typeDao.findOne(t.getId());
        assertTrue("Find one does not work as intended", optType.isPresent());
        assertEquals("Find one does not work as intented", t, optType.get());
    }

    @Test
    public void findOneShouldReturnEmptyOptionalWhenWrongId () {
        Optional<Type> optType = typeDao.findOne(456L);
        assertFalse("Find one does not work as intended", optType.isPresent());
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
        Type t1 = DatabaseObject.type3;
        typeDao.delete(t1.getId());
        Optional<Type> optT1 = typeDao.findOne(t1.getId());
        assertFalse("", optT1.isPresent());
    }

}
