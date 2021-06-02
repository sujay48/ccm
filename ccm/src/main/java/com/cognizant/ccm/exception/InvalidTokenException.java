package com.cognizant.ccm.exception;
/**
 * Custom Exception class to handle Invalid Tokens
 * @author Sujay
 *
 */
public class InvalidTokenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
		super("Security Token is Invalid or it may be Expired");
	}
}
