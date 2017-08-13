package com.mlemaile.ippie.persistence.hql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mlemaile.ippie.core.Model;
import com.mlemaile.ippie.persistence.ModelDao;

@Repository
public class ModelDaoImpl implements ModelDao {

    private static String HQL_SELECT_ALL = "From Model";
    private static String HQL_SELECT_BY_ID = "Select m from Model as m where m.id=:id";
    private static String HQL_UPDATE     = "Update Model m set m.name=:name, m.type=:type where m.id=:id";
    private static final Logger LOGGER           = LoggerFactory.getLogger(ModelDao.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Optional<Model> save ( Model model ) {
        // TODO DAO VERIF what about a null name ? and a null Type ?
        // Some verification about the object
        if (model.getId() == 0) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Creating Model in the database : " + model);
            }
            return create(model);
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Updating Model in the database : " + model);
            }
            return update(model);
        }
    }

    private Optional<Model> update ( Model m ) {
        em.createQuery(HQL_UPDATE).setParameter("name", m.getName())
                .setParameter("type", m.getType()).setParameter("id", m.getId())
                .executeUpdate();
        m = em.contains(m) ? m : em.merge(m);
        return Optional.ofNullable(m);
    }

    private Optional<Model> create ( Model m ) {
        em.persist(m);
        em.flush();
        em.refresh(m);
        return Optional.ofNullable(m);
    }

    @Override
    public Optional<Model> findOne ( long id ) {
        TypedQuery<Model> query = em.createQuery(HQL_SELECT_BY_ID, Model.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Model> findAll () {
        return em.createQuery(HQL_SELECT_ALL, Model.class).getResultList();
    }

    @Override
    public void delete ( long id ) {
        // TODO Handle IllegalArgumentException (find)
        Model m = em.find(Model.class, id);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Deleting Model in the database : " + m);
        }
        em.remove(m);
    }
}
