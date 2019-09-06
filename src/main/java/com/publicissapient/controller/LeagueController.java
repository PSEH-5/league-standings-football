package com.publicissapient.controller;

import java.util.List;

import com.publicissapient.model.FootBallLeagueResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.publicissapient.service.FootBallService;
import com.publicissapient.service.exceptions.FootBallException;

@RestController
public class LeagueController {
	private static final Logger LOGGER = LoggerFactory.getLogger(LeagueController.class);

	@Autowired
	private FootBallService standingservice;

	@GetMapping("/overall-position")
	public String getOverallLeagueStatus(){
		return "Hello";
	}

	public ResponseEntity<List<FootBallLeagueResponse>> getStatus( @RequestParam(value = "name") final String name,
							 @RequestParam(value = "countryId", required=false) final Integer countryId,
							 @RequestParam(value = "leagueId", required=false) final Integer leagueId,
							 @RequestParam(value = "teamId", required=false) final Integer teamId)
			throws FootBallException {
		try {
			List<FootBallLeagueResponse> responselist = standingservice.getFootBallStandingsData(name, leagueId,
					teamId);

			return new ResponseEntity<List<FootBallLeagueResponse>>(responselist, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Error occurred", ex);
			return (ResponseEntity<List<FootBallLeagueResponse>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
