package com.publicissapient.service;

import java.util.ArrayList;
import java.util.List;

import com.publicissapient.config.DataConfiguration;
import com.publicissapient.model.Country;
import com.publicissapient.model.FootBallLeagueResponse;
import com.publicissapient.model.League;
import com.publicissapient.repository.CountryRepository;
import com.publicissapient.repository.LeagueRepostory;
import com.publicissapient.service.exceptions.FootBallException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FootBallServiceImpl implements FootBallService {

	@Autowired
	DataConfiguration config;
	
	@Autowired
    CountryRepository countryRepo;
	
	@Autowired
    LeagueRepostory lrepo;

	@Override
	public String getCountryId(String countryName) throws FootBallException {
		String country_Id = "";
		ResponseEntity<Country[]> responsecountryList = countryRepo.getCountryId(countryName);
		
		
		for (Country country : responsecountryList.getBody()) {
			System.out.println("country == " + country.toString());
			if (countryName.equalsIgnoreCase(country.getCountryName())) {
				country_Id = country.getCountryId();
			}
		}

		if (StringUtils.isEmpty(country_Id)) {
			throw new FootBallException("Country does not exist in league.");
		}
		return country_Id;

	}

	@Override
	public String getLeagueId(String country_Id) throws FootBallException {

		String league_Id = "";
		ResponseEntity<League[]> responseLeagueList = lrepo.getLeagueId(country_Id);
		
		for (League league : responseLeagueList.getBody()) {
			league_Id = league.getLeague_id();
		}
		
		if (StringUtils.isEmpty(league_Id)) {
			throw new FootBallException("League does not exist.");
		}
		
		return league_Id;
	}
	@Override
	public List<FootBallLeagueResponse> getFootBallStandingsData(String countryName, Integer leagueName, Integer teamName)
			throws FootBallException {
		RestTemplate resttemplate = new RestTemplate();
		
		String country_Id=getCountryId(countryName);
		String league_Id=getLeagueId(country_Id);

		
		// get standings
		String standingUrl = UriComponentsBuilder.fromUriString(config.getGetstandingsurl())
				.replaceQueryParam("league_id", league_Id).toUriString();

		ResponseEntity<FootBallLeagueResponse[]> standingresponse = resttemplate.getForEntity(standingUrl,
				FootBallLeagueResponse[].class);

		List<FootBallLeagueResponse> list = new ArrayList<>();
		FootBallLeagueResponse leagueResponse = null;
		
		
		for (FootBallLeagueResponse standings : standingresponse.getBody()) {
			if (standings.getLeagueName().equalsIgnoreCase(leagueName.toString())
					&& standings.getCountryName().equalsIgnoreCase(countryName)
					&& standings.getTeamName().equalsIgnoreCase(teamName.toString()) ) {
				leagueResponse = new FootBallLeagueResponse();
				leagueResponse.setCountryId(country_Id);
				leagueResponse.setCountryName(standings.getCountryName());
				leagueResponse.setLeagueName(standings.getLeagueName());
				leagueResponse.setLegaueId(league_Id);
				leagueResponse.setTeamId(standings.getTeamId());
				leagueResponse.setTeamName(standings.getTeamName());
				list.add(leagueResponse);
			}

		}
		return list;
	}

}
