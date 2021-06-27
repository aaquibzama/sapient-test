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
class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountriesService countriesService;

    @Test
    public void testGetCountries() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/countries")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(countriesService, Mockito.times(1)).execute();
    }
}