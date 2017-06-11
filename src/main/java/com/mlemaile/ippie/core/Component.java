package com.mlemaile.ippie.core;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "component")
public class Component {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long      id;
    private String    name;
    private LocalDate introduced;
    private LocalDate discontinued;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private State     state;
    @Column(name = "state_details")
    private String    stateDetails;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Model     model;
    private String    details;

    public Component() {
        // default empty constructor
    }

    public Component(String name) {
        this.name = name;
    }

    public Component(String name,
            LocalDate introduced,
            LocalDate discontinued,
            State state,
            String stateDetails,
            Model model,
            String details) {
        this(name);
        this.setIntroduced(introduced);
        this.setDiscontinued(discontinued);
        this.setState(state);
        this.setStateDetails(stateDetails);
        this.setModel(model);
        this.setDetails(details);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getIntroduced() {
        return introduced;
    }

    public void setIntroduced(LocalDate introduced) {
        this.introduced = introduced;
    }

    public LocalDate getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(LocalDate discontinued) {
        this.discontinued = discontinued;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getStateDetails() {
        return stateDetails;
    }

    public void setStateDetails(String stateDetails) {
        this.stateDetails = stateDetails;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((details == null) ? 0 : details.hashCode());
        result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
        result = prime * result + ((model == null) ? 0 : model.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((stateDetails == null) ? 0 : stateDetails.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Component other = (Component) obj;
        if (details == null) {
            if (other.details != null)
                return false;
        } else if (!details.equals(other.details))
            return false;
        if (discontinued == null) {
            if (other.discontinued != null)
                return false;
        } else if (!discontinued.equals(other.discontinued))
            return false;
        if (id != other.id)
            return false;
        if (introduced == null) {
            if (other.introduced != null)
                return false;
        } else if (!introduced.equals(other.introduced))
            return false;
        if (model == null) {
            if (other.model != null)
                return false;
        } else if (!model.equals(other.model))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (state == null) {
            if (other.state != null)
                return false;
        } else if (!state.equals(other.state))
            return false;
        if (stateDetails == null) {
            if (other.stateDetails != null)
                return false;
        } else if (!stateDetails.equals(other.stateDetails))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Component [id=" + id + ", name=" + name + ", introduced=" + introduced
                + ", discontinued=" + discontinued + ", state=" + state + ", stateDetails="
                + stateDetails + ", model=" + model + ", details=" + details + "]";
    }
}
