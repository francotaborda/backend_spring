package com.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entity.Organization;




@Repository
public interface IOrganizationRepository extends JpaRepository<Organization, Long>{

	//OrganizationResponse save(OrganizationRequest organization);
	
	
	
}
