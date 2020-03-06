package com.servicioempleados.app.exception;

public class RegistroNoEncontradoException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RegistroNoEncontradoException() {
		super("No existe empleado en nuestra base de datos");
	}
}
