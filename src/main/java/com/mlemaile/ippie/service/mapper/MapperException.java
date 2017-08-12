package com.mlemaile.ippie.service.mapper;

public class MapperException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MapperException(String message) {
        super(message);
    }

    public MapperException(Throwable cause) {
        super(cause);
    }

    public MapperException(String message, Throwable cause) {
        super(message, cause);
    }

}
