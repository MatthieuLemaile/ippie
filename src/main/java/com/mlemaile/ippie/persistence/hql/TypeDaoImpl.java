package com.mlemaile.ippie.persistence.hql;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mlemaile.ippie.core.Type;
import com.mlemaile.ippie.persistence.TypeDao;

@Repository
public class TypeDaoImpl implements TypeDao {

    @Override
    public void save(Type t) {
        // TODO Auto-generated method stub

    }

    @Override
    public Optional<Type> findOne(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Type> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(long id) {
        // TODO Auto-generated method stub

    }

}
