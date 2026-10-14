package ceu.dam.ad.pedidos.service;

public class PedidoNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5846358620340848648L;

	public PedidoNotFoundException() {
	}

	public PedidoNotFoundException(String message) {
		super(message);
	}

	public PedidoNotFoundException(Throwable cause) {
		super(cause);
	}

	public PedidoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
