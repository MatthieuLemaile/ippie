package com.mlemaile.ippie.persistence.hql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.persistence.ComponentDao;

@Repository
public class ComponentDaoImpl implements ComponentDao {

    private static String HQL_FIND_ALL = "From Component";

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Component> save ( Component c ) {
        // TODO ComponentDao
        return null;
    }

    @Override
    public Optional<Component> findOne ( long id ) {
        // TODO ComponentDao
        return null;
    }

    @Override
    public List<Component> findAll () {
        return em.createQuery(HQL_FIND_ALL, Component.class).getResultList();
    }

    @Override
    public void delete ( long id ) {
        // TODO ComponentDao

    }

}
