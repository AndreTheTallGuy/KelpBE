package com.Kelp2.kelp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="review")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Review {

    @Id
    @Column(name="review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewID;

    @Column(name="aquarium_id")
    private int aquariumID;

    @Column(name="user_id")
    private int userID;

    @Column(name="rating")
    private float rating;

    @Column(name="review_text")
    private String reviewText;

    @Column(name="visited_date")
    private java.sql.Date visitedDate;

    @Column(name="posted_date")
    private java.sql.Date postedDate;

    @Column(name="upvotes")
    private String upvotes;

    @Column(name="downvotes")
    private String downvotes;

    @Column(name="signage")
    private int signage;

    @Column(name="votes")
    private int votes;
}
