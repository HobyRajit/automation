package com.ibm.aa.adapter.controller;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.serviceloader.ServiceFactoryBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.aa.adapter.dto.SkillConfigRequestDTO;
import com.ibm.aa.adapter.dto.SkillConfigResponseDTO;
import com.ibm.aa.adapter.dto.SkillRequestDTO;
import com.ibm.aa.adapter.dto.SkillResponseDTO;
import com.ibm.aa.adapter.dto.SkillRunRequestDTO;
import com.ibm.aa.adapter.dto.SkillRunResponseDTO;
import com.ibm.aa.adapter.model.SkillConfigRequest;
import com.ibm.aa.adapter.model.SkillConfigResponse;
import com.ibm.aa.adapter.model.SkillError;
import com.ibm.aa.adapter.model.SkillInfo;
import com.ibm.aa.adapter.model.SkillRequest;
import com.ibm.aa.adapter.model.SkillResponse;
import com.ibm.aa.adapter.model.SkillRunRequest;
import com.ibm.aa.adapter.model.SkillRunResponse;
import com.ibm.aa.adapter.service.SkillService;
import com.ibm.aa.adapter.service.SkillServiceFactory;

@RestController
public class SkillController {
	
	Logger logger = LoggerFactory.getLogger(SkillController.class);
	public static final String WORKSPACE_TYPE ="PRIVATE";
	
	@Autowired
	private SkillService service;
	
	@Autowired
	private SkillServiceFactory skillServiceFactory;
	
	
	@CrossOrigin(origins = "*")
	@PostMapping(value="/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SkillConfigResponse> getToken(@RequestBody SkillConfigRequest skillConfigRequest){
		logger.info("Start of getToken");
		service = skillServiceFactory.getSkillService(skillConfigRequest.getConnectorName());
		SkillConfigResponse skillConfigResponse = null;
		if (Objects.nonNull(service)) {
			SkillConfigRequestDTO skillConfigRequestDTO = mapProcessConfigModelToDTO.apply(skillConfigRequest);
			SkillConfigResponseDTO skillConfigResponseDTO =service.getToken(skillConfigRequestDTO);
			skillConfigResponse  = mapProcessConfigDTOToModel.apply(skillConfigResponseDTO);
			if(Objects.nonNull(skillConfigResponse.getError())) {
				HttpStatus status = HttpStatus.resolve((int)skillConfigResponse.getError().getCode());
				return ResponseEntity.status(status).body(skillConfigResponse);
				
			}
			skillConfigResponse.setConnectorName(skillConfigRequest.getConnectorName());
			skillConfigResponse.setWorkspaceType(WORKSPACE_TYPE);
		}else {
			logger.error("Invalid source provided");
			skillConfigResponse = new SkillConfigResponse();
			SkillError skillError = new SkillError();
			skillError.setCode(400);
			skillError.setDescription("Invalid source name");
			skillConfigResponse.setError(skillError);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(skillConfigResponse);
		}
		
		logger.info("End of getToken");
		return ResponseEntity.status(HttpStatus.OK).body(skillConfigResponse);
	}
	
