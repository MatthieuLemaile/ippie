package com.mlemaile.ippie.persistence;

import java.util.List;
import java.util.Optional;

import com.mlemaile.ippie.core.Component;
import com.mlemaile.ippie.core.Model;

public interface ComponentDao {

    /**
     * This method create or update the given component depending on the id.
     * @param c the component to create/update
     * @return the saved component
     */
    public Optional<Component> save(Component c);

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
     * This method retrieve all component using the given model
     * @param m the model component are using
     * @return a List of Component using the Model m
     */
    public List<Component> findWhereModelIs(Model m);

    /**
     * This method delete a Component based on the given id.
     * @param id the id of the component to delete.
     * @return true if the operation went well
     */
    public boolean delete(long id);
}
