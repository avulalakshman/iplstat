package com.careerit.iplstat.service;

import java.util.List;

import com.careerit.iplstat.domain.Player;
import com.careerit.iplstat.dto.RoleCountDTO;

public interface IplstatService {
	List<String> labels();
    List<Player> playersByTeam(String label);
    List<RoleCountDTO> roleCountByTeam(String label);
    List<Player> playersByTeamAndRole(String label,String roleName);
}
