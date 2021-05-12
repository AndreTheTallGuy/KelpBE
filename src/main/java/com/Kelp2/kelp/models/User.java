package com.Kelp2.kelp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name="display_name")
    private String userName;

    @Column(name="profile_pic")
    private String profilePic;

    @Column(name="fish_personality")
    private String fishPersonality;

    @Column(name="location")
    private String location;

    @Column(name="twitter")
    private String twitter;

    @Column(name="facebook")
    private String facebook;

    @Column(name="instagram")
    private String instagram;

    @Column(name="bio")
    private String bio;

    @Column(name="email")
    private String email;
}
