package com.mlemaile.ippie.service.dto;

public class StateDto {

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
        return "StateDto [id=" + id + ", name=" + name + "]";
    }

    @Override
    public boolean equals ( Object obj ) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StateDto other = (StateDto) obj;
        if (id != other.id) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
