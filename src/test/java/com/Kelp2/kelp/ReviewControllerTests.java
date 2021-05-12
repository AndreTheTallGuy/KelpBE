package com.Kelp2.kelp;


import com.Kelp2.kelp.models.Review;
import com.Kelp2.kelp.services.ReviewService;
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

import java.sql.Date;
import java.time.LocalDate;
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
public class ReviewControllerTests {

    private Review review;
    private String reviewJson;
    private List<Review> reviewList;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @MockBean
    private ReviewService rs;

    @Before
    public void setupMvc(){
        this.mvc = webAppContextSetup(wac).build();
    }

    @Before
    public void modelSetup() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        this.review = new Review();
        this.reviewList = new ArrayList<>();

        this.review.setAquariumID(1);
        this.review.setUserID(1);
        this.review.setRating(5);
        this.review.setReviewText("Scotland's National Aquarium");

        this.reviewJson=om.writeValueAsString(this.review);
        this.reviewList.add(this.review);

    }

    @Test
    public void givenAquaIDReturnResults() throws Exception{
        when(rs.findReviewByAquaID(1)).thenReturn(reviewList);
        MvcResult result=mvc.perform(get("/review/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),reviewJson);
    }

    @Test
    public void createReviewReturnInfo() throws Exception{
        when(rs.saveReview(reviewJson)).thenReturn(review);
        MvcResult result=this.mvc.perform(post("/review/create")
            .contentType(MediaType.APPLICATION_JSON)
            .content(reviewJson))
            .andExpect(status().isOk())
            .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),reviewJson);

    }
}
