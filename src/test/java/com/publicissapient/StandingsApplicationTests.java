package com.publicissapient;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.publicissapient.model.FootBallLeagueResponse;
import com.publicissapient.service.FootBallService;
import com.publicissapient.service.exceptions.FootBallException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(properties = {"management.port=0"})
public class StandingsApplicationTests {
	
	@Autowired
    private MockMvc mvc;

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;
	
	@Autowired
	private FootBallService standingService;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.mgt + "/actuator/info", Map.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
/*	@Test
	public void getTeamStandings()
	{
		try {
			List<FootBallLeagueResponse>   response=standingService.getStandingsData("Scotland", "Premiership", "Celtic","10");
			assert(!response.isEmpty());
		}
		catch (Exception e) {
			assert(true);
		}
	}
	
	@Test
	public void getTeamStandings_InvalidCountryName() throws FootBallException
	{
		try {
			List<FootBallLeagueResponse>   response=standingService.getStandingsData("Scotland123", "Premiership", "Celtic","10");
			assert(response.isEmpty());
		}
		catch (Exception e) {
			assert(true);
		}

	}*/
	/*
	 */
	
	

}
