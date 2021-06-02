package com.cognizant.ccm.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Gateway Settings Parameters
 * @author Sujay
 *
 */
@Getter
@Setter
@Entity
@Table
public class GatewaySettingsParameters {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gatewaySettingsParameterId;
	private String fieldCode;
	private String fieldName;
	private String fieldDescription;
	private String dataType;
	private Boolean isrequired;
	private String dataFormat;
	private String fieldDefaultValue;
	private String maximumValue;
	private String minimumValue;
	private String fieldValue;
	private String affectedModules;
	private Integer gatewayId;
	@CreationTimestamp
	private Date createdon;
	@UpdateTimestamp
	private Date updatedon;
}
