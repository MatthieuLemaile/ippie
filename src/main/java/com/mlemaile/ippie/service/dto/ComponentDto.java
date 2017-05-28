package com.mlemaile.ippie.service.dto;

public class ComponentDto {
    private long   id;
    private String name;
    private String introduced;
    private String discontinued;
    private long   stateId;
    private String state;
    private String stateDetails;
    private String type;
    private long   modelId;
    private String model;
    private String details;


    public long getId () {
        return id;
    }

    public void setId ( long id ) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName ( String name ) {
        this.name = name;
    }

    public String getIntroduced () {
        return introduced;
    }

    public void setIntroduced ( String introduced ) {
        this.introduced = introduced;
    }

    public String getDiscontinued () {
        return discontinued;
    }

    public void setDiscontinued ( String discontinued ) {
        this.discontinued = discontinued;
    }

    public String getState () {
        return state;
    }

    public void setState ( String state ) {
        this.state = state;
    }

    public String getStateDetails () {
        return stateDetails;
    }

    public void setStateDetails ( String stateDetails ) {
        this.stateDetails = stateDetails;
    }

    public String getType () {
        return type;
    }

    public void setType ( String type ) {
        this.type = type;
    }

    public String getModel () {
        return model;
    }

    public void setModel ( String model ) {
        this.model = model;
    }

    public String getDetails () {
        return details;
    }

    public void setDetails ( String details ) {
        this.details = details;
    }

    @Override
    public String toString () {
        return "ComponentDto [id=" + id + ", name=" + name + ", introduced=" + introduced
                + ", discontinued=" + discontinued + ", stateId=" + stateId + ", state="
                + state + ", stateDetails=" + stateDetails + ", type=" + type + ", model="
                + model + ", modelId=" + modelId + ", details=" + details + "]";
    }

    public long getModelId () {
        return modelId;
    }

    public void setModelId ( long modelId ) {
        this.modelId = modelId;
    }

    public long getStateId () {
        return stateId;
    }

    public void setStateId ( long stateId ) {
        this.stateId = stateId;
    }
}
