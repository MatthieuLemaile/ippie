package com.mlemaile.ippie.persistence.hql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.persistence.ComponentDao;

@Repository
public class ComponentDaoImpl implements ComponentDao {
    private static final Logger LOGGER               = LoggerFactory.getLogger(ComponentDao.class);
    private static String       HQL_SELECT_BY_ID     = "Select c from Component as c where c.id=:id";
    private static String       HQL_FIND_ALL         = "From Component";
    private static String       HQL_UPDATE_COMPONENT = "Update Component c set c.name=:name, "
            + "c.introduced=:introduced, c.discontinued=:discontinued, c.state=:state, "
            + "c.stateDetails=:stateDetails, c.model=:model, c.details=:details where c.id=:id";

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public Optional<Component> save ( Component c ) {
        // TODO What check tp dp about a Component ?
        if (c == null) {
            return Optional.empty();
        }
        if (c.getId() == 0) {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Creating Component in the database : " + c);
            }
            return create(c);
        } else {
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("Updating Component in the database : " + c);
            }
            return update(c);
        }
    }

    private Optional<Component> create ( Component c ) {
        em.persist(c);
        em.flush();
        em.refresh(c);
        return Optional.ofNullable(c);
    }

    private Optional<Component> update ( Component c ) {
        em.createQuery(HQL_UPDATE_COMPONENT).setParameter("name", c.getName())
                .setParameter("introduced", c.getIntroduced())
                .setParameter("discontinued", c.getDiscontinued())
                .setParameter("state", c.getState())
                .setParameter("stateDetails", c.getStateDetails())
                .setParameter("model", c.getModel()).setParameter("details", c.getDetails())
                .setParameter("id", c.getId()).executeUpdate();
        c = em.contains(c) ? c : em.merge(c);
        return Optional.ofNullable(c);
    }

    @Override
    public Optional<Component> findOne ( long id ) {
        TypedQuery<Component> query = em.createQuery(HQL_SELECT_BY_ID, Component.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Component> findAll () {
        return em.createQuery(HQL_FIND_ALL, Component.class).getResultList();
    }

    @Override
    @Transactional
    public boolean delete ( long id ) {
        Component c = this.em.find(Component.class, id);
        LOGGER.info("Deleting component {}", c);
        try {
            this.em.remove(c);
            return true;
        } catch (IllegalArgumentException e) {
            LOGGER.info("Can't delete component {} with exception {}", c, e.getMessage());
            LOGGER.debug("Exception is {}", e);
            return false;
        }
    }

}
