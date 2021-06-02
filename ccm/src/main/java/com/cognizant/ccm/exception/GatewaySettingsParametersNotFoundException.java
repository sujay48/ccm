package com.cognizant.ccm.exception;
/**
 * Custom Exception Class
 * @author Sujay
 *
 */
public class GatewaySettingsParametersNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public GatewaySettingsParametersNotFoundException(String message) {
		super(message);
	}
}
