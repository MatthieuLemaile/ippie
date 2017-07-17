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

    @Override
    public boolean equals ( Object obj ) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ModelDto other = (ModelDto) obj;
        if (id != other.id || typeId != other.typeId) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

}
