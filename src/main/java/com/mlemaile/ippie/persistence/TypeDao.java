package com.mlemaile.ippie.persistence;

import java.util.List;
import java.util.Optional;

import com.mlemaile.ippie.core.Type;

public interface TypeDao {

    /**
     * This method update or create (depending on whether the id is set) the given Type.
     * @param t the Type to update or create.
     * @return The saved computer
     */
    public Optional<Type> save(Type t);

    /**
     * This method find one Type by his id.
     * @param id the id of the Type to find.
     * @return the Type found
     */
    public Optional<Type> findOne(long id);

    /**
     * This method return all Type from the database.
     * @return a List of Type.
     */
    public List<Type> findAll();

    /**
     * This method delete the type corresponding to the given id.
     * @param id
     */
    public void delete(long id);
}
