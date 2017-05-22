package com.mlemaile.ippie.persistence;

import java.util.List;
import java.util.Optional;

import com.mlemaile.ippie.core.State;

public interface StateDao {

    /**
     * This method update or create the given State
     * @param state the State to update/create.
     */
    public void save(State state);

    /**
     * This method find a State based on the given id.
     * @param id the id of the State to find
     * @return An optional of the state
     */
    public Optional<State> findOne(long id);

    /**
     * This method List all the State.
     * @return a List of State
     */
    public List<State> findAll();

    /**
     * This method delete the State based on the given id
     * @param id the id of the State to delete.
     */
    public void delete(long id);
}
