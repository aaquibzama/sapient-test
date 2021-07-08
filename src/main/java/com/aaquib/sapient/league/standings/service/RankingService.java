package com.aaquib.sapient.league.standings.service;

import com.aaquib.sapient.league.standings.controller.StandingsController;
import com.aaquib.sapient.league.standings.entity.Competition;
import com.aaquib.sapient.league.standings.entity.Country;
import com.aaquib.sapient.league.standings.entity.Standing;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingService {

    public static Logger log = LoggerFactory.getLogger(RankingService.class);

    private final CompetitionsService competitionsService;

    private final CountriesService countriesService;

    private final StandingsService standingsService;

    public RankingService(CompetitionsService competitionsService, CountriesService countriesService, StandingsService standingsService) {
        this.competitionsService = competitionsService;
        this.countriesService = countriesService;
        this.standingsService = standingsService;
    }

    public String execute(String countryName, String leagueName, String teamName) {
        return getRank(getLeagueId(getCountryId(countryName), leagueName), teamName);
    }

    private String getCountryId(String countryName) {
        if (StringUtils.isBlank(countryName)) return StringUtils.EMPTY;
        List<Country> countryList = countriesService.execute();
        for (Country country : countryList) {
            if (country.getCountryName().equalsIgnoreCase(countryName)) {
                return country.getCountryId();
            }
        }
        return StringUtils.EMPTY;
    }

    private String getLeagueId(String countryId, String leagueName) {
        if (StringUtils.isBlank(countryId) || StringUtils.isBlank(leagueName)) return StringUtils.EMPTY;
        List<Competition> competitionList = competitionsService.execute(countryId);
        for (Competition competition : competitionList) {
            if (competition.getLeagueName().equalsIgnoreCase(leagueName)) {
                return competition.getLeagueId();
            }
        }
        return StringUtils.EMPTY;
    }

    private String getRank(String leagueId, String teamName) {
        if (StringUtils.isBlank(teamName) || StringUtils.isBlank(leagueId)) return StringUtils.EMPTY;
        List<Standing> standingList = standingsService.execute(leagueId);
        for (Standing standing: standingList) {
            if (standing.getTeamName().equalsIgnoreCase(teamName)) {
                return standing.getOverallLeaguePosition();
            }
        }
        return StringUtils.EMPTY;
    }
}
