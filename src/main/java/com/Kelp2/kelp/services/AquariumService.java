package com.Kelp2.kelp.services;

import com.Kelp2.kelp.DAO.AquariumRepo;
import com.Kelp2.kelp.models.Aquarium;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;


@Service
public class AquariumService {
    private static final Logger logger = LoggerFactory.getLogger(AquariumService.class);

    private AquariumRepo aquariumRepo;

    @Autowired
    public AquariumService(AquariumRepo aquariumRepo){this.aquariumRepo = aquariumRepo;}

    public Page<Aquarium> findAll(int page, int size){
        return aquariumRepo.findAll(PageRequest.of(page,size));
    }

    public Aquarium findByID(int aquaID){
        return aquariumRepo.getOne(aquaID);
    }

    public List<Aquarium> findByName(String name){
        return aquariumRepo.findByNameIgnoreCaseContaining(name);
    }

    public List<Aquarium> findByAddress(String address){ return aquariumRepo.findByAddressIgnoreCaseContaining(address); }


    public Aquarium saveAqua(String json){
        ObjectMapper om = new ObjectMapper();
        System.out.println("in the service!!");
        Aquarium aqua = null;
        try {
            aqua = om.readValue(json, Aquarium.class);
            if(aqua !=null) aquariumRepo.save(aqua);
            return aqua;
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
            return null;
        } catch (Exception e){
            logger.warn(e.getMessage());
            return null;
        }
    }
}