	@PostMapping(value="/skills", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SkillResponse> getSkills(@RequestBody SkillRequest skillRequest){
		
		logger.info("Start of getSkills");
		SkillResponse skillResponse = null;
		
		service = skillServiceFactory.getSkillService(skillRequest.getConnectorName());
		if (Objects.nonNull(service)) {
			SkillRequestDTO skillRequestDTO = mapSkillModelToDTO.apply(skillRequest);
			SkillResponseDTO skillResponseDTO = service.getSkills(skillRequestDTO);
			
			skillResponse = mapSkillDTOToModel.apply(skillResponseDTO);
			if(Objects.nonNull(skillResponse.getError())) {
				HttpStatus status = HttpStatus.resolve((int)skillResponse.getError().getCode());
				return ResponseEntity.status(status).body(skillResponse);
				
			}
			skillResponse.setConnectorName(skillRequest.getConnectorName());
			skillResponse.setWorkspaceType(skillRequest.getWorkspaceType());
		}else {
			logger.error("Invalid source provided");
			skillResponse = new SkillResponse();
			SkillError skillError = new SkillError();
			skillError.setCode(400);
			skillError.setDescription("Invalid source name");
			skillResponse.setError(skillError);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(skillResponse);
		}
		logger.info("End of getSkills");
		return ResponseEntity.status(HttpStatus.OK).body(skillResponse);
		
	}
	
	
	@PostMapping(value="/skill/run", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SkillRunResponse> runProcess(@RequestBody SkillRunRequest skillRunRequest){
		logger.info("Start of Run");
		service = skillServiceFactory.getSkillService(skillRunRequest.getConnectorName());
		SkillRunResponse skillRunResponse = null;
		
		if (Objects.nonNull(service)) {
			SkillRunRequestDTO runRequestDTO = mapProcessRunModelToDTO.apply(skillRunRequest);
			SkillRunResponseDTO skillRunResponseDTO = service.runSkill(runRequestDTO);
			skillRunResponse = mapProcessRunDTOTOModel.apply(skillRunResponseDTO);
			if(Objects.nonNull(skillRunResponse.getError())){
				HttpStatus status = HttpStatus.resolve((int)skillRunResponse.getError().getCode());
				return ResponseEntity.status(status).body(skillRunResponse);
			}
		}else {
			logger.error("Invalid source provided");
			skillRunResponse = new SkillRunResponse();
			SkillError skillError = new SkillError();
			skillError.setCode(400);
			skillError.setDescription("Invalid source name");
			skillRunResponse .setError(skillError);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(skillRunResponse );
		}
		
		logger.info("End of Run");
		return ResponseEntity.status(HttpStatus.OK).body(skillRunResponse);
		
	}
	
	
	
		
	Function<SkillConfigRequest,SkillConfigRequestDTO>  mapProcessConfigModelToDTO = model ->{
			
			SkillConfigRequestDTO skillConfigRequestDTO = new SkillConfigRequestDTO();
			
			skillConfigRequestDTO.setUsername(model.getCrUserName());
			skillConfigRequestDTO.setPassword(model.getCrPassword());
			return skillConfigRequestDTO;
			
   };
		
		
	Function<SkillConfigResponseDTO, SkillConfigResponse> mapProcessConfigDTOToModel = dto -> {
		
		
		SkillConfigResponse skillConfigResponse = new SkillConfigResponse();
		if(Objects.nonNull(dto.getError())) {
			SkillError error = new SkillError();
			error.setCode(dto.getError().getCode());
			error.setDescription(dto.getError().getDescription());
			skillConfigResponse.setError(error);
			return skillConfigResponse;
		}
		
		skillConfigResponse.setAccessToken(dto.getAccessToken());
		skillConfigResponse.setUserId(dto.getUserId());
		return skillConfigResponse;
	};
		
	
	Function<SkillRequest, SkillRequestDTO> mapSkillModelToDTO = model -> {
		
		SkillRequestDTO skillRequestDTO = new SkillRequestDTO();
		skillRequestDTO.setAccessToken(model.getAccessToken());
		skillRequestDTO.setWorkspaceType(model.getWorkspaceType());
		return skillRequestDTO;
		
	};
	
	
	Function<SkillResponseDTO, SkillResponse> mapSkillDTOToModel = dto -> {
		
		
		SkillResponse skillResponse = new SkillResponse();
		
		if(Objects.nonNull(dto.getError())){
			SkillError skillError = new SkillError();
			skillError.setCode(dto.getError().getCode());
			skillError.setDescription(dto.getError().getDescription());
			
			skillResponse.setError(skillError);
			return skillResponse;
		}
		List<SkillInfo> skillInfoList = dto.getSkills().stream().map(dtoSkill ->{
			
			SkillInfo skillInfo = new SkillInfo();
			skillInfo.setSkillId(dtoSkill.getSkillId());
			skillInfo.setSkillName(dtoSkill.getSkillName());
			skillInfo.setSkillDesc(dtoSkill.getSkillDesc());
			skillInfo.setFolderName(dtoSkill.getFolderName());
			return skillInfo;
		}).collect(Collectors.toList());
		skillResponse.setSkills(skillInfoList);
		return skillResponse;
	};
	
	
	Function<SkillRunRequest, SkillRunRequestDTO> mapProcessRunModelToDTO = model-> {
		
		SkillRunRequestDTO skillRunRequestDTO = new SkillRunRequestDTO();
		skillRunRequestDTO.setAccessToken(model.getAccessToken());
		skillRunRequestDTO.setSkillId(model.getSkillId());
		skillRunRequestDTO.setUserId(model.getUserId());
		
		return skillRunRequestDTO;
	};
	
	Function<SkillRunResponseDTO, SkillRunResponse> mapProcessRunDTOTOModel = dto -> {
		
		SkillRunResponse skillRunResponse = new SkillRunResponse();
		if(Objects.nonNull(dto.getError())) {
			SkillError skillError = new SkillError();
			skillError.setCode(dto.getError().getCode());
			skillError.setDescription(dto.getError().getDescription());
			skillRunResponse.setError(skillError);
			return skillRunResponse;
		}
		skillRunResponse.setResult(dto.getResult());
		return skillRunResponse;
	};
	
	
	

}
