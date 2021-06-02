package com.cognizant.ccm.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.ccm.client.AuthorizationFeignClient;
import com.cognizant.ccm.exception.GatewaySettingsParametersNotFoundException;
import com.cognizant.ccm.exception.InvalidTokenException;
import com.cognizant.ccm.model.GatewaySettings;
import com.cognizant.ccm.service.GatewaySettingsSerivce;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Controller class which incorporates all the api endpoints
 * 
 * @author Sujay
 *
 */
@Slf4j
@RestController
@RequestMapping("/gateway-settings")
public class GatewaySettingsController {

	@Autowired
	private GatewaySettingsSerivce gatewaySettingsService;

	@Autowired
	private AuthorizationFeignClient authorizationFeignClient;

	/**
	 * Lists all the Gateway Settings Parameters
	 * 
	 * @return Gateway Settings Parameters List
	 * @throws GatewaySettingsParametersNotFoundException
	 * 
	 */
	@GetMapping
	public List<GatewaySettings> getGatewaySettingsList(@RequestHeader(name = "Authorization") String token)
			throws GatewaySettingsParametersNotFoundException, InvalidTokenException {
		log.info("START : getGatewaySettingsList()");
		var authResponse = authorizationFeignClient.getValidity(token);
		if (authResponse.isValid()) {
			return gatewaySettingsService.getGatewaySettingsList();
		} else {
			throw new InvalidTokenException();
		}

	}

	/**
	 * Gets the Gateway Settings Parameters by Id
	 * 
	 * @param Gateway Settings Parameters Id
	 * @return Gateway Settings Parameter Object
	 * @throws GatewaySettingsParametersNotFoundException
	 * 
	 */
	@GetMapping("/{id}")
	public GatewaySettings getGatewaySettings(@RequestHeader(name = "Authorization") String token, @PathVariable int id)
			throws GatewaySettingsParametersNotFoundException, InvalidTokenException {
		log.info("START : getGatewaySettings()");
		var authResponse = authorizationFeignClient.getValidity(token);
		if (authResponse.isValid()) {
			return gatewaySettingsService.getGatewaySettings(id);
		} else {
			throw new InvalidTokenException();
		}
	}

	/**
	 * Adds new entry of Gateway Settings Parameters
	 * 
	 * @param Gateway Settings Parameters Object
	 * 
	 */
	@PostMapping
	public void addGatewaySettingsParameters(@RequestHeader(name = "Authorization") String token,
			@Valid @RequestBody GatewaySettings gatewaySettings) throws InvalidTokenException {
		log.info("START : addGatewaySettingsParameters()");
		var authResponse = authorizationFeignClient.getValidity(token);
		if (authResponse.isValid()) {
			gatewaySettingsService.addGatewayServiceParameters(gatewaySettings);
			log.info("END : addGatewaySettingsParameters()");
		} else {
			throw new InvalidTokenException();
		}
	}

}
