package com.Kelp2.kelp;


import com.Kelp2.kelp.models.User;
import com.Kelp2.kelp.services.UserService;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    private User user;
    private User updateUser;
    private String userJson;
    private String updateUserJson;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @MockBean
    private UserService us;

    @Before
    public void setupMvc(){
        this.mvc = webAppContextSetup(wac).build();
    }

    @Before
    public void modelSetup() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        this.user = new User();
        this.updateUser = new User();

        this.user.setID(1);
        this.user.setUserName("CarlSemken");
        this.user.setProfilePic("https://static.wikia.nocookie.net/soma/images/6/61/Carl_Semken.png/revision/latest/scale-to-width-down/260?cb=20200601182323");
        this.user.setFishPersonality("Sting Ray");
        this.user.setLocation("Under the Atlantic");
        this.user.setTwitter("n/a");
        this.user.setFacebook("n/a");
        this.user.setInstagram("n/a");
        this.user.setBio("A researcher in the Upsilon facility of the PATHOS-II underwater complex.");
        this.user.setEmail("Carl.Semken@PathosResearch.org");

        this.updateUser.setID(1);
        this.updateUser.setUserName("CarlSemkenBot");
        this.updateUser.setProfilePic("https://shortcut-test2.s3.amazonaws.com/uploads/role_template_image/attachment/135506/default_carl-semken.jpg");
        this.updateUser.setFacebook("Angler Fish");
        this.updateUser.setLocation("Not on the ARK.");
        this.updateUser.setTwitter("n/a");
        this.updateUser.setFacebook("n/a");
        this.updateUser.setInstagram("n/a");
        this.updateUser.setBio("Still asking for help, despite someone throwing a bottle of Anti-freeze at me.");
        this.updateUser.setEmail("Carl.Semken@PathosResearch.org");

        this.userJson = om.writeValueAsString(this.user);
        this.updateUserJson = om.writeValueAsString(this.updateUser);
    }

    @Test
    public void givenUserIDReturnResults() throws Exception{
        when(us.findUserByID(1)).thenReturn(this.user);
        MvcResult result = mvc.perform(get("/user/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),this.userJson);
    }

    @Test
    public void givenUserNameReturnResults() throws Exception{
        when(us.findByUserName("CarlSemken")).thenReturn(this.user);
        MvcResult result = mvc.perform(get("/user/name/CarlSemken"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),this.userJson);
    }

    @Test
    public void givenEmailReturnResults() throws Exception{
        when(us.findByEmail("Carl.Semken@PathosResearch.org")).thenReturn(this.user);
        MvcResult result = mvc.perform(get("/user/email/Carl.Semken@PathosResearch.org"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),this.userJson);
    }

    @Test
    public void createUserReturnInfo() throws Exception{
        when(us.saveUser(userJson)).thenReturn(user);
        MvcResult result = mvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),this.userJson);
    }

    @Test
    public void updateUserReturnInfo() throws Exception{
        ObjectMapper om = new ObjectMapper();

        when(us.updateUserInfoByEmail(this.updateUserJson)).thenReturn(this.updateUser);
        MvcResult result = mvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.updateUserJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),this.updateUserJson);
    }
}
