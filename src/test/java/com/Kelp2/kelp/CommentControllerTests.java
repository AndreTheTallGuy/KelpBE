package com.Kelp2.kelp;


import com.Kelp2.kelp.models.Comment;
import com.Kelp2.kelp.services.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTests {

    private Comment comment;
    private String commentJson;
    private List<Comment> commentList = new ArrayList<>();

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @MockBean
    private CommentService cs;

    @Before
    public void setupMvc(){
        this.mvc = webAppContextSetup(wac).build();
    }

    @Before
    public void modelSetup() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        this.comment = new Comment();

        this.comment.setReviewID(1);
        this.comment.setUserID(1);
        this.comment.setReviewID(0);
        this.comment.setComment("This is a test. This is only a test.");

        this.commentJson = om.writeValueAsString(comment);
        this.commentList.add(comment);

    }

    @Test
    public void givenReplyIDReturnResults() throws Exception {
        ObjectMapper om = new ObjectMapper();
        when(cs.findAllByReviewID(1)).thenReturn(this.commentList);
        MvcResult result = this.mvc.perform(get("/comment/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(), om.writeValueAsString(this.commentList));
    }

    @Test
    public void createCommentReturnInfo() throws Exception{
        when(cs.saveComment(commentJson)).thenReturn(this.comment);
        MvcResult result = this.mvc.perform(post("/comment/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(commentJson))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),this.commentJson);
    }

}
