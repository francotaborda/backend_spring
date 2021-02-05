package com.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.entity.Company;
import com.backend.entity.Organization;
import com.backend.models.OrganizationRequest;
import com.backend.models.OrganizationResponse;
import com.backend.repository.ICompanyRepository;
import com.backend.repository.IOrganizationRepository;
import com.backend.service.IOrganizationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrganizationServiceImp implements IOrganizationService {

    @Autowired
    IOrganizationRepository orgRepository;
    @Autowired
    IOrganizationService organizations;

    @Autowired
    ICompanyRepository compRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Transactional(readOnly = true)
    public OrganizationResponse getOrganization(Long id) {
        Organization org = orgRepository.findById(id).orElse(null);

        return new OrganizationResponse(org.getId(), org.getOrgname(), org.getActive(), org.getCompany_id().getId(), org.getCreated_at(), org.getUpdated_at());
    }

    @Transactional
    public OrganizationResponse save(OrganizationRequest orgRequest) {
        Company comp = compRepository.findById(orgRequest.getCompany_id()).orElse(null);

        Organization org = new Organization();
        org.setOrgname(orgRequest.getOrgname());
        org.setActive(orgRequest.getActive());
        org.setCompany_id(comp);
        Organization result = orgRepository.save(org);
        return new OrganizationResponse(result.getId(), result.getOrgname(), result.getActive(), result.getCompany_id().getId(),
                result.getCreated_at(), result.getUpdated_at());
    }

    @Transactional
    public void delete(Long id) {
        orgRepository.deleteById(id);

    }

    @Transactional
    public OrganizationResponse updateOrganization(Long id, OrganizationRequest orgRequest) {
        Organization org = orgRepository.findById(id).orElse(null);

        Date fecha = new Date();

        Company comp = compRepository.findById(orgRequest.getCompany_id()).orElse(null);

        org.setOrgname(orgRequest.getOrgname());
        org.setActive(orgRequest.getActive());
        org.setCompany_id(comp);
        org.setUpdated_at(fecha);
        org.setCreated_at(org.getCreated_at());
        Organization result = orgRepository.save(org);
        return new OrganizationResponse(result.getId(), result.getOrgname(), result.getActive(), result.getCompany_id().getId(),
                result.getCreated_at(), result.getUpdated_at());


    }

    @Transactional(readOnly = true)
    public List<OrganizationResponse> findAll() {
        List<Organization> org = orgRepository.findAll();
        List<OrganizationResponse> res = new ArrayList<>();
        for (Organization orgAll : org) {
            OrganizationResponse orgR = new OrganizationResponse();
            EntyToModel(orgAll, orgR);
            res.add(orgR);
        }
        return res;
    }

    private void EntyToModel(Organization org, OrganizationResponse orgResp) {
        orgResp.setId(org.getId());
        orgResp.setOrgname(org.getOrgname());
        orgResp.setActive(org.getActive());
        orgResp.setCompanyid(org.getCompany_id().getId());
        orgResp.setCreate_at(org.getCreated_at());
        orgResp.setUpdated_at(org.getUpdated_at());

    }

    @Override
    @Transactional
    public void isActive(Long id) {
        Organization organization = orgRepository.findById(id).orElse(null);
        organization.setActive(!organization.getActive());
    }

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    @Transactional(readOnly = true)
    public List<OrganizationResponse> findByCompanyId(Long company_id) {
        return jdbcTemplate.query("SELECT organizations.\"id\", organizations.orgname, organizations.active, organizations.company_id, organizations.created_at, organizations.updated_at\r\n" +
                        "FROM organizations where organizations.company_id= " + company_id + " GROUP BY \r\n" +
                        "organizations.\"id\", organizations.orgname, organizations.active, organizations.company_id, organizations.created_at, organizations.updated_at",
                (rs, rowNum) -> new OrganizationResponse(rs.getLong("id"), rs.getString("orgname"), rs.getBoolean("active"), rs.getLong("company_id"), rs.getDate("created_at"), rs.getDate("updated_at")));
    }

}
