package com.cognizant.ccm.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.ccm.model.AuthResponse;

import feign.Headers;

/**
 * This interface is a feign Client used to communicate with
 * authorization-microservice
 * 
 * @author Sujay
 *
 */
@Headers("Content-Type: application/json")
@FeignClient(name = "Authorization", url = "${feign.authorization.url}", fallback = AuthorizationFallbackFactory.class)
public interface AuthorizationFeignClient {

	@GetMapping(value = "/validate")
	public AuthResponse getValidity(@RequestHeader("Authorization") String token);

}
@Component 
class AuthorizationFallbackFactory implements AuthorizationFeignClient {

	@Override
	public AuthResponse getValidity(String token) {
		return new AuthResponse("","",false);
	}
	
}
