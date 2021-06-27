package com.aaquib.sapient.league.standings.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Competition {

    @JsonProperty("country_id")
    public String countryId;

    @JsonProperty("country_name")
    public String countryName;

    @JsonProperty("league_id")
    public String leagueId;

    @JsonProperty("league_name")
    public String leagueName;

    @JsonProperty("league_season")
    public String leagueSeason;

    @JsonProperty("league_logo")
    public String leagueLogo;

    @JsonProperty("country_logo")
    public String countryLogo;
}
