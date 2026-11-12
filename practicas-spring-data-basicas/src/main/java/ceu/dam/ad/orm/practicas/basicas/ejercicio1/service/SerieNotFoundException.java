package ceu.dam.ad.orm.practicas.basicas.ejercicio1.service;

public class SerieNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3640487346695264469L;

	public SerieNotFoundException() {
	}

	public SerieNotFoundException(String message) {
		super(message);
	}

	public SerieNotFoundException(Throwable cause) {
		super(cause);
	}

	public SerieNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SerieNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
