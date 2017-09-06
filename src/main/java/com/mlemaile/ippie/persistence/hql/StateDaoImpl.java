package com.mlemaile.ippie.persistence.hql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mlemaile.ippie.core.State;
import com.mlemaile.ippie.persistence.StateDao;

@Repository
public class StateDaoImpl implements StateDao {

    private static String       HQL_SELECT_ALL = "From State";
    private static String       HQL_FIND_BY_ID = "Select s from State as s where s.id = :id";

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<State> save ( State state ) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<State> findOne ( long id ) {
        TypedQuery<State> query = em.createQuery(HQL_FIND_BY_ID, State.class);
        query.setParameter("id", id);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<State> findAll () {
        return em.createQuery(HQL_SELECT_ALL, State.class).getResultList();
    }

    @Override
    public void delete ( long id ) {
        throw new UnsupportedOperationException();
    }

}
