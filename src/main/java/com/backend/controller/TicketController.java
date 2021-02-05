package com.backend.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Category;
import com.backend.entity.Priority;
import com.backend.entity.Stage;
import com.backend.entity.Ticket;
import com.backend.models.ClientResponse;
import com.backend.service.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses({
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = Ticket.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private ITicketService ticketService;

    @Autowired
    private IPrioritiesService prioritiesService;

    @Autowired
    private IClientService clientService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IStageService stageService;

    @GetMapping("/")
    public List<Ticket> index() {
        return ticketService.findAll();
    }

    ///////////////////////////////////////////////////////////////////////////
    // Muentra una entidad Tickek
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Ticket ticket = null;
        Map<String, Object> response = new HashMap<>();

        try {
            ticket = ticketService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al consultar el ticket en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (ticket == null) {
            response.put("mensaje", "El ticket ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);

    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Ticket ticket, BindingResult result) {

        Ticket newTicket = null;
        ClientResponse client = clientService.findById(ticket.getClientId().getId());
        Priority priority = prioritiesService.findById(ticket.getPriorityId().getId());
        Stage stage = stageService.findById(ticket.getStage().getId());
        Map<String, Object> response = new HashMap<>();

        if (client == null) {
            response.put("mensaje", "no se encontro un Cliente con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (priority == null) {
            response.put("mensaje", "no se encontro una priodidad con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (stage == null) {
            response.put("mensaje", "no se encontro una state con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            newTicket = ticketService.save(ticket);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de insert en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El ticket has sido creado con éxito");
        response.put("ticket", newTicket);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTicket);

    }

    //////////////////////////////////////////////////////////////////////////////////////

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Ticket ticket, BindingResult result, @PathVariable Long id) {
        Ticket ticketActual = ticketService.findById(id);
        Priority priority = prioritiesService.findById(ticket.getPriorityId().getId());
        ClientResponse client = clientService.findById(ticket.getClientId().getId());
        Stage stage = stageService.findById(ticket.getStage().getId());

        Ticket ticketUpdate = null;
        Date fecha = new Date();

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {

            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        if (ticketActual == null) {
            response.put("mensaje", "Error: no se pudo editar el ticket ID: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        if (priority == null) {
            response.put("mensaje", "no se encontro una priodidad con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (client == null) {
            response.put("mensaje", "no se encontro un Cliente con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (stage == null) {
            response.put("mensaje", "no se encontro una priodidad con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            //Seteo de los datos nuevos
            ticketActual.setTitle(ticket.getTitle());
            ticketActual.setSummary(ticket.getSummary());
            ticketActual.setStage(ticket.getStage());
            ticketActual.setUserid(ticket.getUserid());
            ticketActual.setClientId(ticket.getClientId());

            //fecha de vencimiento del ticket
            ticketActual.setExpirationDate(ticket.getExpirationDate());
            ticketActual.setTicketHistoryId(ticket.getTicketHistoryId());
            ticketActual.setAssociatedWith(ticket.getAssociatedWith());
            ticketActual.setPriorityId(ticket.getPriorityId());
            ticketActual.setFiles(ticket.getFiles());
            ticketActual.setContactName(ticket.getContactName());
            ticketActual.setReportedBy(ticket.getReportedBy());
            ticketActual.setCreatedBy(ticket.getCreatedBy());
            ticketActual.setCreatedAt(ticket.getCreatedAt());
            ticketActual.setUpdatedAt(fecha);

            ticketUpdate = ticketService.save(ticketActual);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al insertar el registro");
            response.put("error", (e.getMostSpecificCause().getMessage().contains("Detail:")) ?
                    e.getMostSpecificCause().getMessage().substring(e.getMostSpecificCause().getMessage().indexOf("Detail:") + 7) : e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put("mensaje", "El ticket has sido actualizado con éxito");
        response.put("ticket", ticketUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {

            ticketService.delete(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el ticket en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El ticket se ha eliminado con éxito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }


    @GetMapping("/list-by-category/{id}")
    public ResponseEntity<?> getTicketsByCategory(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        List<Ticket> tickets = null;
        Map<String, Object> response = new HashMap<>();


        if (category == null) {
            response.put("mensaje", "Error al  consultar categoria id en base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            tickets = ticketService.findByCategoriesEquals(category);
        } catch (DataAccessException e) {
            response.put("mensaje", "No existen tickets");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);

    }


}

