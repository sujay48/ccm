package com.cognizant.ccm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.ccm.client.AuthorizationFeignClient;
import com.cognizant.ccm.exception.GatewaySettingsParametersNotFoundException;
import com.cognizant.ccm.exception.InvalidTokenException;
import com.cognizant.ccm.model.AuthResponse;
import com.cognizant.ccm.model.GatewaySettings;
import com.cognizant.ccm.service.GatewaySettingsSerivce;

@ExtendWith(MockitoExtension.class)
class GatewaySettingsControllerTest {

	@Mock
	AuthorizationFeignClient authorizationFeignClient;
	
	@Mock
	GatewaySettingsSerivce gatewaySettingsSerivce;
	
	@InjectMocks
	GatewaySettingsController gatewaySettingsController;
	
	@Test
	void test_GetGatewaySettingsList() throws GatewaySettingsParametersNotFoundException, InvalidTokenException {
		AuthResponse authResponse = new AuthResponse("","",true);
		Mockito.when(authorizationFeignClient.getValidity(Mockito.anyString())).thenReturn(authResponse);
		GatewaySettings gatewaySettings = new GatewaySettings();
		List<GatewaySettings> gatewaySettingsList = new ArrayList<>();
		gatewaySettingsList.add(gatewaySettings);
		Mockito.when(gatewaySettingsSerivce.getGatewaySettingsList()).thenReturn(gatewaySettingsList);
		assertEquals(1, gatewaySettingsController.getGatewaySettingsList("token").size());
	}
	
	@Test
	void test_GetGatewaySettings() throws GatewaySettingsParametersNotFoundException, InvalidTokenException {
		AuthResponse authResponse = new AuthResponse("","",true);
		Mockito.when(authorizationFeignClient.getValidity(Mockito.anyString())).thenReturn(authResponse);
		GatewaySettings gatewaySettings = new GatewaySettings();
		gatewaySettings.setGatewaySettingsParameterId(1);
		Mockito.when(gatewaySettingsSerivce.getGatewaySettings(Mockito.anyInt())).thenReturn(gatewaySettings);
		assertEquals(gatewaySettings.getGatewaySettingsParameterId(), gatewaySettingsController.getGatewaySettings("token", 1).getGatewaySettingsParameterId());
	}
	
	@Test
	void test_addGatewaySettingsParameters() throws InvalidTokenException {
		AuthResponse authResponse = new AuthResponse("","",true);
		Mockito.when(authorizationFeignClient.getValidity(Mockito.anyString())).thenReturn(authResponse);
		GatewaySettings gatewaySettings = new GatewaySettings();
		gatewaySettingsController.addGatewaySettingsParameters("token", gatewaySettings);
		Mockito.verify(gatewaySettingsSerivce,Mockito.times(1)).addGatewayServiceParameters(gatewaySettings);
	}
	
	@Test
	void test_invalidTokenException() {
		AuthResponse authResponse = new AuthResponse("","",false);
		Mockito.when(authorizationFeignClient.getValidity(Mockito.anyString())).thenReturn(authResponse);
		GatewaySettings gatewaySettings = new GatewaySettings();
		assertThrows(InvalidTokenException.class, () -> gatewaySettingsController.getGatewaySettingsList("token"));
		assertThrows(InvalidTokenException.class, () -> gatewaySettingsController.getGatewaySettings("token", 1));
		assertThrows(InvalidTokenException.class, () -> gatewaySettingsController.addGatewaySettingsParameters("token", gatewaySettings));
		
	}
	
}
