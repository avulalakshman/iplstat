package com.careerit.iplstat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerit.iplstat.dto.LabelDTO;
import com.careerit.iplstat.dto.PlayerDTO;
import com.careerit.iplstat.dto.RoleCountDTO;
import com.careerit.iplstat.dto.TeamAmountDTO;
import com.careerit.iplstat.dto.TeamDTO;
import com.careerit.iplstat.dto.TeamRoleAmountDTO;
import com.careerit.iplstat.service.IplstatService;

@RestController
@RequestMapping("/api/v1/ipl")
public class IplstatController {

	@Autowired
	private IplstatService iplstatService;

	@RequestMapping("/teams/label")
	public LabelDTO getLabels() {
		List<String> labels = iplstatService.labels();
		LabelDTO labelDTO = new LabelDTO();
		labelDTO.setLabels(labels);
		return labelDTO;
	}

	@RequestMapping("/rolecount/{label}")
	public List<RoleCountDTO> getRoleCount(@PathVariable("label") String label) {
		return iplstatService.roleCountByTeam(label);
	}

	@RequestMapping("/players/{label}/{rolename}")
	public List<PlayerDTO> getPlayerByLabelAndRole(@PathVariable("label") String label,
			@PathVariable("rolename") String roleName) {
		return iplstatService.playersByLabelAndRole(label, roleName);
	}

	@RequestMapping("/players/{label}")
	public List<PlayerDTO> getPlayerByLabel(@PathVariable("label") String label) {
		return iplstatService.playersByLabel(label);
	}

	@RequestMapping("/teams")
	public List<TeamDTO> getAllTeams() {
		return iplstatService.allTeamDetails();
	}

	@RequestMapping("/teams/amountspent")
	public List<TeamAmountDTO> getAmountSpentByAllTeams() {

		return iplstatService.getAmountSpentByTeams();
	}

	@RequestMapping("/teams/{label}/amountforallroles")
	public List<TeamRoleAmountDTO> getAmountSpentByTeamForAllRoles(@PathVariable("label") String label) {
		return iplstatService.getAmountSpentByTeamAllRoles(label);
	}
}
