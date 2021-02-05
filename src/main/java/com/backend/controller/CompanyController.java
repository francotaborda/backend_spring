package com.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Company;
import com.backend.repository.ICompanyRepository;
import com.backend.service.ICompanyService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Company.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("api/v1/companies")
@Api(tags = "company-controller")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @Autowired
    private ICompanyRepository companyRepo;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Registro de una nueva Empresa")
    public ResponseEntity<?> registerCompany(@Valid @RequestBody Company company, BindingResult result) {
        Company companyNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            companyNew = companyService.save(company);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La empresa ha sido creado con exito");
        response.put("cliente", companyNew);
        return new ResponseEntity<Company>(companyNew, HttpStatus.CREATED);
    }

    /////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Muestra una Empresa")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Company company = null;
        Map<String, Object> response = new HashMap<>();
        try {
            company = companyService.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if (company == null) {
            response.put("mensaje", "La empresa Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Company>(company, HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Muestra una lista de Empresa")
    public List<Company> index() {
        return companyService.findAll();

    }

    ///////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Elimina una Empresa")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            companyService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La empresa fue eliminada con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


    ////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Actualzia una  Empresa")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Company companyNew) {

        Company company = null;
        Company companyOld = companyRepo.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (companyOld == null) {
            response.put("mensaje", "La organizacion Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        company = companyService.update(id, companyNew);

        return new ResponseEntity<Company>(company, HttpStatus.OK);

    }

    ///////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/active/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Activa o desactiva una Empresa")
    //activa o desactiva una empresa
    public ResponseEntity<?> isActive(@PathVariable("id") Long id) {
        Company company = companyRepo.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        try {
            companyService.isActive(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "La empresa " + company.getName() + " cambio de estado a " + company.getActive());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);


    }

}

