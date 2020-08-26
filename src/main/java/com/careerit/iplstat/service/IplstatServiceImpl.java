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
import com.careerit.iplstat.dto.RoleCountDTO;
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
	public List<Player> playersByTeam(String label) {
		
		return null;
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
	public List<Player> playersByTeamAndRole(String label, String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

}
