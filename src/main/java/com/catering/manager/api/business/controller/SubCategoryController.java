package com.catering.manager.api.business.controller;


import com.catering.manager.api.business.common.criteria.CategoryCriteria;
import com.catering.manager.api.business.payload.CategoryPayload;
import com.catering.manager.api.business.payload.SubCategoryPayload;
import com.catering.manager.api.business.service.inter.ISubCategoryService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.catering.manager.api.business.common.util.BusinessConstants.SUB_CATEGORY_CONTROLLER;
import static com.catering.manager.api.common.constant.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping(SUB_CATEGORY_CONTROLLER)
public class SubCategoryController {

    @Autowired
    private ISubCategoryService service;

    @PostMapping(path = ADD_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCategoryPayload> add(
            @RequestBody SubCategoryPayload payload
    ) {
        try {
            return new ResponseEntity(service.save(payload), HttpStatus.OK);
        }catch(CRUDException exception){
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = UPDATE_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SubCategoryPayload> update(
            @RequestBody SubCategoryPayload payload
    ) {
        return new ResponseEntity(service.update(payload), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{categoryID}")
    public ResponseEntity<String> delete(
            @PathVariable Integer subCategoryID
    ) {
        try {
            return new ResponseEntity(service.deleteById(subCategoryID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{subCategoryID}")
    public ResponseEntity<SubCategoryPayload> get(
            @PathVariable Integer subCategoryID
    ) {
        try {
            return new ResponseEntity(service.findById(subCategoryID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
