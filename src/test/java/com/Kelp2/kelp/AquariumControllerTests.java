package com.Kelp2.kelp;


import com.Kelp2.kelp.models.Aquarium;
import com.Kelp2.kelp.services.AquariumService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
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
public class AquariumControllerTests {

    private Aquarium aqua;
    private String aquaJson;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @MockBean
    private AquariumService as;

    @Before
    public void setupMvc(){
        this.mvc = webAppContextSetup(wac).build();
    }

    @Before
    public void modelSetup() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        aqua = new Aquarium();

        this.aqua.setAquariumID(1);
        this.aqua.setPhoto("https://pbs.twimg.com/profile_images/1053227774088003584/CQKoPoQS_400x400.jpg");
        this.aqua.setName("Deep Sea World");
        this.aqua.setPhone("01383 411 880");
        this.aqua.setUrl("https://www.deepseaworld.com/");
        this.aqua.setDescription("Scotland's National Aquarium");

        this.aquaJson = om.writeValueAsString(aqua);

    }

    @Test
    public void givenAquaIDReturnResults() throws Exception{
        when(as.findByID(aqua.getAquariumID())).thenReturn(aqua);
        MvcResult result = this.mvc.perform(get("/aqua/id/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),aquaJson);
    }

    @Test
    public void givenPageReturnResults() throws Exception{
        ObjectMapper om = new ObjectMapper();
        PageRequest pages = PageRequest.of(1,2);

        List<Aquarium> aquaList = Arrays.asList(new Aquarium());
        Page<Aquarium> aquaPage = new PageImpl<Aquarium>(aquaList,pages,2);

        when(as.findAll(1,2)).thenReturn(aquaPage);
        MvcResult result = this.mvc.perform(get("/aqua/{page}","1").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),om.writeValueAsString(aquaPage));
    }

    @Test
    public void createAquaReturnInfo() throws Exception{
        when(as.saveAqua(aquaJson)).thenReturn(aqua);
        MvcResult result = this.mvc.perform(post("/aqua/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(aquaJson))
                .andExpect(status().isOk())
                .andReturn();

        Assert.assertEquals(result.getResponse().getContentAsString(),aquaJson);
    }

}
