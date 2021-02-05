package com.backend.service;


import java.util.List;

import com.backend.models.OrganizationRequest;
import com.backend.models.OrganizationResponse;


public interface IOrganizationService {

    List<OrganizationResponse> findAll();

    OrganizationResponse getOrganization(Long id);

    OrganizationResponse save(OrganizationRequest orgRequest);

    OrganizationResponse updateOrganization(Long id, OrganizationRequest orgRequest);

    void delete(Long id);

    void isActive(Long id);

    List<OrganizationResponse> findByCompanyId(Long company_id);


}
