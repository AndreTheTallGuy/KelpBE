package com.Kelp2.kelp;


import com.Kelp2.kelp.DAO.UserRepo;
import com.Kelp2.kelp.models.User;
import com.Kelp2.kelp.services.UserService;
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
public class UserServiceTests {

    private User user;
    private String userJson;

    @Mock
    private UserRepo ur;

    @InjectMocks
    private UserService us;

    @Before
    public void setupModels() throws JsonProcessingException{
        ObjectMapper om = new ObjectMapper();

        this.user = new User();
        this.userJson = om.writeValueAsString(this.user);

    }

    @Test
    public void givenInfoUpdateUser_GiveError(){
        when(this.us.updateUserInfoByEmail(this.userJson)).thenReturn(this.user);

        User expected = this.us.updateUserInfoByEmail(this.userJson);
        User actual = this.user;

        System.out.println(expected);
        System.out.println(actual);

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void givenInfoSaveUser(){
        when(this.us.saveUser(this.userJson)).thenReturn(this.user);

        User expected = this.us.saveUser(this.userJson);
        User actual = this.user;

        System.out.println(expected);
        System.out.println(actual);

        Assert.assertEquals(expected, actual);
    }
}
