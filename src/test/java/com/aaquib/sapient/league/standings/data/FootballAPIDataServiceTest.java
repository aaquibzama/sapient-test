package com.aaquib.sapient.league.standings.data;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@SpringBootTest
class FootballAPIDataServiceTest {
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private FootballAPIDataService dataService;

    @Test
    public void testGet() {
        Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.any())).thenReturn("String");

        dataService.get(UriComponentsBuilder.fromHttpUrl("http://test"));
    }

    @Test
    public void testGetWithOneFailure() {
        Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.any())).thenThrow(new RuntimeException("Testing")).thenReturn("String");

        dataService.get(UriComponentsBuilder.fromHttpUrl("http://test"));
    }

    @Test
    public void testGetWithThreeFailure() {
        Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.any()))
                .thenThrow(new RuntimeException("Testing"))
                .thenThrow(new RuntimeException("Testing"))
                .thenThrow(new RuntimeException("Testing"))
                .thenReturn("String");

        dataService.get(UriComponentsBuilder.fromHttpUrl("http://test"));
    }
}