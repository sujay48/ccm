package com.cognizant.ccm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ccm.exception.GatewaySettingsParametersNotFoundException;
import com.cognizant.ccm.model.GatewaySettings;
import com.cognizant.ccm.model.GatewaySettingsParameters;
import com.cognizant.ccm.repository.GatewaySettingsParameterRepository;

import lombok.extern.slf4j.Slf4j;
/**
 * Service Class which incorporates all the Business logic methods
 * 
 * @author Sujay
 *
 */
@Slf4j
@Service
@Transactional
public class GatewaySettingsServiceImpl implements GatewaySettingsSerivce {

	@Autowired
	private GatewaySettingsParameterRepository gatewaySettingsParameterRepository;

	@Autowired
	private GatewaySettingsMapper gatewaySettingsMapper;
	
	@Value("${exception.gateway.empty}")
	private String emptyList;
	
	@Value("${exception.gateway.notfound}")
	private String notFound;

	/**
	 * Gets all the List of Gateway Settings Parameters,
	 * Throws Exception if List is Empty 
	 */
	@Override
	public List<GatewaySettings> getGatewaySettingsList() throws GatewaySettingsParametersNotFoundException {
		log.info("START : getGatewaySettingsList()");
		List<GatewaySettingsParameters> gatewaySettingsParameterList = gatewaySettingsParameterRepository.findAll();
		List<GatewaySettings> gatewaySettingsList = new ArrayList<>();
		gatewaySettingsParameterList.stream().forEach(g -> {
			var gatewaySettings = new GatewaySettings();
			// Entity to DTO Conversion
			gatewaySettingsMapper.gatewaySettingsMapper(gatewaySettings, g);
			gatewaySettingsList.add(gatewaySettings);
		});
		/*
		 * If List is Empty, throws Exception
		 */
		if(gatewaySettingsList.isEmpty()) {
			throw new GatewaySettingsParametersNotFoundException(emptyList);
		}
		log.info("END : getGatewaySettingsList()");
		return gatewaySettingsList;
	}

	/**
	 * Gets Gateway Settings Parameter By Id,
	 * Throws Exception if not found
	 */
	@Override
	public GatewaySettings getGatewaySettings(int id) throws GatewaySettingsParametersNotFoundException {
		log.info("START : getGatewaySettings()");
		GatewaySettingsParameters gatewaySettingsParameter = gatewaySettingsParameterRepository
			.findById(id)
			.orElseThrow(() -> new GatewaySettingsParametersNotFoundException(notFound));
		var gatewaySettings = new GatewaySettings();
		// Entity to DTO Conversion
		gatewaySettingsMapper.gatewaySettingsMapper(gatewaySettings, gatewaySettingsParameter);
		log.info("END : getGatewaySettings()");
		return gatewaySettings;
	}

	/**
	 * Adds a new entry to Gateway Settings Parameters
	 */
	@Override
	public void addGatewayServiceParameters(GatewaySettings gatewaySettings) {
		log.info("START : addGatewayServiceParameters()");
		var gatewaySettingsParameters = new GatewaySettingsParameters();
		// DTO to Entity Conversion
		gatewaySettingsMapper.gatewaySettingsParametersMapper(gatewaySettingsParameters, gatewaySettings);
		gatewaySettingsParameterRepository.save(gatewaySettingsParameters);
		log.info("END : addGatewayServiceParameters()");
	}

}
