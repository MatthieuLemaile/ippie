package com.mlemaile.ippie.persistence.hql;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.persistence.TypeDao;

@Repository
public class TypeDaoImpl implements TypeDao {

    private static String HQL_SELECT_ALL   = "From Type";
    private static String HQL_FIND_BY_ID   = "Select t from Type as t where t.id = ?";
    private static String HQL_DELETE_BY_ID = "let's see what I did...";

    @PersistenceContext
    EntityManager em;

    @Override
    public void save ( Type t ) {
        // TODO DAO VERIF
        // Here goes some verification about the object
        if (t.getId() == 0) {
            create(t);
        } else {
            update(t);
        }
    }

    private void update ( Type t ) {
        // TODO DAO UPDATE
    }

    private void create ( Type t ) {
        // TODO DAO CREATE
    }

    @Override
    public Optional<Type> findOne ( long id ) {
        TypedQuery<Type> query = em.createQuery(HQL_FIND_BY_ID, Type.class);
        query.setParameter(0, id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Type> findAll () {
        return em.createQuery(HQL_SELECT_ALL, Type.class).getResultList();
    }

    @Override
    public void delete ( long id ) {
        // TODO Auto-generated method stub

    }

}
