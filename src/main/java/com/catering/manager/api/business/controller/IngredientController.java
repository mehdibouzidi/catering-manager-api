package com.catering.manager.api.business.controller;


import com.catering.manager.api.business.common.criteria.IngredientCriteria;
import com.catering.manager.api.business.payload.IngredientPayload;
import com.catering.manager.api.business.service.inter.IIngredientService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.catering.manager.api.business.common.util.BusinessConstants.INGREDIENT_CONTROLLER;
import static com.catering.manager.api.common.constant.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping(INGREDIENT_CONTROLLER)
public class IngredientController {

    @Autowired
    private IIngredientService service;

    @PostMapping(path = ADD_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientPayload> add(
            @RequestBody IngredientPayload payload
    ) {
        return new ResponseEntity(service.save(payload), HttpStatus.OK);
    }

    @PutMapping(path = UPDATE_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IngredientPayload> update(
            @RequestBody IngredientPayload payload
    ) {
        try {
            return new ResponseEntity(service.update(payload), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{ingredientID}")
    public ResponseEntity<String> delete(
            @PathVariable Integer ingredientID
    ) {
        try {
            return new ResponseEntity(service.deleteById(ingredientID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{ingredientID}")
    public ResponseEntity<IngredientPayload> get(
            @PathVariable Integer ingredientID
    ) {
        try {
            return new ResponseEntity(service.findById(ingredientID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = FIND_ALL_BY_CRITERIA_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<IngredientPayload>> findAllByBlogPostId(
            @RequestBody IngredientCriteria criteria

    ) {
        return new ResponseEntity(service.findAllByCriteria(criteria), HttpStatus.OK);
    }
}
