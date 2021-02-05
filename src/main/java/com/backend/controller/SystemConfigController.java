package com.backend.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.SystemConfig;
import com.backend.service.ISystemConfigService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = SystemConfig.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/systemConfig")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SystemConfigController {

    @Autowired
    private ISystemConfigService systemConfigService;

    @GetMapping("/")
    public List<SystemConfig> index() {
        return systemConfigService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<SystemConfig> index(@PathVariable Integer page) {
        return systemConfigService.findAll(PageRequest.of(page, 4));
    }


    @GetMapping("/{id}")
    public HttpEntity<?> show(@PathVariable Long id) {
        SystemConfig systemConfig = null;
        Map<String, Object> response = new HashMap<>();

        try {
            systemConfig = systemConfigService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar consulta en DB");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (systemConfig == null) {
            response.put("mensaje", "El systemConfig ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SystemConfig>(systemConfig, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody SystemConfig systemConfig, BindingResult result) {

        SystemConfig systemConfigNew = null;
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
            systemConfigNew = systemConfigService.save(systemConfig);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El systemConfig ha sido creado con éxito!");
        response.put("systemConfig", systemConfigNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody SystemConfig systemConfig, BindingResult result, @PathVariable Long id) {

        SystemConfig systemConfigActual = systemConfigService.findById(id);

        SystemConfig systemConfigUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (systemConfigActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el systemConfig ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            systemConfigActual.setConf_name(systemConfig.getConf_name());
            systemConfigActual.setSummary(systemConfig.getSummary());
            systemConfigUpdated = systemConfigService.save(systemConfigActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el systemConfig en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El systemConfig ha sido actualizado con éxito!");
        response.put("systemConfig", systemConfigUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<String, Object>();
        SystemConfig systemConfig = null;
        try {
            systemConfigService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar systemConfig de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El systemConfig se ha sido eliminado con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


}
