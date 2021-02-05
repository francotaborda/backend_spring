package com.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.TicketHistory;
import com.backend.service.ITicketHistoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1/ticketHistories")
public class TicketHistoryRestController {

    @Autowired
    private ITicketHistoryService ticketHistoryService;

    @GetMapping("/")
    public List<TicketHistory> index() {
        return ticketHistoryService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        TicketHistory ticketHistory = null;
        Map<String, Object> response = new HashMap<>();

        try {
            ticketHistory = ticketHistoryService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar el ticket history en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ticketHistory == null) {
            response.put("mensaje", "El ticket history ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TicketHistory>(ticketHistory, HttpStatus.OK);

    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody TicketHistory ticketHistory, BindingResult result) {

        TicketHistory newTicketHistory = null;
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
            newTicketHistory = ticketHistoryService.save(ticketHistory);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El ticket history has sido creado con éxito");
        response.put("ticket", newTicketHistory);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody TicketHistory ticketHistory, BindingResult result, @PathVariable Long id) {
        TicketHistory ticketHistoryActual = ticketHistoryService.findById(id);
        TicketHistory ticketHistoryUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (ticketHistoryActual == null) {
            response.put("mensaje", "Error: no se pudo editar el ticket history ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        try {
            //Seteo de los datos nuevos
            //ticketHistoryActual.setTicketId(ticketHistory.getTicketId());
            ticketHistoryActual.setSummary(ticketHistory.getSummary());
            ticketHistoryActual.setCreatedBy(ticketHistory.getCreatedBy());
            ticketHistoryActual.setCreatedAt(ticketHistory.getCreatedAt());
            ticketHistoryActual.setUpdatedAt(ticketHistory.getUpdatedAt());
            ticketHistoryActual.setUpdatedBy(ticketHistory.getUpdatedBy());

            ticketHistoryUpdate = ticketHistoryService.save(ticketHistoryActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el ticket history en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El ticket history has sido actualizado con éxito");
        response.put("ticketHistory", ticketHistoryUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id){
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			
//			ticketHistoryService.delete(id);
//			
//		}
//		catch(DataAccessException e) {
//			response.put("mensaje", "Error al eliminar el ticket history en la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje","El ticket se ha eliminado con éxito");
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//	}


}

