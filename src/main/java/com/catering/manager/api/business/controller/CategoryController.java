package com.catering.manager.api.business.controller;


import com.catering.manager.api.business.common.criteria.CategoryCriteria;
import com.catering.manager.api.business.payload.CategoryPayload;
import com.catering.manager.api.business.service.inter.ICategoryService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.catering.manager.api.business.common.util.BusinessConstants.CATEGORY_CONTROLLER;
import static com.catering.manager.api.common.constant.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping(CATEGORY_CONTROLLER)
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @PostMapping(path = ADD_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryPayload> add(
            @RequestBody CategoryPayload payload
    ) {
        return new ResponseEntity(service.save(payload), HttpStatus.OK);
    }

    @PutMapping(path = UPDATE_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryPayload> update(
            @RequestBody CategoryPayload payload
    ) {
        return new ResponseEntity(service.update(payload), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{categoryID}")
    public ResponseEntity<String> delete(
            @PathVariable Integer categoryID
    ) {
        try {
            return new ResponseEntity(service.deleteById(categoryID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{categoryID}")
    public ResponseEntity<CategoryPayload> get(
            @PathVariable Integer categoryID
    ) {
        try {
            return new ResponseEntity(service.findById(categoryID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = FIND_ALL_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryPayload>> findAllByBlogPostId() {
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = FIND_ALL_BY_CRITERIA_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryPayload>> findAllByBlogPostId(
            @RequestBody CategoryCriteria criteria

    ) {
        return new ResponseEntity(service.findAllByCriteria(criteria), HttpStatus.OK);
    }
}
