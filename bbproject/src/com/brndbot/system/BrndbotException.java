package com.brndbot.system;

/** A general-purpose class for application-specific exceptions. */
public class BrndbotException extends Exception {

	private static final long serialVersionUID = 1L;

	public BrndbotException() {
		super();
	}

	public BrndbotException(String message) {
		super(message);
	}

	public BrndbotException(Throwable cause) {
		super(cause);
	}

	public BrndbotException(String message, Throwable cause) {
		super(message, cause);
	}

	public BrndbotException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
