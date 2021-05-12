package com.Kelp2.kelp.services;

import com.Kelp2.kelp.DAO.ReviewRepo;
import com.Kelp2.kelp.models.Review;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private ReviewRepo reviewRepo;

    @Autowired
    public ReviewService(ReviewRepo reviewRepo){this.reviewRepo = reviewRepo;}

    public List<Review> findReviewByAquaID(int aquaID){
        return reviewRepo.findByAquariumID(aquaID);
    }

    public Review saveReview(String json){
        ObjectMapper om = new ObjectMapper();
        Review review = null;
        try{
            review = om.readValue(json, Review.class);
            reviewRepo.save(review);
            return review;
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
            return null;
        } catch (Exception e){
            logger.warn(e.getMessage());
            return null;
        }
    }

    public Review updateUpvotes(int reviewId, String upvotes){
        Review review = reviewRepo.getOne(reviewId);
        int downvotes = review.getDownvotes().split(",").length;
        int up = upvotes.split(",").length;
        review.setUpvotes(upvotes);
        review.setVotes(up - downvotes);
        try{
            reviewRepo.save(review);
        } catch(Exception e){
            logger.warn(e.getMessage());
        }
        return review;
    }

    public Review updateDownvotes(int reviewId, String downvotes){
        Review review = reviewRepo.getOne(reviewId);
        int upvotes = review.getUpvotes().split(",").length;
        int down = downvotes.split(",").length;
        review.setVotes(upvotes - down);
        review.setDownvotes(downvotes);
        try{
            reviewRepo.save(review);
        } catch(Exception e){
            logger.warn(e.getMessage());
        }
        return review;
    }


}
