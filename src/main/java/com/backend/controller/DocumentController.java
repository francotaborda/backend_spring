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

import com.backend.entity.IDType;
import com.backend.service.IDocumentService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = IDType.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("api/v1/document")
@Api(tags = "document-controller")
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @RequestMapping(value = "/", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Registro de un Documento")
    public ResponseEntity<?> registerDocument(@Valid @RequestBody IDType IDType, BindingResult result) {
        IDType IDTypeNew = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            IDTypeNew = documentService.save(IDType);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El Documento ha sido creado con exito");
        response.put("documento", IDTypeNew);
        return new ResponseEntity<IDType>(IDTypeNew, HttpStatus.CREATED);
    }
    /////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Muestra un Documento")
    public ResponseEntity<?> show(@PathVariable Long id) {
        IDType IDType = null;
        Map<String, Object> response = new HashMap<>();
        try {
            IDType = documentService.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        if (IDType == null) {
            response.put("mensaje", "El Documento Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<IDType>(IDType, HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Muestra una lista de Documento")
    public List<IDType> index() {
        return documentService.findAll();
    }

    ///////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Elimina un IDType")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            documentService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El documento fue eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


    ////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, produces = "application/json")
    @ApiOperation(value = "Actualzia un Documento")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody IDType IDTypeNew) {

        IDType IDType = null;
        IDType IDTypeOld = documentService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (IDTypeOld == null) {
            response.put("mensaje", "La organizacion Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        IDType = documentService.update(id, IDTypeNew);

        return new ResponseEntity<IDType>(IDType, HttpStatus.OK);

    }

}

