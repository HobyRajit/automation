package com.ibm.aa.adapter.service;

import com.ibm.aa.adapter.dto.SkillConfigRequestDTO;
import com.ibm.aa.adapter.dto.SkillConfigResponseDTO;
import com.ibm.aa.adapter.dto.SkillRequestDTO;
import com.ibm.aa.adapter.dto.SkillResponseDTO;
import com.ibm.aa.adapter.dto.SkillRunRequestDTO;
import com.ibm.aa.adapter.dto.SkillRunResponseDTO;

/**
 * Interface for Skill Service
 * 
 * @author Rakesh R Pai
 *
 */
public interface SkillService {
	
	SkillConfigResponseDTO getToken(SkillConfigRequestDTO configRequestDTO);
	
	SkillResponseDTO getSkills(SkillRequestDTO skillRequestDTO);
	
	SkillRunResponseDTO runSkill(SkillRunRequestDTO runRequestDTO);
}
