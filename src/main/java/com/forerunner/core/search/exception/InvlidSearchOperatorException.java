package com.forerunner.core.search.exception;

/**
 * 
 * @author Administrator
 *
 */
public final class InvlidSearchOperatorException extends SearchException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1548250343258420280L;

	public InvlidSearchOperatorException(String searchProperty, String operatorStr) {
        this(searchProperty, operatorStr, null);
    }

    public InvlidSearchOperatorException(String searchProperty, String operatorStr, Throwable cause) {
        super("Invalid Search Operator searchProperty [" + searchProperty + "], " +
                "operator [" + operatorStr + "], must be one of "  /*SearchOperator.toStringAllOperator()*/, cause);
    }
}
