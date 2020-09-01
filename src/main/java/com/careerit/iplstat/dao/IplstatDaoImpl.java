package com.careerit.iplstat.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import com.careerit.iplstat.dto.LabelDTO;
import com.careerit.iplstat.dto.PlayerDTO;
import com.careerit.iplstat.dto.RoleCountDTO;
import com.careerit.iplstat.dto.TeamAmountDTO;
import com.careerit.iplstat.dto.TeamDTO;

@Repository
public class IplstatDaoImpl implements IplstatDao {

	private Logger log = LoggerFactory.getLogger(IplstatDaoImpl.class);

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public LabelDTO selectTeamLabels() {

		GroupOperation group = Aggregation.group("null").addToSet("label").as("labels");
		ProjectionOperation project = Aggregation.project().and("labels").as("labels").andExclude("_id");
		Aggregation aggregation = Aggregation.newAggregation(group, project);

		log.debug("Query :{}", aggregation);

		AggregationResults<LabelDTO> result = mongoOperations.aggregate(aggregation, "team", LabelDTO.class);
		LabelDTO labelDto = result.getUniqueMappedResult();
		log.info("Total labels found in db: {}", labelDto != null ? labelDto.getLabels().size() : 0);

		return labelDto;
	}

	@Override
	public List<PlayerDTO> findPlayersByLabel(String label) {
		MatchOperation match = Aggregation.match(Criteria.where("label").is(label));
		UnwindOperation unwind = Aggregation.unwind("players");
		ProjectionOperation project = Aggregation.project().and("players.name").as("name").and("players.role")
				.as("role").and("label").as("label").and("players.price").as("price").andExclude("_id");
		Aggregation aggregation = Aggregation.newAggregation(match, unwind, project);
		log.debug("Query :{}", aggregation);
		AggregationResults<PlayerDTO> result = mongoOperations.aggregate(aggregation, "team", PlayerDTO.class);
		List<PlayerDTO> playersList = result.getMappedResults();
		log.info("Players found in db:{} for given label {}", playersList.size(), label);
		return playersList;
	}

	@Override
	public List<RoleCountDTO> findRoleCountByLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayerDTO> findPlayersByLabelAndRole(String label, String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamDTO> selectTeamDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamAmountDTO> findAmountSpentByLabel(String label) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TeamAmountDTO> findAmountSpentByLabelAndRole(String label, String role) {
		// TODO Auto-generated method stub
		return null;
	}

}
