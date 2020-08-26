package com.careerit.iplstat.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.careerit.iplstat.domain.Team;

@SpringBootTest
public class IplstatDataUtilTest {

	@Autowired
	private IplstatDataUtil util;
	
	@Test
	public void loadDataFromJsonTest() {
		List<Team> teams = util.loadDataFromJson();
		teams.stream().forEach(e->{
			System.out.println(e);
		});
		assertEquals(8, teams.size());
	}
}
