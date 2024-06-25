package com.catering.manager.api.business.controller;


import com.catering.manager.api.business.common.criteria.NomenclatureCriteria;
import com.catering.manager.api.business.payload.NomenclaturePayload;
import com.catering.manager.api.business.payload.global.GlobalPayload;
import com.catering.manager.api.business.service.impl.NomenclatureService;
import com.catering.manager.api.common.exception.CRUDException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.catering.manager.api.business.common.util.BusinessConstants.NOMENCLATURE_CONTROLLER;
import static com.catering.manager.api.common.constant.CommonConstants.*;

@CrossOrigin
@RestController
@RequestMapping(NOMENCLATURE_CONTROLLER)
public class NomenclatureController {

    @Autowired
    private NomenclatureService service;

    @PostMapping(path = ADD_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NomenclaturePayload> add(
            @RequestBody NomenclaturePayload payload
    ) {
        return new ResponseEntity(service.save(payload), HttpStatus.OK);
    }

    @PutMapping(path = UPDATE_EP, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NomenclaturePayload> update(
            @RequestBody NomenclaturePayload payload
    ) {
        try {
            return new ResponseEntity(service.update(payload), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/{nomenclatureID}")
    public ResponseEntity<String> delete(
            @PathVariable Integer nomenclatureID
    ) {
        try {
            return new ResponseEntity(service.deleteById(nomenclatureID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{nomenclatureID}")
    public ResponseEntity<NomenclaturePayload> get(
            @PathVariable Integer nomenclatureID
    ) {
        try {
            return new ResponseEntity(service.findById(nomenclatureID), HttpStatus.OK);
        } catch (CRUDException exception) {
            return new ResponseEntity(exception.getDescription(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = FIND_ALL_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalPayload<NomenclaturePayload>> findAllByBlogPostId() {
        return new ResponseEntity(service.findAll(), HttpStatus.OK);
    }

    @PostMapping(path = FIND_ALL_BY_CRITERIA_EP, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GlobalPayload<NomenclaturePayload>> findAllByBlogPostId(
            @RequestBody NomenclatureCriteria criteria

    ) {
        return new ResponseEntity(service.findAllByCriteria(criteria), HttpStatus.OK);
    }
}