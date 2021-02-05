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

import com.backend.entity.Role;
import com.backend.service.IRoleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Role.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin(origins = {"http://localhost:4200"})
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/")
    public List<Role> index() {
        return roleService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<Role> index(@PathVariable Integer page) {
        return roleService.findAll(PageRequest.of(page, 4));
    }


    @GetMapping("/{id}")
    public HttpEntity<?> show(@PathVariable Long id) {
        Role role = null;
        Map<String, Object> response = new HashMap<>();

        try {
            role = roleService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar consulta en DB");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (role == null) {
            response.put("mensaje", "El role ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Role>(role, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Role role, BindingResult result) {

        Role roleNew = null;
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
            roleNew = roleService.save(role);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El role ha sido creado con éxito!");
        response.put("role", roleNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Role role, BindingResult result, @PathVariable Long id) {

        Role roleActual = roleService.findById(id);

        Role roleUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (roleActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el role ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            roleActual.setName(role.getName());
            roleActual.setDescription(role.getDescription());
            roleActual.setReportToRole(role.getReportToRole());
            roleUpdated = roleService.save(roleActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el role en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El role ha sido actualizado con éxito!");
        response.put("role", roleUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<String, Object>();
        Role role = null;
        try {
            roleService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar role de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El role se ha sido eliminado con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


}
