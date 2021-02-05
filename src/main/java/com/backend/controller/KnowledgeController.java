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

import com.backend.entity.Knowledge;
import com.backend.service.IKnowledgeService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Knowledge.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/knowledges")
@CrossOrigin(origins = {"http://localhost:4200"})
public class KnowledgeController {

    @Autowired
    private IKnowledgeService knowledgeService;

    @GetMapping("/")
    public List<Knowledge> index() {
        return knowledgeService.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<Knowledge> index(@PathVariable Integer page) {
        return knowledgeService.findAll(PageRequest.of(page, 4));
    }


    @GetMapping("/{id}")
    //@ResponseStatus(HttpStatus.OK) 200
    public HttpEntity<?> show(@PathVariable Long id) {
        Knowledge knowledge = null;
        Map<String, Object> response = new HashMap<>();

        try {
            knowledge = knowledgeService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar consulta en DB");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (knowledge == null) {
            response.put("mensaje", "El knowledge ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Knowledge>(knowledge, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody Knowledge knowledge, BindingResult result) {

        Knowledge knowledgeNew = null;
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
            knowledgeNew = knowledgeService.save(knowledge);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El knowledge ha sido creado con éxito!");
        response.put("knowledge", knowledgeNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Knowledge knowledge, BindingResult result, @PathVariable Long id) {

        Knowledge knowledgeActual = knowledgeService.findById(id);

        Knowledge knowledgeUpdated = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (knowledgeActual == null) {
            response.put("mensaje", "Error: no se pudo editar, el knowledge ID: "
                    .concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {

            knowledgeActual.setTitle(knowledge.getTitle());
            knowledgeActual.setSummary(knowledge.getSummary());
            knowledgeActual.setFiles(knowledge.getFiles());
            knowledgeUpdated = knowledgeService.save(knowledgeActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el knowledge en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El knowledge ha sido actualizado con éxito!");
        response.put("knowledge", knowledgeUpdated);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<String, Object>();
        Knowledge knowledge = null;
        try {
            knowledgeService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar knowledge de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El knowledge se ha sido eliminado con exito!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


}
