package com.Kelp2.kelp.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name="comments")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {

    @Id
    @Column(name="comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    @Column(name="review_id")
    private int reviewID;

    @Column(name="user_id")
    private int userID;

    @Column(name="reply_id")
    private int replyID;

    @Column(name="comment")
    private String comment;

    @Column(name="posted_date")
    @Temporal(TemporalType.DATE)
    private java.util.Date postedDate;

    @Column(name="margin")
    private int margin;

    @Column(name="upvotes")
    private String upvotes;

    @Column(name="downvotes")
    private String downvotes;

    @Column(name="votes")
    private int votes;
}
