package ceu.dam.ad.orm.practicas.basicas.ejercicio1.service;

public class SeriesServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2963030282235260647L;

	public SeriesServiceException() {
	}

	public SeriesServiceException(String message) {
		super(message);
	}

	public SeriesServiceException(Throwable cause) {
		super(cause);
	}

	public SeriesServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeriesServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
