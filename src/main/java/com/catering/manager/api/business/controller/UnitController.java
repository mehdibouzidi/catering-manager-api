package com.catering.manager.api.business.controller;


import com.catering.manager.api.business.common.criteria.UnitCriteria;
import com.catering.manager.api.business.payload.UnitPayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.service.inter.IUnitService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.catering.manager.api.business.common.util.BusinessConstants.UNIT_CONTROLLER;
import static com.catering.manager.api.common.constant.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping(UNIT_CONTROLLER)
public class UnitController {

    @Autowired
    private IUnitService service;

    @PostMapping(path = ADD_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnitPayload> add(
            @RequestBody UnitPayload payload
    ) {
        return new ResponseEntity(service.save(payload), HttpStatus.OK);
    }

    @PutMapping(path = UPDATE_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UnitPayload> update(
            @RequestBody UnitPayload payload
    ) {
        return new ResponseEntity(service.update(payload), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{unitID}")
    public ResponseEntity<String> delete(
            @PathVariable Integer unitID
    ) {
        try {
            return new ResponseEntity(service.deleteById(unitID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{unitID}")
    public ResponseEntity<UnitPayload> get(
            @PathVariable Integer unitID
    ) {
        try {
            return new ResponseEntity(service.findById(unitID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = FIND_ALL_BY_CRITERIA_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalPayload<UnitPayload>> findAllByBlogPostId(
            @RequestBody UnitCriteria criteria

    ) {
        return new ResponseEntity(service.findAllByCriteria(criteria), HttpStatus.OK);
    }
}
