package com.careerit.iplstat.dao;

import java.util.List;

import com.careerit.iplstat.dto.LabelDTO;
import com.careerit.iplstat.dto.PlayerDTO;
import com.careerit.iplstat.dto.RoleCountDTO;
import com.careerit.iplstat.dto.TeamAmountDTO;
import com.careerit.iplstat.dto.TeamDTO;

public interface IplstatDao {
	
			public LabelDTO selectTeamLabels();
			public List<PlayerDTO> findPlayersByLabel(String label);
			public List<RoleCountDTO> findRoleCountByLabel(String label);
			public List<PlayerDTO> findPlayersByLabelAndRole(String label,String role);
			public List<TeamDTO> selectTeamDetails();
			public List<TeamAmountDTO> findAmountSpentByLabel(String label);
			public List<TeamAmountDTO> findAmountSpentByLabelAndRole(String label,String role);
			
			
}
