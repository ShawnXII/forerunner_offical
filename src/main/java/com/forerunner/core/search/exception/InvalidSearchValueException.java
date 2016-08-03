package com.forerunner.core.search.exception;

/**
 * 
 * @author Administrator
 *
 */
public final class InvalidSearchValueException extends SearchException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5522295578374867394L;

	public InvalidSearchValueException(String searchProperty, String entityProperty, Object value) {
        this(searchProperty, entityProperty, value, null);
    }

    public InvalidSearchValueException(String searchProperty, String entityProperty, Object value, Throwable cause) {
        super("Invalid Search Value, searchProperty [" + searchProperty + "], " +
                "entityProperty [" + entityProperty + "], value [" + value + "]", cause);
    }

}
