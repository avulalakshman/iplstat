package com.careerit.iplstat.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.careerit.iplstat.domain.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class IplstatDataUtil {

	private Logger log = LoggerFactory.getLogger(IplstatDataUtil.class);

	String fileName = "/ipl2020.json";

	public List<Team> loadDataFromJson() {

		List<Team> teams = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			teams = mapper.readValue(IplstatDataUtil.class.getResourceAsStream(fileName),
					new TypeReference<List<Team>>() {
					});
			log.info("Total {} teams found in the file: {}", teams.size(), fileName);
		} catch (IOException e) {
			log.error("While loading data from file:{}", e);
		}

		return teams;
	}
}
