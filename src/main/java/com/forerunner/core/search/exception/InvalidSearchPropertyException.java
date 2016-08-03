package com.forerunner.core.search.exception;

/**
 * 
 * @author Administrator
 *
 */
public final class InvalidSearchPropertyException extends SearchException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7486649986355623605L;

	public InvalidSearchPropertyException(String searchProperty, String entityProperty) {
        this(searchProperty, entityProperty, null);
    }

    public InvalidSearchPropertyException(String searchProperty, String entityProperty, Throwable cause) {
        super("Invalid Search Property [" + searchProperty + "] Entity Property [" + entityProperty + "]", cause);
    }


}
