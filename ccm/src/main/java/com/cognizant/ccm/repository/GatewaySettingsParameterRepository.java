package com.cognizant.ccm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.ccm.model.GatewaySettingsParameters;

/**
 * Interface to perform CRUD Operations
 * @author Sujay
 *
 */
public interface GatewaySettingsParameterRepository extends JpaRepository<GatewaySettingsParameters, Integer> {
	
}
