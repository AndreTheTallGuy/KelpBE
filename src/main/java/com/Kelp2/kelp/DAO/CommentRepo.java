package com.Kelp2.kelp.DAO;

import com.Kelp2.kelp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
    public List<Comment> findByReviewID(int reviewID);

    public List<Comment> findByReplyID(int replyID);


}
