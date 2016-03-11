package ir.co.dpq.pluf.wiki;

import ir.co.dpq.pluf.PException;

public class PWikiException extends PException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -744401153523946018L;

	/**
	 * 
	 */
	public PWikiException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PWikiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PWikiException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public PWikiException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PWikiException(Throwable cause) {
		super(cause);
	}

}
