package com.mlemaile.ippie.persistence.hql;

public class TestDatabaseInitialisationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TestDatabaseInitialisationException(String message) {
        super(message);
    }

    public TestDatabaseInitialisationException(Throwable t) {
        super(t);
    }

    public TestDatabaseInitialisationException(String m, Throwable t) {
        super(m, t);
    }

}
