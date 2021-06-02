package com.cognizant.ccm.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO Class for Gateway Settings Parameters
 * @author Sujay
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class GatewaySettings {
	
	private int gatewaySettingsParameterId;
	@NotEmpty(message = "{field.code.required}")
	@Size(min = 2, max = 10, message = "{field.code.size}")
	private String fieldCode;
	@NotEmpty(message = "field.name.required")
	@Size(min = 2, max = 20, message = "{field.name.size}")
	private String fieldName;
	@NotEmpty(message = "{field.desc.required}")
	@Size(min = 2, max = 200, message = "{field.desc.size}")
	private String fieldDescription;
	@NotEmpty(message = "{data.type.required}")
	@Size(min = 2, max = 10, message = "{data.type.size}")
	private String dataType;
	@NotNull(message = "{isrequired.required}")
	private Boolean isrequired;
	@NotEmpty(message = "{data.format.required}")
	@Size(min = 2, max = 50, message = "{data.format.size}")
	private String dataFormat;
	@NotEmpty(message = "{field.default.value.required}")
	@Size(min = 2, max = 50, message = "{field.default.value.size}")
	private String fieldDefaultValue;
	@NotEmpty(message = "{maximum.value.required}")
	@Size(min = 2, max = 50, message = "{maximum.value.size}")
	private String maximumValue;
	@NotEmpty(message = "{minimum.value.required}")
	@Size(min = 2, max = 50, message = "{minimum.value.size}")
	private String minimumValue;
	@NotEmpty(message = "{field.value.required}")
	@Size(min = 2, max = 50, message = "{field.value.size}")
	private String fieldValue;
	@NotEmpty(message = "{affected.modules.required}")
	@Size(min = 2, max = 200, message = "{affected.modules.size}")
	private String affectedModules;
	@NotNull(message = "{gateway.id.required}")
	private Integer gatewayId;
	private Date createdon;
	private Date updatedon;
	
}
