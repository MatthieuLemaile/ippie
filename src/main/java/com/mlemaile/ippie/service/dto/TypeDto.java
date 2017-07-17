package com.mlemaile.ippie.service.dto;

public class TypeDto {

    private long   id;
    private String name;

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

    @Override
    public String toString () {
        return "TypeDto [id=" + id + ", name=" + name + "]";
    }

    @Override
    public boolean equals ( Object obj ) {
        if (obj == null || !(obj instanceof TypeDto)) {
            return false;
        }
        TypeDto type = (TypeDto) obj;
        if (type.name != null) {
            return type.id == this.id && type.name.equals(this.name);
        } else {
            return type.id == this.id && this.name == null;
        }
    }
}