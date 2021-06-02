package com.cognizant.authorization.model;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * 
 * model class for user details
 * 
 * @Entity indicates Spring Data JPA that it is an entity class for the
 *         application
 * @Data is a convenient shortcut annotation that bundles the features
 *       of @ToString , @EqualsAndHashCode , @Getter / @Setter
 *       and @RequiredArgsConstructor together
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

	@NotEmpty(message = "{username.required}")
	private String username;
	
	@NotEmpty(message = "{password.required}")
	private String password;

}
