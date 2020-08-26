package com.careerit.iplstat.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Team {
		private String city;
		private String coach;
		private String name;
		private String home;
		private String label;
		private List<Player> players;
}
