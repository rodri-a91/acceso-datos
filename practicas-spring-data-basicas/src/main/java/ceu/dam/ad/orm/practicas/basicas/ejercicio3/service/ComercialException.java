package ceu.dam.ad.orm.practicas.basicas.ejercicio3.service;

public class ComercialException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7609690449829076259L;

	public ComercialException() {
	}

	public ComercialException(String message) {
		super(message);
	}

	public ComercialException(Throwable cause) {
		super(cause);
	}

	public ComercialException(String message, Throwable cause) {
		super(message, cause);
	}

	public ComercialException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
