package com.catering.manager.api.business.controller;


import com.catering.manager.api.business.common.criteria.ProductTypeCriteria;
import com.catering.manager.api.business.payload.ProductTypePayload;
import com.catering.manager.api.business.service.inter.IProductTypeService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.catering.manager.api.business.common.util.BusinessConstants.PRODUCT_TYPE_CONTROLLER;
import static com.catering.manager.api.common.constant.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping(PRODUCT_TYPE_CONTROLLER)
public class ProductTypeController {

    @Autowired
    private IProductTypeService service;

    @PostMapping(path = ADD_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductTypePayload> add(
            @RequestBody ProductTypePayload payload
    ) {
        return new ResponseEntity(service.save(payload), HttpStatus.OK);
    }

    @PutMapping(path = UPDATE_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductTypePayload> update(
            @RequestBody ProductTypePayload payload
    ) {
        return new ResponseEntity(service.update(payload), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{productTypeID}")
    public ResponseEntity<String> delete(
            @PathVariable Integer productTypeID
    ) {
        try {
            return new ResponseEntity(service.deleteById(productTypeID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{productTypeID}")
    public ResponseEntity<ProductTypePayload> get(
            @PathVariable Integer productTypeID
    ) {
        try {
            return new ResponseEntity(service.findById(productTypeID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = FIND_ALL_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductTypePayload>> findAllByBlogPostId() {
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = FIND_ALL_BY_CRITERIA_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductTypePayload>> findAllByBlogPostId(
            @RequestBody ProductTypeCriteria criteria

    ) {
        return new ResponseEntity(service.findAllByCriteria(criteria), HttpStatus.OK);
    }
}
