package com.Kelp2.kelp.controllers;


import com.Kelp2.kelp.models.Comment;
import com.Kelp2.kelp.models.Review;
import com.Kelp2.kelp.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/comment")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private CommentService commentService;

    @Autowired
    public CommentController (CommentService commentService){this.commentService=commentService;}

    @GetMapping(path="/{reviewId}")
    public ResponseEntity<List<Comment>> getAllCommentsByReviewID(@PathVariable(name="reviewId") int reviewID){
        logger.info("Received request for Comments by ReviewID");
        List<Comment> allComments = commentService.findAllByReviewID(reviewID);
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @GetMapping(path="/reply/{replyId}")
    public ResponseEntity<List<Comment>> getAllCommentsByReplyID(@PathVariable(name="replyId") int replyID){
        logger.info("Received request for Comments by ReplyID " + replyID);
        List<Comment> allComments = commentService.findAllByReplyID(replyID);
        return new ResponseEntity<>(allComments, HttpStatus.OK);
    }

    @PostMapping(path="/create/{token}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> submitComment(@RequestBody String json){
        logger.info("Submitting a Comment");
        Comment submittedComment = commentService.saveComment(json);
        return new ResponseEntity<>(submittedComment, HttpStatus.OK);
    }

    @PutMapping(path="/upvotes/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> updateUpvotes(@RequestBody String upvotes,
                                                @PathVariable(name="commentId") int commentId){
        logger.info("Updating upvotes");
        System.out.println(upvotes);
        Comment updatedComment = commentService.updateUpvotes(commentId, upvotes);
        System.out.println(updatedComment);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @PutMapping(path="/downvotes/{commentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> updateDownvotes(@RequestBody String downvotes,
                                                  @PathVariable(name="commentId") int commentId){
        logger.info("Updating downvotes");
        System.out.println(downvotes);
        Comment updatedDownvotes = commentService.updateDownvotes(commentId, downvotes);
        System.out.println(updatedDownvotes);
        return new ResponseEntity<>(updatedDownvotes, HttpStatus.OK);
    }

}
