package com.publicissapient.service;

import java.util.List;

import com.publicissapient.model.FootBallLeagueResponse;
import com.publicissapient.service.exceptions.FootBallException;

public interface FootBallService {
	String getCountryId(String countryName) throws FootBallException;

	String getLeagueId(String leagueName) throws FootBallException;

	List<FootBallLeagueResponse> getFootBallStandingsData(String countryName, Integer leagueName, Integer teamName)
			throws FootBallException;
}
