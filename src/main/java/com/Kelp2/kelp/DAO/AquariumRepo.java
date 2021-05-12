package com.Kelp2.kelp.DAO;

import com.Kelp2.kelp.models.Aquarium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AquariumRepo extends JpaRepository<Aquarium, Integer> {
//    @Query(value = "SELECT a FROM Aquarium a WHERE a.name LIKE string or a.city LIKE string")
    public List<Aquarium> findByNameIgnoreCaseContaining(String name);

    public List<Aquarium> findByAddressIgnoreCaseContaining(String address);

}
