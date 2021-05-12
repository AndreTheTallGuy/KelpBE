package com.Kelp2.kelp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="aquarium")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Aquarium {

    @Id
    @Column(name="aquarium_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aquariumID;

    @Column(name="photo")
    private String photo;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    @Column(name="url")
    private String url;

    @Column(name="address")
    private String address;

    @Column(name="description")
    private String description;

}
