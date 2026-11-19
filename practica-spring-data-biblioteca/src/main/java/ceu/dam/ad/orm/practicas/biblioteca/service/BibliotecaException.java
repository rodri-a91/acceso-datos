package ceu.dam.ad.orm.practicas.biblioteca.service;

public class BibliotecaException extends Exception {

	private static final long serialVersionUID = 5126432977330019021L;

	public BibliotecaException() {
	}

	public BibliotecaException(String message) {
		super(message);
	}

	public BibliotecaException(Throwable cause) {
		super(cause);
	}

	public BibliotecaException(String message, Throwable cause) {
		super(message, cause);
	}

}
