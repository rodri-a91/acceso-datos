package ceu.dam.ad.orm.practicas.biblioteca.service;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = -3640487346695264469L;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
