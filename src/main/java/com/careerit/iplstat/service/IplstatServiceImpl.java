package com.careerit.iplstat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.careerit.iplstat.domain.Player;
import com.careerit.iplstat.domain.Team;
import com.careerit.iplstat.dto.PlayerDTO;
import com.careerit.iplstat.dto.RoleCountDTO;
import com.careerit.iplstat.dto.TeamAmountDTO;
import com.careerit.iplstat.dto.TeamDTO;
import com.careerit.iplstat.dto.TeamRoleAmountDTO;
import com.careerit.iplstat.service.exception.TeamNotFoundException;
import com.careerit.iplstat.util.IplstatDataUtil;

@Service
public class IplstatServiceImpl implements IplstatService {

	private Logger log = LoggerFactory.getLogger(IplstatServiceImpl.class);

	private IplstatDataUtil dataUtil;

	private List<Team> teams;

	@Autowired
	public IplstatServiceImpl(IplstatDataUtil dataUtil) {
		this.dataUtil = dataUtil;
		teams = this.dataUtil.loadDataFromJson();
	}

	@Override
	public List<String> labels() {
		List<String> labelNames = teams.stream().map(t -> t.getLabel()).collect(Collectors.toList());
		log.info("Team label count :{}", labelNames.size());
		return labelNames;
	}

	@Override
	public List<RoleCountDTO> roleCountByTeam(String label) {

		List<RoleCountDTO> roleCountList = new ArrayList<>();
		Assert.notNull(label, "Label name can't be empty or null");

		Optional<Team> optTeam = teams.stream().filter(t -> t.getLabel().equals(label)).findFirst();
		if (optTeam.isPresent()) {
			log.info("Team with label {} is found", label);
			Team team = optTeam.get();
			Map<String, List<Player>> map = team.getPlayers().stream().collect(Collectors.groupingBy(Player::getRole));

			map.entrySet().forEach(e -> {
				RoleCountDTO obj = new RoleCountDTO();
				obj.setRoleName(e.getKey());
				obj.setCount(e.getValue().size());
				roleCountList.add(obj);

			});
			log.info("Role count is :{} ", roleCountList.size());

		}
		return roleCountList;
	}

	@Override
	public List<PlayerDTO> playersByLabel(String label) {
		Assert.notNull(label, "Label name can't be empty or null");

//		Team team = null;
//		for (Team t : teams) {
//			if (t.getLabel().equals(label)) {
//				team = t;
//				break;
//			}
//		}
//
//		if (team != null) {
//			List<Player> players = team.getPlayers();
//			List<PlayerDTO> list = new ArrayList<>();
//			for (Player p : players) {
//				PlayerDTO obj = PlayerDTO.builder().name(p.getName()).price(p.getPrice()).role(p.getRole()).label(label)
//						.build();
//				list.add(obj);
//			}
//			log.info("Total {} player found for the given team : {}",list.size(),label);
//			return list;
//		}

		Optional<Team> optTeam = teams.stream().filter(t -> t.getLabel().equals(label)).findFirst();
		if (optTeam.isPresent()) {
			Team team = optTeam.get();
			List<PlayerDTO> list = team.getPlayers().stream().map(p -> converToDTO(p, label))
					.collect(Collectors.toList());
			log.info("Total {} player found for the given team : {}", list.size(), label);
			return list;
		}

		throw new TeamNotFoundException("Team with label :" + label + " is not found");
	}

	private PlayerDTO converToDTO(Player p, String label) {
		PlayerDTO obj = PlayerDTO.builder().name(p.getName()).price(p.getPrice()).role(p.getRole()).label(label)
				.build();
		return obj;
	}

	@Override
	public List<PlayerDTO> playersByLabelAndRole(String label, String roleName) {
		Assert.notNull(label, "Label name can't be empty or null");
		Assert.notNull(roleName, "Role name can't be empty or null");

		Optional<Team> optTeam = teams.stream().filter(t -> t.getLabel().equals(label)).findFirst();

		if (optTeam.isPresent()) {
			Team team = optTeam.get();
			List<PlayerDTO> playersList = team.getPlayers().stream().filter(p -> p.getRole().equals(roleName))
					.map(p -> converToDTO(p, label)).collect(Collectors.toList());
			log.info("Total {} player found for the given team : {} for given role :{}", playersList.size(), label,
					roleName);
			return playersList;
		}

		throw new TeamNotFoundException("Team with label :" + label + " is not found");
	}

	@Override
	public List<TeamDTO> allTeamDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamAmountDTO> getAmountSpentByTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamRoleAmountDTO> getAmountSpentByTeamAllRoles(String label) {
		// TODO Auto-generated method stub
		return null;
	}

}
