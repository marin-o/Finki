package com.dians.omm.Prototype2.controller;

import com.dians.omm.Prototype2.model.OSMObject;
import com.dians.omm.Prototype2.service.OSMObjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@Validated
@CrossOrigin(origins = "*")
public class OSMObjectController {
    private final OSMObjectService service;

    public OSMObjectController(OSMObjectService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OSMObject>> getAll(){
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<OSMObject>> findById(@PathVariable Long id){
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }
}
