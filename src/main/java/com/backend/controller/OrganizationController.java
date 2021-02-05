package com.backend.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Company;
import com.backend.entity.Organization;
import com.backend.models.OrganizationRequest;
import com.backend.models.OrganizationResponse;
import com.backend.repository.ICompanyRepository;
import com.backend.repository.IOrganizationRepository;
import com.backend.service.IOrganizationService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Organization.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("api/v1/organizations")
public class OrganizationController {

    @Autowired
    IOrganizationService orgService;

    @Autowired
    IOrganizationRepository orgRepository;

    @Autowired
    ICompanyRepository compRepository;

    @PersistenceContext
    EntityManager entityManager;

    //////////////////////////// CONTROLADOR QUE CREAR UNA ORGANIZACION/////////////////////////////////////////////////////////
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Registro de una nueva organizacion")
    public ResponseEntity<?> create(@Valid @RequestBody OrganizationRequest organizationRequest) {
        OrganizationResponse orgSummary = null;
        Map<String, Object> response = new HashMap<>();
        Company company = compRepository.findById(organizationRequest.getCompany_id()).orElse(null);
        if (company == null) {
            response.put("mensaje", "no se encontro una Compañia con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            orgSummary = orgService.save(organizationRequest);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<OrganizationResponse>(orgSummary, HttpStatus.CREATED);

    }

    /////////////////////////////////// CONTROLADOR QUE DEVUELVE UNA SOLA ORGANIZACION/////////////////////////////////////////////

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Obtiene una organizacion")
    public ResponseEntity<?> getOrganization(@PathVariable("id") Long id) {
        OrganizationResponse organizationResponse = orgService.getOrganization(id);
        return new ResponseEntity<OrganizationResponse>(organizationResponse, HttpStatus.OK);
    }

    //////////////////////////////////////////Actualizar Organizacione///////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Actualiza una organizacion")
    public ResponseEntity<?> updateOrganization(@PathVariable("id") Long id, @RequestBody OrganizationRequest orgRequest) {
        Organization organization = orgRepository.findById(id).orElse(null);
        OrganizationResponse orgSummary = null;
        Company company = compRepository.findById(orgRequest.getCompany_id()).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (organization == null) {
            response.put("mensaje", "La organizacion Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (company == null) {
            response.put("mensaje", "no se encontro una Compañia con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        orgSummary = orgService.updateOrganization(id, orgRequest);

        return new ResponseEntity<OrganizationResponse>(orgSummary, HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////Borra una Organizacion//////////////////////////////////
    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Borra organizacion")
    public ResponseEntity<?> deleteOrganization(@PathVariable("id") Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            orgService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La Organizacion fue eliminada con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Obtiene todas las organizaciones")
    public List<OrganizationResponse> getOrganizations() {
        return orgService.findAll();
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/active/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Activa o desactiva una Empresa")
    //activa o desactiva una Organizacion
    public ResponseEntity<?> isActive(@PathVariable("id") Long id) {
        Organization organization = orgRepository.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        try {
            orgService.isActive(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La organizacion  " + organization.getOrgname() + " cambio de estado a " + organization.getActive());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/listcompany/{company_id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Obtiene todas las organizaciones dentro de una compañia ")
    public ResponseEntity<List<OrganizationResponse>> getOrganizationsWithinACompany(@PathVariable("company_id") Long company_id) {

        return ResponseEntity.status(HttpStatus.OK).body(orgService.findByCompanyId(company_id));

    }

}





