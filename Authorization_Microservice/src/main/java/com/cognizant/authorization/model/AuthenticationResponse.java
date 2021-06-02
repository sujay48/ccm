package com.cognizant.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * 
 * 		model class for authorization response
 * @Data - Lombok annotation that
 * bundles @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode
 * @AllArgsConstructor - Lombok annotation for constructor with all arguements
 * @NoArgsConstructor - Lombok annotation for constructor with no arguements
 * @Entity - Spring Data JPA Entity class

 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {	
	private String jwt;
}
