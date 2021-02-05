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

import com.backend.entity.Gender;
import com.backend.service.IGendersService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Gender.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("api/v1/genders")
@Api(tags = "genders-controller")
public class GendersController {

    @Autowired
    IGendersService gendersService;
	
/*	@Autowired
    IGendersRepository gendersRepo;*/

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Registro de una nueva Empresa")
    public ResponseEntity<?> registerGenders(@Valid @RequestBody Gender gender, BindingResult result) {
        Gender genderNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            genderNew = gendersService.save(gender);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El genero ha sido creado con exito");
        response.put("cliente", genderNew);
        return new ResponseEntity<Gender>(genderNew, HttpStatus.CREATED);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Muestra un Genero")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Gender gender = null;
        Map<String, Object> response = new HashMap<>();
        try {
            gender = gendersService.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if (gender == null) {
            response.put("mensaje", "El genero Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Gender>(gender, HttpStatus.OK);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Elimina un Genero")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            gendersService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El genero fue eliminada con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Actualzia un Genero")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Gender genderNew) {

        Gender gender = null;
        Gender genderOld = gendersService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (genderOld == null) {
            response.put("mensaje", "El genero Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        gender = gendersService.update(id, genderNew);

        return new ResponseEntity<Gender>(gender, HttpStatus.OK);

    }

    ///////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/active/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Activa o desactiva un Genero")
    //activa o desactiva un Genero
    public ResponseEntity<?> isActive(@PathVariable("id") Long id) {
        Gender gender = gendersService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (gender == null) {
            response.put("mensaje", "No existe ningun generero para la id: " + id);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

        }
        try {
            gendersService.isActive(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El genero " + gender.getSummary() + " cambio de estado a " + gender.getActive());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);


    }


}
