package com.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.backend.entity.Category;
import com.backend.service.ICategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@ApiResponses(value = {@ApiResponse(code = 200, message = "Respuesta Existosa", response = Category.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")})

@RestController
@RequestMapping("api/v1/category")
@Api(tags = "category-controller")
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Lista todas las Categorias")
    public List<Category> index() {
        return categoryService.findAll();
    }


    @PostMapping(value = "/")
    @ApiOperation(value = "Registra una nueva Categoria")
    public ResponseEntity<?> create(@Valid @RequestBody Category category, BindingResult result) {
        Category categoryNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            categoryNew = categoryService.save(category);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La categoria ha sido creado con exito");
        response.put("categoria", categoryNew);
        return new ResponseEntity<Category>(categoryNew, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Devuelve una Categoria")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Category category = null;
        Map<String, Object> response = new HashMap<>();
        try {
            category = categoryService.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (category == null) {
            response.put("mensaje", "La categoria Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Actualiza una Categoria")
    public ResponseEntity<?> updateOrganization(@PathVariable("id") Long id, @RequestBody Category categoryNew) {
        Category category = null;
        Category categorySummary = categoryService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (categorySummary == null) {
            response.put("mensaje",
                    "La organizacion Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        category = categoryService.updateCategory(id, categoryNew);

        return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Elimina una Categoria")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            categoryService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "El cliente eliminado con exito");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }
}
