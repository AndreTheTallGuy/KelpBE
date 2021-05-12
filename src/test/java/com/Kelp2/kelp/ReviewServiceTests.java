package com.Kelp2.kelp;


import com.Kelp2.kelp.DAO.ReviewRepo;
import com.Kelp2.kelp.models.Review;
import com.Kelp2.kelp.services.ReviewService;
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
public class ReviewServiceTests {

    private Review review;
    private String reviewJson;

    @Mock
    private ReviewRepo rr;

    @InjectMocks
    private ReviewService rs;

    @Before
    public void setupModels() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        this.review = new Review();
        this.reviewJson = om.writeValueAsString(this.review);
    }

    @Test
    public void givenInfoSaveReview(){
        when(this.rs.saveReview(this.reviewJson)).thenReturn(this.review);

        Review expected = this.rs.saveReview(this.reviewJson);
        Review actual = this.review;

        System.out.println(expected);
        System.out.println(actual);

        Assert.assertEquals(expected, actual);
    }
}
