package com.aaquib.sapient.league.standings.controller;

import com.aaquib.sapient.league.standings.Application;
import com.aaquib.sapient.league.standings.service.CompetitionsService;
import com.aaquib.sapient.league.standings.service.CountriesService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = Application.class)
@EnableAutoConfiguration
@AutoConfigureMockMvc
public class CompetitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompetitionsService competitionsService;

    @Test
    public void testGetCompetition() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/competition?countryId=44")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(competitionsService, Mockito.times(1)).execute("44");
    }
}
