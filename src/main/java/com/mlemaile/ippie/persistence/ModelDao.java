package com.mlemaile.ippie.persistence;

import java.util.List;
import java.util.Optional;

import com.mlemaile.ippie.core.Model;

public interface ModelDao {

    /**
     * This method create or update the given Model (depending on whether or not the id is set).
     * @param model the model to create/update
     * @return The saved model
     */
    public Optional<Model> save ( Model model );

    /**
     * This method return the model corresponding to the given id.
     * @param id the id of the model you want.
     * @return an Optional of Model
     */
    public Optional<Model> findOne(long id);

    /**
     * This method find all Model on the database
     * @return A List of Model
     */
    public List<Model> findAll();

    /**
     * This method delete the model corresponding to the given id.
     * @param id the id of the model to delete.
     */
    public void delete(long id);
}
