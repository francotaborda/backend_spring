package com.backend.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Habilities;
import com.backend.service.IHabilitiesService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Habilities.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/habilities")
public class HabilitiesController {

    @Autowired
    IHabilitiesService habilitiesService;


    /**
     * List All Habilities
     *
     * @return
     */
    @GetMapping(value = "/", produces = "application/json")
    @ApiOperation(value = "Obtiene el listado de habilidades")
    public ResponseEntity<List<Habilities>> index() {
        return ResponseEntity.status(HttpStatus.OK).body(habilitiesService.findAll());
    }


    /**
     * Create a new Hability
     *
     * @param habilities
     * @param result
     * @return
     */
    @PostMapping(value = "/", produces = "application/json")
    @ApiOperation(value = "Creacion de una nueva Habilidad")
    public ResponseEntity<?> create(@Valid @RequestBody Habilities habilities, BindingResult result) {

        Habilities habilitiesNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            habilitiesNew = habilitiesService.save(habilities);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el registro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Se ha creado un nuevo registro!");
        response.put("habilities", habilitiesNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    /**
     * Get a specific Hability
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Obtiene una habilidad especifica")
    public HttpEntity<?> show(@PathVariable Long id) {
        Habilities habilities = null;
        Map<String, Object> response = new HashMap<>();

        try {
            habilities = habilitiesService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar consulta");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (habilities == null) {
            response.put("mensaje", "La habilidad: ".concat(id.toString()).concat(" no existe"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Habilities>(habilities, HttpStatus.OK);

    }

    /**
     * Update Hability
     *
     * @param habilities
     * @param result
     * @param id
     * @return
     */
    @PatchMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Actualiza una Habilidad")
    public ResponseEntity<?> update(@Valid @RequestBody Habilities habilities, BindingResult result, @PathVariable Long id) {

        Habilities habilitiesActual = habilitiesService.findById(id);

        Habilities habilitiesUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (habilitiesActual == null) {
            response.put("mensaje", "Error: no se pudo editar: "
                    .concat(id.toString().concat(" el registro no existe!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            habilitiesActual.setName(habilities.getName());
            habilitiesActual.setExperience(habilities.getExperience());
            habilitiesUpdated = habilitiesService.save(habilitiesActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Actualizacion exitosa!");
        response.put("habilities", habilitiesUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Borra una habilidad")

    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<String, Object>();
        Habilities habilities = null;
        try {
            habilitiesService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el registro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "eliminacion exitosa!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


}
