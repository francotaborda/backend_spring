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

import com.backend.entity.Client;
import com.backend.entity.Gender;
import com.backend.models.ClientRequest;
import com.backend.models.ClientResponse;
import com.backend.service.IClientService;
import com.backend.service.IGendersService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Client.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("api/v1/client")
@Api(tags = "client-controller")
public class ClientController {

    @Autowired
    IClientService clientService;

    @Autowired
    IGendersService gendersService;


    ///////////////////////Crea un cliente/////////////////////////////

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Registro un nuevo Cliente")
    public ResponseEntity<?> registerClient(@Valid @RequestBody ClientRequest clientRequest, BindingResult result) throws DataAccessException {
        ClientResponse clientResponse;
        Gender gender = gendersService.findById(clientRequest.getGender_id());
        Map<String, Object> response = new HashMap<>();
        if (gender == null) {
            response.put("mensaje", "no se encontro un genero con ese id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            clientResponse = clientService.save(clientRequest);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ? e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente ha sido creado con exito");
        response.put("cliente", clientResponse);
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.CREATED);
    }
    ///////////////////////////////////Muestra un cliente por su id/////////////////////////

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Muestra un Cliente")
    public ResponseEntity<?> getClient(@PathVariable Long id) {
        ClientResponse clientResponse = null;
        Map<String, Object> response = new HashMap<>();
        try {
            clientResponse = clientService.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ? e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if (clientResponse == null) {
            response.put("mensaje", "El cliente Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.OK);

    }
    /////////////////////////////////////lista de clientes////////////////////////////

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Muestra lista de Cliente")
    public List<ClientResponse> getClients() {
        return clientService.findAll();
    }

    ///////////////////////////////////Borra un cliente/////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Borra un Cliente")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ? e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El cliente eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    ///////////////////////////Actualiza un Cliente/////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Actualiza un Cliente")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        ClientResponse clientResponse = null;
        Client client = clientService.findByIdClient(id);
        Gender gender = gendersService.findById(clientRequest.getGender_id());
        Map<String, Object> response = new HashMap<>();
        if (client == null) {
            response.put("mensaje", "Error: no se pudo editar .El cliente Id: no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        if (gender == null) {
            response.put("mensaje", "no se encontro un Genero con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            clientResponse = clientService.update(id, clientRequest);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ? e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje", "El cliente ha sido creado con exito");
        response.put("cliente", clientResponse);
        return new ResponseEntity<ClientResponse>(clientResponse, HttpStatus.OK);
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/listgender/{genders_id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Obtiene todolos clientes segun la id del genero ")
    public ResponseEntity<List<ClientResponse>> getClientsGen(@PathVariable("genders_id") Long genders_id) {

        return ResponseEntity.status(HttpStatus.OK).body(clientService.listarGenders(genders_id));

    }
    //////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/listactive/{active}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Obtiene todo los clientes activos o inactivos")
    public ResponseEntity<List<ClientResponse>> getClientsActive(@PathVariable("active") Boolean active) {

        return ResponseEntity.status(HttpStatus.OK).body(clientService.listaClientActive(active));
    }

    ////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/active/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Activa o desactiva un Cliente")
    public ResponseEntity<?> isActive(@PathVariable("id") Long id) {
        Client client = clientService.findByIdClient(id);
        Map<String, Object> response = new HashMap<>();
        try {
            clientService.isActive(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El Cliente " + client.getFirstName() + " " + client.getLastName() + " cambio de estado de actividad a " + client.getActive());
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
