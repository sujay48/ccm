package com.cognizant.ccm.service;

import org.springframework.stereotype.Component;

import com.cognizant.ccm.model.GatewaySettings;
import com.cognizant.ccm.model.GatewaySettingsParameters;

@Component
public class GatewaySettingsMapper {
	
	/**
	 * Entity to DTO Conversion Gateway Settings Parameters -> Gateway Settings
	 * 
	 * @param gatewaySettings
	 * @param gatewaySettingsParameters
	 */
	public void gatewaySettingsMapper(GatewaySettings gatewaySettings, GatewaySettingsParameters gatewaySettingsParameters) {
		gatewaySettings.setGatewaySettingsParameterId(gatewaySettingsParameters.getGatewaySettingsParameterId());
		gatewaySettings.setFieldCode(gatewaySettingsParameters.getFieldCode());
		gatewaySettings.setFieldName(gatewaySettingsParameters.getFieldName());
		gatewaySettings.setFieldDescription(gatewaySettingsParameters.getFieldDescription());
		gatewaySettings.setDataType(gatewaySettingsParameters.getDataType());
		gatewaySettings.setIsrequired(gatewaySettingsParameters.getIsrequired());
		gatewaySettings.setDataFormat(gatewaySettingsParameters.getDataFormat());
		gatewaySettings.setFieldDefaultValue(gatewaySettingsParameters.getFieldDefaultValue());
		gatewaySettings.setMaximumValue(gatewaySettingsParameters.getMaximumValue());
		gatewaySettings.setMinimumValue(gatewaySettingsParameters.getMinimumValue());
		gatewaySettings.setFieldValue(gatewaySettingsParameters.getFieldValue());
		gatewaySettings.setAffectedModules(gatewaySettingsParameters.getAffectedModules());
		gatewaySettings.setGatewayId(gatewaySettingsParameters.getGatewayId());
		gatewaySettings.setCreatedon(gatewaySettingsParameters.getCreatedon());
		gatewaySettings.setUpdatedon(gatewaySettingsParameters.getUpdatedon());
	}
	
	/**
	 * DTO to Entity Conversion Gateway Settings -> Gateway Settings Parameters
	 * 
	 * @param gatewaySettings
	 * @param gatewaySettingsParameters
	 */
	public void gatewaySettingsParametersMapper(GatewaySettingsParameters gatewaySettings, GatewaySettings gatewaySettingsParameters) {
		gatewaySettings.setFieldCode(gatewaySettingsParameters.getFieldCode());
		gatewaySettings.setFieldName(gatewaySettingsParameters.getFieldName());
		gatewaySettings.setFieldDescription(gatewaySettingsParameters.getFieldDescription());
		gatewaySettings.setDataType(gatewaySettingsParameters.getDataType());
		gatewaySettings.setIsrequired(gatewaySettingsParameters.getIsrequired());
		gatewaySettings.setDataFormat(gatewaySettingsParameters.getDataFormat());
		gatewaySettings.setFieldDefaultValue(gatewaySettingsParameters.getFieldDefaultValue());
		gatewaySettings.setMaximumValue(gatewaySettingsParameters.getMaximumValue());
		gatewaySettings.setMinimumValue(gatewaySettingsParameters.getMinimumValue());
		gatewaySettings.setFieldValue(gatewaySettingsParameters.getFieldValue());
		gatewaySettings.setAffectedModules(gatewaySettingsParameters.getAffectedModules());
		gatewaySettings.setGatewayId(gatewaySettingsParameters.getGatewayId());
	}
}
