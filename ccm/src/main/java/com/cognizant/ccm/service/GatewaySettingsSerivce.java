package com.cognizant.ccm.service;

import java.util.List;

import com.cognizant.ccm.exception.GatewaySettingsParametersNotFoundException;
import com.cognizant.ccm.model.GatewaySettings;
/**
 * Interface which incorporates all the Business logic methods
 * @author Sujay
 *
 */
public interface GatewaySettingsSerivce {

	List<GatewaySettings> getGatewaySettingsList() throws GatewaySettingsParametersNotFoundException;

	GatewaySettings getGatewaySettings(int id) throws GatewaySettingsParametersNotFoundException;

	void addGatewayServiceParameters(GatewaySettings gatewaySettings);
	
}
