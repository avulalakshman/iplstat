package com.careerit.iplstat.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.careerit.iplstat.domain.Player;
import com.careerit.iplstat.dto.LabelDTO;
import com.careerit.iplstat.dto.RoleCountDTO;
import com.careerit.iplstat.service.IplstatService;

@RestController
@RequestMapping("/api/v1/ipl")
public class IplstatController {

	@Autowired
	private IplstatService iplstatService;

	@RequestMapping("/label")
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
	public List<Player> getPlayerByLabelAndRole(@PathVariable("label")String label,@PathVariable("rolename")String roleName){
		
		return null;
	}
	@RequestMapping("/players/{label}")
	public List<Player> getPlayerByLabel(@PathVariable("label")String label){
		
		return null;
	}
}
