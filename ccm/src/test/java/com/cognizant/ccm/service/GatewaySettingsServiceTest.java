package com.cognizant.ccm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.ccm.exception.GatewaySettingsParametersNotFoundException;
import com.cognizant.ccm.model.GatewaySettings;
import com.cognizant.ccm.model.GatewaySettingsParameters;
import com.cognizant.ccm.repository.GatewaySettingsParameterRepository;

@ExtendWith(MockitoExtension.class)
class GatewaySettingsServiceTest {

	@Mock
	private GatewaySettingsParameterRepository gatewaySettingsParametersRepository;

	@Mock
	private GatewaySettingsMapper gatewaySettingsMapper;

	@InjectMocks
	private GatewaySettingsServiceImpl gatewaySettingsServiceImpl;

	private GatewaySettingsParameters gatewaySettings = new GatewaySettingsParameters();

	@Test
	void test_GetGatewaySettingsList() throws GatewaySettingsParametersNotFoundException {
		List<GatewaySettingsParameters> gatewaySettingsParametersList = new ArrayList<GatewaySettingsParameters>();
		gatewaySettingsParametersList.add(gatewaySettings);
		Mockito.when(gatewaySettingsParametersRepository.findAll()).thenReturn(gatewaySettingsParametersList);
		assertEquals(1, gatewaySettingsServiceImpl.getGatewaySettingsList().size());
	}

	@Test
	void test_GetGatewaySettingsListFail() {
		Mockito.when(gatewaySettingsParametersRepository.findAll()).thenReturn(new ArrayList<>());
		assertThrows(GatewaySettingsParametersNotFoundException.class,
				() -> gatewaySettingsServiceImpl.getGatewaySettingsList());
	}

	@Test
	void test_GetGatewaySettings() throws GatewaySettingsParametersNotFoundException {
		Optional<GatewaySettingsParameters> gatewaySettingsParameters = Optional.ofNullable(gatewaySettings);
		Mockito.when(gatewaySettingsParametersRepository.findById(Mockito.anyInt()))
				.thenReturn(gatewaySettingsParameters);
		assertEquals(0, gatewaySettingsServiceImpl.getGatewaySettings(1).getGatewaySettingsParameterId());
	}

	@Test
	void test_GetGatewaySettingsFail() {
		Optional<GatewaySettingsParameters> gatewaySettingsParameters = Optional.empty();
		Mockito.when(gatewaySettingsParametersRepository.findById(Mockito.anyInt()))
				.thenReturn(gatewaySettingsParameters);
		assertThrows(GatewaySettingsParametersNotFoundException.class,
				() -> gatewaySettingsServiceImpl.getGatewaySettings(1));
	}

	@Test
	void test_AddGatewayServiceParameters() {
		var gatewaySettings = new GatewaySettings();
		gatewaySettingsServiceImpl.addGatewayServiceParameters(gatewaySettings);
		Mockito.verify(gatewaySettingsMapper, Mockito.times(1)).gatewaySettingsParametersMapper(Mockito.any(),
				Mockito.any());
	}

	@Test
	void test_RedisCache() throws GatewaySettingsParametersNotFoundException {
		Optional<GatewaySettingsParameters> gatewaySettingsParameters = Optional.ofNullable(gatewaySettings);
		Mockito.when(gatewaySettingsParametersRepository.findById(Mockito.anyInt()))
				.thenReturn(gatewaySettingsParameters);

		GatewaySettings gateway1 = gatewaySettingsServiceImpl.getGatewaySettings(0);
		GatewaySettings gateway2 = gatewaySettingsServiceImpl.getGatewaySettings(0);

		assertEquals(gateway1.getGatewaySettingsParameterId(), gateway2.getGatewaySettingsParameterId());
		Mockito.verify(gatewaySettingsParametersRepository, Mockito.times(1)).findById(0);
	}
}
