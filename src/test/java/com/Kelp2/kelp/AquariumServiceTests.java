package com.Kelp2.kelp;


import com.Kelp2.kelp.DAO.AquariumRepo;
import com.Kelp2.kelp.models.Aquarium;
import com.Kelp2.kelp.services.AquariumService;
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
public class AquariumServiceTests {

    private Aquarium aqua;
    private String aquaJson;

    @Mock
    private AquariumRepo ar;

    @InjectMocks
    private AquariumService as;

    @Before
    public void setupModels() throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();

        this.aqua = new Aquarium();
        this.aquaJson = om.writeValueAsString(this.aqua);

    }

    @Test
    public void givenInfoSaveAqua(){
        when(this.as.saveAqua(this.aquaJson)).thenReturn(this.aqua);

        Aquarium expected = this.as.saveAqua(this.aquaJson);
        Aquarium actual = this.aqua;

        System.out.println(expected);
        System.out.println(actual);

        Assert.assertEquals(expected,actual);
    }
}
