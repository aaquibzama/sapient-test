package com.aaquib.sapient.league.standings.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FootballAPIDataService {

    public static Logger log = LoggerFactory.getLogger(FootballAPIDataService.class);

    @Autowired
    RestTemplate restTemplate;

    @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public String get(UriComponentsBuilder builder) {
        log.info("Calling football API: {}", builder.toUriString());
        return restTemplate.getForObject(builder.toUriString(), String.class);
    }
}
