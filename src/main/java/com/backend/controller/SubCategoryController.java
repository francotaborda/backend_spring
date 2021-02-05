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

import com.backend.entity.Category;
import com.backend.entity.SubCategory;
import com.backend.models.SubCategoryRequest;
import com.backend.models.SubCategoryResponse;
import com.backend.repository.ICategoryRepository;
import com.backend.repository.ISubCategoryRepository;
import com.backend.service.ISubCategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Respuesta Existosa", response = SubCategory.class),
        @ApiResponse(code = 400, message = "Solicitud incorrecta"),
        @ApiResponse(code = 401, message = "Usted no esta autorizado para ver este recurso"),
        @ApiResponse(code = 404, message = "Recurso no existe"),
        @ApiResponse(code = 500, message = "Error Interno")
})

@RestController
@RequestMapping("api/v1/subcategory")
@Api(tags = "sub-category-controller")
public class SubCategoryController {
    @Autowired
    ISubCategoryService subCategoryService;

    @Autowired
    ISubCategoryRepository subCategoryRepo;

    @Autowired
    ICategoryRepository categoryRepo;


    @PostMapping(value = "/")
    @ApiOperation(value = "Registra una nueva Sub-Categoria")
    public ResponseEntity<?> create(@Valid @RequestBody SubCategoryRequest subCategoryRequest, BindingResult result) {
        SubCategoryResponse subCategoryNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        Category category = categoryRepo.findById(subCategoryRequest.getCategory_id()).orElse(null);
        if (category == null) {
            response.put("mensaje", "no se encontro un categoria con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        try {
            subCategoryNew = subCategoryService.save(subCategoryRequest);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La categoria ha sido creado con exito");
        response.put("Sub Categoria", subCategoryNew);
        return new ResponseEntity<SubCategoryResponse>(subCategoryNew, HttpStatus.CREATED);
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Devuelve una SubCategoria")
    public ResponseEntity<?> show(@PathVariable Long id) {
        SubCategoryResponse subcategory = null;
        Map<String, Object> response = new HashMap<>();
        try {
            subcategory = subCategoryService.findById(id);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (subcategory == null) {
            response.put("mensaje", "La categoria Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SubCategoryResponse>(subcategory, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    @ApiOperation(value = "Actualiza una Sub-Categoria")
    public ResponseEntity<?> updateSubCategory(@PathVariable("id") Long id, @RequestBody SubCategoryRequest subCategoryRequest) {
        SubCategoryResponse subCategory = null;
        SubCategory subCategorySummary = subCategoryRepo.findById(id).orElse(null);
        Map<String, Object> response = new HashMap<>();
        if (subCategorySummary == null) {
            response.put("mensaje", "La subCategoria Id: ".concat(id.toString().concat(" no existe en la base de datos")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        Category category = categoryRepo.findById(subCategoryRequest.getCategory_id()).orElse(null);
        if (category == null) {
            response.put("mensaje", "no se encontro un categoria con esa id");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        subCategory = subCategoryService.updateSubCategory(id, subCategoryRequest);

        return new ResponseEntity<SubCategoryResponse>(subCategory, HttpStatus.OK);
    }

    @GetMapping(value = "/listsub/{category_id}")
    @ApiOperation(value = "Devuelve todas subcategorias de una categoria")
    public ResponseEntity<List<SubCategoryResponse>> get(@PathVariable("category_id") Long category_id) {
        return ResponseEntity.status(HttpStatus.OK).body(subCategoryService.listar(category_id));
    }
}

