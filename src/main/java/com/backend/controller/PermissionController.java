package com.backend.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Permission;
import com.backend.service.IPermissionService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Permission.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})
@RestController
@RequestMapping("api/v1/permissions")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @GetMapping("/")
    public List<Permission> index() {
        return permissionService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Permission permission = null;
        Map<String, Object> response = new HashMap<>();

        try {
            permission = permissionService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar el permiso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (permission == null) {
            response.put("mensaje", "El permiso ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Permission>(permission, HttpStatus.OK);

    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Permission permission, BindingResult result) {

        Permission newPermission = null;
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
            newPermission = permissionService.save(permission);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El permiso has sido creado con éxito");
        response.put("cliente", newPermission);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Permission permission, BindingResult result, @PathVariable Long id) {
        Permission permissionActual = permissionService.findById(id);
        Permission permissionUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (permissionActual == null) {
            response.put("mensaje", "Error: no se pudo editar el permiso ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            permissionActual.setName(permission.getName());
            permissionActual.setCreateAt(permission.getCreateAt());

            permissionUpdate = permissionService.save(permissionActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el permiso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El permiso has sido actualizado con éxito");
        response.put("permiso", permissionUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            //Permission permission = permissionService.findById(id);
            permissionService.delete(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el permiso en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El permiso se ha eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


}

