package com.Kelp2.kelp;


import com.Kelp2.kelp.DAO.CommentRepo;
import com.Kelp2.kelp.models.Comment;
import com.Kelp2.kelp.services.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTests {

    private Comment comment;
    private String commentJson;

    @Mock
    private CommentRepo cr;

    @InjectMocks
    private CommentService cs;

    @Before
    public void setupModels() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        this.comment = new Comment();
        this.commentJson = om.writeValueAsString(this.comment);
    }

    @Test
    public void givenInfoSaveComment(){
        when(this.cs.saveComment(this.commentJson)).thenReturn(this.comment);

        Comment expected = this.cs.saveComment(this.commentJson);
        Comment actual = this.comment;

        System.out.println(expected);
        System.out.println(actual);

        Assert.assertEquals(expected, actual);
    }
}
