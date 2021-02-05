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

import com.backend.entity.Channel;
import com.backend.service.IChannelService;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Channel.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/channels")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ChannelController {

    private final IChannelService channelService;

    public ChannelController(IChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/")
    public List<Channel> index() {
        return channelService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<Channel> index(@PathVariable Integer page) {
        return channelService.findAll(PageRequest.of(page, 4));
    }


    @GetMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK) 200
    public HttpEntity<?> show(@PathVariable Long id) {
        Channel channel = null;
        Map<String, Object> response = new HashMap<>();

        try {
            channel = channelService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar consulta en DB");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (channel == null) {
            response.put("mensaje", "El channel ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Channel>(channel, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Channel channel, BindingResult result) {

        Channel channelNew = null;
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
            channelNew = channelService.save(channel);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El channel ha sido creado con éxito!");
        response.put("channel", channelNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Channel channel, BindingResult result, @PathVariable Long id) {

        Channel channelActual = channelService.findById(id);

        Channel channelUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (channelActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el channel ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            channelActual.setName(channel.getName());
            channelActual.setConfiguration(channel.getConfiguration());
            channelActual.setUpdateAt(new Date());
            channelUpdated = channelService.save(channelActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el channel en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El channel ha sido actualizado con éxito!");
        response.put("channel", channelUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<String, Object>();
        Channel channel = null;
        try {
            channelService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar channel de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El channel se ha sido eliminado con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


}
