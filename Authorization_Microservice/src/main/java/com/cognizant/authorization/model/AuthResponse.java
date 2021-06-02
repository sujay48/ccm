package com.cognizant.authorization.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
	
	@JsonIgnore
	private String uId;
	@JsonIgnore
	private String uName;
	private boolean isValid;
}
