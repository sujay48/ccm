package com.cognizant.ccm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.cognizant.ccm.model.GatewaySettings;
import com.cognizant.ccm.model.GatewaySettingsParameters;

class GatewaySettingsMapperTest {
	
	private GatewaySettingsMapper gatewaySettingsMapper = new GatewaySettingsMapper();
	
	@Test
	void test_GatewaySettingsMapper() {
		GatewaySettings gatewaySettings = new GatewaySettings();
		GatewaySettingsParameters gatewaySettingsParameters = new GatewaySettingsParameters();
		gatewaySettingsParameters.setGatewaySettingsParameterId(1);
		gatewaySettingsMapper.gatewaySettingsMapper(gatewaySettings, gatewaySettingsParameters);
		assertEquals(1, gatewaySettings.getGatewaySettingsParameterId());
	}
	
	@Test
	void test_GatewaySettingsParametersMapper() {
		GatewaySettings gatewaySettings = new GatewaySettings();
		gatewaySettings.setGatewayId(1);
		GatewaySettingsParameters gatewaySettingsParameters = new GatewaySettingsParameters();
		gatewaySettingsMapper.gatewaySettingsParametersMapper(gatewaySettingsParameters, gatewaySettings);
		assertEquals(1, gatewaySettingsParameters.getGatewayId());
	}
}
