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
import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.persistence.ModelDao;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceContext.class)
public class ModelDaoImplTest {

    @Autowired
    private ModelDao modelDao;

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
    public void saveShouldPutACorrectModelInTheDb () {
        Model model = new Model("model");
        model.setType(DatabaseObject.type1);
        modelDao.save(model);
        assertEquals("Save does not work properly", model, modelDao.findOne(model.getId()).get());
    }

    @Transactional
    @Test
    public void saveShouldReturnEmptyResultWithNullValue () {
        assertFalse("Save does not return an empty result for null value",
                modelDao.save(null).isPresent());
    }

    @Transactional
    @Test
    public void saveShouldUpdateWhenModified () {
        Model m1 = DatabaseObject.model1;
        m1.setName(m1.getName() + "modified");
        Optional<Model> modelOpt = modelDao.save(m1);
        assertTrue("save didn't work as expected", modelOpt.isPresent());
        assertEquals("Save didn't update the object",m1,modelOpt.get());
    }

    @Test
    public void findOneShouldRetrieveCorrectlyAnObjectInDatabase () {
        Optional<Model> modelOpt = modelDao.findOne(DatabaseObject.model1.getId());
        assertTrue("find one does not work as expected",
                modelOpt.isPresent());
        assertEquals("Find one didn' retrieve the rigth object", DatabaseObject.model1,
                modelOpt.get());
    }

    @Test
    public void findOneShouldReturnEmptyOptionalForNonExistentObject () {
        assertFalse("Find one does not work correctly", modelDao.findOne(-15L).isPresent());
    }

    @Test
    public void testFindAll () {
        List<Model> models = modelDao.findAll();
        assertEquals("Find all models does not work properly", 6, models.size());
        assertEquals("Find all models does not work properly", DatabaseObject.model1,
                models.get(0));
        assertEquals("Find all models does not work properly", DatabaseObject.model2,
                models.get(1));
        assertEquals("Find all models does not work properly", DatabaseObject.model3,
                models.get(2));
        assertEquals("Find all models does not work properly", DatabaseObject.model4,
                models.get(3));
        assertEquals("Find all models does not work properly", DatabaseObject.model5,
                models.get(4));
        assertEquals("Find all models does not work properly", DatabaseObject.model6,
                models.get(5));
    }

    @Test
    public void testDelete () {
        // TODO implement this test
    }

}
