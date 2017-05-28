package com.mlemaile.ippie.service.dto;

public class ModelDto {
    private long   id;
    private String name;
    private long   typeId;
    private String type;

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

    public long getTypeId () {
        return typeId;
    }

    public void setTypeId ( long typeId ) {
        this.typeId = typeId;
    }

    public String getType () {
        return type;
    }

    public void setType ( String type ) {
        this.type = type;
    }

    @Override
    public String toString () {
        return "ModelDto [id=" + id + ", name=" + name + ", typeId=" + typeId + ", type="
                + type + "]";
    }
}
