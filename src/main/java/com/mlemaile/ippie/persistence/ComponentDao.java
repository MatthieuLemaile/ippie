package com.mlemaile.ippie.persistence;

import java.util.List;
import java.util.Optional;

import com.mlemaile.ippie.core.Component;

public interface ComponentDao {

    /**
     * This method create or update the given component depending on the id.
     * @param c the component to create/update
     */
    public void save(Component c);

    /**
     * This method find a component based on the given id.
     * @param id The id of the component to retrieve.
     * @return an optional of component
     */
    public Optional<Component> findOne(long id);

    /**
     * This method return a list of Component
     * @return
     */
    public List<Component> findAll();

    /**
     * This method delete a Component based on the given id.
     * @param id the id of the component to delete.
     */
    public void delete(long id);
}
