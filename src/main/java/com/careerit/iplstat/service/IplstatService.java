package com.careerit.iplstat.service;

import java.util.List;

import com.careerit.iplstat.dto.PlayerDTO;
import com.careerit.iplstat.dto.RoleCountDTO;
import com.careerit.iplstat.dto.TeamAmountDTO;
import com.careerit.iplstat.dto.TeamDTO;
import com.careerit.iplstat.dto.TeamRoleAmountDTO;

public interface IplstatService {
	List<String> labels();
    List<PlayerDTO> playersByLabel(String label);
    List<RoleCountDTO> roleCountByTeam(String label);
    List<PlayerDTO> playersByLabelAndRole(String label,String roleName);
    
    List<TeamDTO> allTeamDetails();
    List<TeamAmountDTO> getAmountSpentByTeams();
    List<TeamRoleAmountDTO> getAmountSpentByTeamAllRoles(String label);
    
    
}
