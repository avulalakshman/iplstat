package com.careerit.iplstat.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.careerit.iplstat.dao.IplstatDao;
import com.careerit.iplstat.dto.LabelDTO;
import com.careerit.iplstat.dto.PlayerDTO;
import com.careerit.iplstat.dto.RoleCountDTO;
import com.careerit.iplstat.dto.TeamAmountDTO;
import com.careerit.iplstat.dto.TeamDTO;
import com.careerit.iplstat.dto.TeamRoleAmountDTO;

@Primary
@Service
public class IplstatServiceMdbImpl implements IplstatService {

	private Logger log = LoggerFactory.getLogger(IplstatServiceMdbImpl.class);
	@Autowired
	private IplstatDao iplsatDao;

	@Override
	public LabelDTO labels() {
		return iplsatDao.selectTeamLabels();
	}

	@Override
	public List<PlayerDTO> playersByLabel(String label) {
		Assert.notNull(label, "Label can't be empty or null");
		List<PlayerDTO> players = iplsatDao.findPlayersByLabel(label);
		log.info("Total players found for the given label : {} is :{}", label, players.size());
		return players;
	}

	@Override
	public List<RoleCountDTO> roleCountByTeam(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDTO> playersByLabelAndRole(String label, String roleName) {
		// TODO Auto-generated method stub
		return null;
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
