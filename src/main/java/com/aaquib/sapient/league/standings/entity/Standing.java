package com.aaquib.sapient.league.standings.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Standing {

    @JsonProperty("country_name")
    public String countryName;

    @JsonProperty("league_id")
    public String leagueId;

    @JsonProperty("league_name")
    public String leagueName;

    @JsonProperty("team_id")
    public String teamId;

    @JsonProperty("team_name")
    public String teamName;

    @JsonProperty("overall_league_position")
    public String overallLeaguePosition;
}
