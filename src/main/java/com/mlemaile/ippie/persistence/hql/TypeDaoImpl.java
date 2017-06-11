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

    private static String HQL_SELECT_ALL      = "From Type";
    private static String HQL_FIND_BY_ID      = "Select t from Type as t where t.id = :id";
    private static String HQL_UPDATE_COMPUTER = "Update Type t set t.name=:name where t.id=:id";

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Type> save ( Type t ) {
        // TODO DAO VERIF
        // Here goes some verification about the object
        if (t.getId() == 0) {
            return create(t);
        } else {
            return update(t);
        }
    }

    private Optional<Type> update ( Type t ) {
        em.createQuery(HQL_UPDATE_COMPUTER).setParameter("name", t.getName())
                .setParameter("id", t.getId()).executeUpdate();
        t = em.contains(t) ? t : em.merge(t);
        return Optional.ofNullable(t);
    }

    private Optional<Type> create ( Type t ) {
        em.persist(t);
        em.flush();
        em.refresh(t);
        return Optional.ofNullable(t);
    }

    @Override
    public Optional<Type> findOne ( long id ) {
        TypedQuery<Type> query = em.createQuery(HQL_FIND_BY_ID, Type.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public List<Type> findAll () {
        return em.createQuery(HQL_SELECT_ALL, Type.class).getResultList();
    }

    @Override
    public void delete ( long id ) {
        Type t = this.em.find(Type.class, id);
        this.em.remove(t);
        // TODO take care of the IllegalArgumentException
    }

}