package com.backend.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.User;
import com.backend.models.UserResponse;
import com.backend.repository.*;
import com.backend.service.IPermissionService;
import com.backend.service.ITeamService;
import com.backend.service.IUserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = User.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    IUserService userService;
    //    @Autowired Users user;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    ITeamRepository teamDao;
    @Autowired
    IHabilitiesRepository habilitiesDao;
    @Autowired
    IOrganizationRepository organizationRepository;
    @Autowired
    IRoleRepository roleDao;
    @Autowired
    ITeamService teamService;
    @Autowired
    IPermissionService permissionService;
    @Autowired
    IPermissionRepository permissionRepository;
    @PersistenceContext
    EntityManager entityManager;


    /**
     * List All Users
     *
     * @return
     */
    @GetMapping(value = "/", produces = "application/json")
    @ApiOperation(value = "Obtiene el listado de Usuarios")
    public ResponseEntity<List<User>> index() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
    }


    /**
     * Create a new User
     *
     * @param users
     * @return
     */
    @PostMapping(value = "/", produces = "application/json")
    @ApiOperation(value = "Alta de Usuario")
    public ResponseEntity<?> create(@Valid @RequestBody User users) {
        User userNew = null;
        User user = null;
        Map<String, Object> response = new HashMap<>();
        try {
            user = userRepository.save(users);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el registro");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ? e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Se ha creado un nuevo registro!");
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    /**
     * Get a specific User
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Obtiene un usuario especifica")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        UserResponse userResponse = null;

        try {

            return ResponseEntity.status(HttpStatus.OK).body(userRepository.findById(id));
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar consulta");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Update User
     *
     * @param newUser
     * @param id
     * @return
     */
    @PatchMapping(value = "/{id}", produces = "application/json")
    @ApiOperation(value = "Actualiza un Usuario")
    public Object update(@RequestBody User newUser, @PathVariable Long id) {
        User userOld = userRepository.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (newUser.getUpdatedBy() == null) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", "updatedBy is required");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return userRepository.findById(id).map(usuario -> {
            usuario.setFirstName((newUser.getFirstName() == null) ? userOld.getFirstName() : newUser.getFirstName());
            usuario.setLastName((newUser.getLastName() == null) ? userOld.getLastName() : newUser.getLastName());
            usuario.setEmail((newUser.getEmail() == null) ? userOld.getEmail() : newUser.getEmail());
            usuario.setAddress((newUser.getAddress() == null) ? userOld.getAddress() : newUser.getAddress());

            usuario.setPicture((newUser.getPicture() == null) ? userOld.getPicture() : newUser.getPicture());
            usuario.setInternal((newUser.getInternal() == null) ? userOld.getInternal() : newUser.getInternal());
            usuario.setPersonalPhone((newUser.getPersonalPhone() == null) ? userOld.getPersonalPhone() : newUser.getPersonalPhone());

            usuario.setUpdatedBy(newUser.getUpdatedBy());
            usuario.setUpdatedAt(new Date());
            return userRepository.save(usuario);
        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }


    /**
     * Enable / Disable User
     *
     * @param id
     * @return
     */
    @PatchMapping(value = "/active/{id}", produces = "application/json")
    @ApiOperation(value = "Activa o desactiva un Usuario")
    public ResponseEntity<?> isActive(@PathVariable("id") Long id) {
        User user = userRepository.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        try {
            userService.isActive(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ? e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Usuario " + user.getFirstName() + " " + user.getLastName() + " cambio de estado a " + user.getActive());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    /**
     * Change Status
     *
     * @param id
     * @param status_id
     * @return
     */
    @PatchMapping(value = "/status/{id}", produces = "application/json")
    @ApiOperation(value = "Cambia el estado de un Usuario")
    public ResponseEntity<?> changeState(@PathVariable Long id, Long status_id) {
        User user = userRepository.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        try {
            userService.changeStatus(id, status_id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ? e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El Usuario " + user.getFirstName() + " " + user.getLastName() + " cambio de estado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
