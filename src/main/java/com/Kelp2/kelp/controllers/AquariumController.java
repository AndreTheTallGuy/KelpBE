package com.Kelp2.kelp.controllers;

import com.Kelp2.kelp.models.Aquarium;
import com.Kelp2.kelp.services.AquariumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/aqua")
public class AquariumController {
    private static final Logger logger = LoggerFactory.getLogger(AquariumController.class);

    private AquariumService aquariumService;

    @Autowired
    public AquariumController (AquariumService aquariumService){this.aquariumService = aquariumService;}

    @GetMapping(path="/{page}")//research pagination further.
    public ResponseEntity<Page<Aquarium>> getAllAquariums(@PathVariable(name="page") int page){
        logger.info("Received request for all Aquariums");
        Page<Aquarium> aquaList = aquariumService.findAll(page,30);
        return new ResponseEntity<> (aquaList, HttpStatus.OK);
    }

    @GetMapping(path="/id/{aquaId}")
    public ResponseEntity<Aquarium> getAquariumByID(@PathVariable(name="aquaId") int aquaId){
        logger.info("Received request for Aquarium by ID");
        Aquarium calledAqua = aquariumService.findByID(aquaId);
        return new ResponseEntity<> (calledAqua, HttpStatus.OK);
    }

    @GetMapping(path="/name/{name}")
    public ResponseEntity<List<Aquarium>> getAquariumByName(@PathVariable(name="name") String name){
        logger.info("Received request for Aquarium by Name");
        List<Aquarium> calledAqua = aquariumService.findByName(name);
        System.out.println(calledAqua);
        return new ResponseEntity<> (calledAqua, HttpStatus.OK);
    }

    @GetMapping(path="/address/{address}")
    public ResponseEntity<List<Aquarium>> getAquariumByCity(@PathVariable(name="address") String address){
        logger.info("Received request for Aquarium by Address");
        List<Aquarium> calledAqua = aquariumService.findByAddress(address);
        return new ResponseEntity<> (calledAqua, HttpStatus.OK);
    }

    @PostMapping(path="/create/{token}")
    public ResponseEntity<Aquarium> saveAquarium(@RequestBody String json){
        System.out.println(json);
        logger.info("Adding an Aquarium to the Database");
        Aquarium addedAqua = aquariumService.saveAqua(json);
        return new ResponseEntity<>(addedAqua, HttpStatus.OK);
    }

}
