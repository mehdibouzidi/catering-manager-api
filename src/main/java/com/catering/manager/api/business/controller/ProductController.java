package com.catering.manager.api.business.controller;


import com.catering.manager.api.business.common.criteria.ProductCriteria;
import com.catering.manager.api.business.payload.ProductPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.service.inter.ProductService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.catering.manager.api.business.common.util.BusinessConstants.PRODUCT_CONTROLLER;
import static com.catering.manager.api.common.constant.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping(PRODUCT_CONTROLLER)
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(path = ADD_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPayload> add(
            @RequestBody ProductPayload payload
    ) {
        return new ResponseEntity(service.save(payload), HttpStatus.OK);
    }

    @PutMapping(path = UPDATE_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductPayload> update(
            @RequestBody ProductPayload payload
    ) {
        try {
            return new ResponseEntity(service.update(payload), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{productID}")
    public ResponseEntity<String> delete(
            @PathVariable Integer productID
    ) {
        try {
            return new ResponseEntity(service.deleteById(productID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{productID}")
    public ResponseEntity<ProductPayload> get(
            @PathVariable Integer productID
    ) {
        try {
            return new ResponseEntity(service.findById(productID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = FIND_ALL_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalPayload<ProductPayload>> findAllByBlogPostId() {
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = FIND_ALL_BY_CRITERIA_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalPayload<ProductPayload>> findAllByBlogPostId(
            @RequestBody ProductCriteria criteria

    ) {
        return new ResponseEntity(service.findAllByCriteria(criteria), HttpStatus.OK);
    }
}
