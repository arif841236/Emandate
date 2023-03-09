package com.mandate.exception;

public class EmandateException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public EmandateException(){}
	public EmandateException(String msg) {
		super(msg);
	}
}
