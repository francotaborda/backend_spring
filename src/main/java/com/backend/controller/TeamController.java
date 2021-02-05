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

import com.backend.entity.Team;
import com.backend.service.ITeamService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Team.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/teams")
public class TeamController {

    @Autowired
    ITeamService teamService;

    /**
     * List Teams
     *
     * @return
     */
    @GetMapping(value = "/", produces = "application/json")
    @ApiOperation(value = "Obtiene el listado de Equipos")
    public ResponseEntity<List<Team>> index() {
        return ResponseEntity.status(HttpStatus.OK).body(teamService.findAll());
    }

    /**
     * List Teams per page
     *
     * @param page
     * @return
     */
    @GetMapping(value = "/page/{page}", produces = "application/json")
    @ApiOperation(value = "Obtiene el listado de Equipoes de manera paginada")
    public List<Team> index(@PathVariable Integer page) {
        return teamService.findAll();
    }


    /**
     * Create a new Team
     *
     * @param team
     * @param result
     * @return
     */
    @PostMapping(value = "/", produces = "application/json")
    @ApiOperation(value = "Creacion de una nueva Habilidad")
    public ResponseEntity<?> create(@Valid @RequestBody Team team, BindingResult result) {

        Team teamNew = null;
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
            teamNew = teamService.save(team);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el registro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Se ha creado un nuevo registro!");
        response.put("team", teamNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    /**
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Obtiene un Equipo en especific")
    public HttpEntity<?> show(@PathVariable Long id) {
        Team team = null;
        Map<String, Object> response = new HashMap<>();

        try {
            team = teamService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar consulta");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (team == null) {
            response.put("mensaje", "La Equipo: ".concat(id.toString()).concat(" no existe"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Team>(team, HttpStatus.OK);

    }


    /**
     * Update Hability
     *
     * @param team
     * @param result
     * @param id
     * @return
     */
    @PatchMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Actualiza una Habilidad")
    public ResponseEntity<?> update(@Valid @RequestBody Team team, BindingResult result, @PathVariable Long id) {

        Team teamActual = teamService.findById(id);

        Team teamUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (teamActual == null) {
            response.put("mensaje", "Error: no se pudo editar: "
                    .concat(id.toString().concat(" el registro no existe!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            teamActual.setName(team.getName());
            teamUpdated = teamService.save(teamActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el registro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Actualizacion exitosa!");
        response.put("team", teamUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Borra una Equipo")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<String, Object>();
        Team team = null;
        try {
            teamService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el registro");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "eliminacion exitosa!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }
}
