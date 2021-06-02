package com.cognizant.ccm.model;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

class GatewaySettingsTest {

	GatewaySettings gatewaySettings = new GatewaySettings();
	
	@Test
	void test_GatewaySettingsParameterId() {
		gatewaySettings.setGatewaySettingsParameterId(1);
		assertEquals(1, gatewaySettings.getGatewaySettingsParameterId());
	}
	
	@Test
	void test_FieldCode() {
		gatewaySettings.setFieldCode("F001");
		assertEquals("F001", gatewaySettings.getFieldCode());
	}
	
	@Test
	void test_FieldName() {
		gatewaySettings.setFieldName("Field Name");
		assertEquals("Field Name", gatewaySettings.getFieldName());
	}
	
	@Test
	void test_FieldDescription() {
		gatewaySettings.setFieldDescription("Field Description");
		assertEquals("Field Description", gatewaySettings.getFieldDescription());
	}
	
	@Test
	void test_DataType() {
		gatewaySettings.setDataType("Data type");
		assertEquals("Data type", gatewaySettings.getDataType());
	}
	
	@Test
	void test_IsRequired() {
		gatewaySettings.setIsrequired(true);
		assertEquals(true, gatewaySettings.getIsrequired());
	}
	
	@Test
	void test_DataFormat() {
		gatewaySettings.setDataFormat("dd/MM/yyyy");;
		assertEquals("dd/MM/yyyy", gatewaySettings.getDataFormat());
	}
	
	@Test
	void test_FieldDefaultValue() {
		gatewaySettings.setFieldDefaultValue("Default Value");
		assertEquals("Default Value", gatewaySettings.getFieldDefaultValue());
	}
	
	@Test
	void test_MaximumValue() {
		gatewaySettings.setMaximumValue("Maximum");
		assertEquals("Maximum", gatewaySettings.getMaximumValue());
	}
	
	@Test
	void test_MinimumValue() {
		gatewaySettings.setMinimumValue("Minimum");
		assertEquals("Minimum", gatewaySettings.getMinimumValue());
	}
	
	@Test
	void test_FieldValue() {
		gatewaySettings.setFieldValue("Field Value");
		assertEquals("Field Value", gatewaySettings.getFieldValue());
	}
	
	@Test
	void test_AffectedModules() {
		gatewaySettings.setAffectedModules("Affected Modules");
		assertEquals("Affected Modules", gatewaySettings.getAffectedModules());
	}
	
	@Test
	void test_GatewayId() {
		gatewaySettings.setGatewayId(1);
		assertEquals(Integer.valueOf(1), gatewaySettings.getGatewayId());
	}
	
	@Test
	void test_CreatedOn() {
		var date = new Date();
		gatewaySettings.setCreatedon(date);
		assertEquals(date, gatewaySettings.getCreatedon());
	}
	
	@Test
	void test_UpdatedOn() {
		var date = new Date();
		gatewaySettings.setUpdatedon(date);
		assertEquals(date, gatewaySettings.getUpdatedon());
	}
}
