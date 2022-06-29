package com.ibm.aa.adapter.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.aa.adapter.dto.SkillConfigRequestDTO;
import com.ibm.aa.adapter.dto.SkillConfigResponseDTO;
import com.ibm.aa.adapter.dto.SkillErrorDTO;
import com.ibm.aa.adapter.dto.SkillInfoDTO;
import com.ibm.aa.adapter.dto.SkillRequestDTO;
import com.ibm.aa.adapter.dto.SkillResponseDTO;
import com.ibm.aa.adapter.dto.SkillRunRequestDTO;
import com.ibm.aa.adapter.dto.SkillRunResponseDTO;
import com.ibm.aa.adapter.entity.Skill;
import com.ibm.aa.adapter.entity.Token;

/**
 * Service implementation for Automation Anywhere
 * 
 * @author Rakesh R Pai
 *
 */
@Service
public class AASkillServiceImpl implements SkillService {

	Logger logger = LoggerFactory.getLogger(AASkillServiceImpl.class);

	private static final String CONTENT_TYPE = "Content-Type";

	@Autowired
	private Environment env;

	/**
	 * Method to get the access token from user credentials
	 * 
	 * @param skillConfigDTO
	 * @return
	 */
	@Override
	public SkillConfigResponseDTO getToken(SkillConfigRequestDTO skillConfigDTO) {
		SkillConfigResponseDTO responseDTO = new SkillConfigResponseDTO();
		try {
			StringBuilder apiUrl = new StringBuilder(env.getProperty("aa.baseurl"));
			apiUrl.append("v1/authentication");

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(apiUrl.toString());
			httpPost.setHeader(CONTENT_TYPE, env.getProperty("aa.content-type"));

			JSONObject requestJson = new JSONObject();
			requestJson.put("username", skillConfigDTO.getUsername());
			requestJson.put("password", skillConfigDTO.getPassword());

			StringEntity entity = new StringEntity(requestJson.toString());
			httpPost.setEntity(entity);

			CloseableHttpResponse response = client.execute(httpPost);
			if (Objects.nonNull(response) && 200 == response.getStatusLine().getStatusCode()) {
				logger.info("getAccessToken API response code: {}", response.getStatusLine().getStatusCode());
				String responseJsonString = EntityUtils.toString(response.getEntity());
				Token token = new ObjectMapper().readValue(responseJsonString, Token.class);
				responseDTO.setAccessToken(token.getToken());
				responseDTO.setUserId(token.getUser().getId());
			} else {
				logger.error("Incorrect username or password");
				responseDTO.setError(populateErrorDTO(401, "Incorrect username or password"));
			}
			client.close();
		} catch (IOException e) {
			logger.error("Exception while getting access token from Automation Anywhere: {}", e.getMessage());
			responseDTO.setError(populateErrorDTO(500, "Internal server error"));
		}
		return responseDTO;
	}

	/**
	 * Method to fetch the list of automation skills
	 * 
	 * @param skillDTO
	 * @return
	 */
	@Override
	public SkillResponseDTO getSkills(SkillRequestDTO skillDTO) {
		SkillResponseDTO skillResponseDTO = new SkillResponseDTO();
		try {
			StringBuilder apiUrl = new StringBuilder(env.getProperty("aa.baseurl"));
			apiUrl.append("v2/repository/workspaces/").append(skillDTO.getWorkspaceType()).append("/files/list");

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(apiUrl.toString());
			httpPost.setHeader(CONTENT_TYPE, env.getProperty("aa.content-type"));
			httpPost.setHeader("X-Authorization", skillDTO.getAccessToken());

			JSONObject object = new JSONObject();
			JSONObject requestJson = new JSONObject();
			requestJson.put("fields", new ArrayList<>());
			requestJson.put("filter", object);
			requestJson.put("sort", new ArrayList<>());
			requestJson.put("page", object);

			StringEntity entity = new StringEntity(requestJson.toString());
			httpPost.setEntity(entity);
			CloseableHttpResponse response = client.execute(httpPost);
			if (Objects.nonNull(response) && 200 == response.getStatusLine().getStatusCode()) {
				logger.info("getAccessToken API response code: {}", response.getStatusLine().getStatusCode());
				String responseJsonString = EntityUtils.toString(response.getEntity());
				Skill skill = new ObjectMapper().readValue(responseJsonString, Skill.class);
				logger.info("Skill details: {}", skill.toString());

				List<SkillInfoDTO> skills = processSkills(skill);
				skillResponseDTO.setSkills(skills);
			} else if (Objects.nonNull(response) && 401 == response.getStatusLine().getStatusCode()) {
				logger.error("Invalid access token. Login again");
				skillResponseDTO.setError(populateErrorDTO(401, "Session expired. Please login again"));
			} else {
				logger.error("Invalid request parameters");
				skillResponseDTO.setError(populateErrorDTO(400, "Invalid request parameters"));
			}
			client.close();
		} catch (IOException e) {
			logger.error("Exception while getting access token from Automation Anywhere: {}", e.getMessage());
			skillResponseDTO.setError(populateErrorDTO(500, "Internal server error"));
		}
		return skillResponseDTO;
	}

	/**
	 * Method to run the skill
	 * 
	 * @param request
	 * @return
	 */
	@Override
	public SkillRunResponseDTO runSkill(SkillRunRequestDTO skillRunDTO) {
		SkillRunResponseDTO responseDTO = null;
		try {
			StringBuilder apiUrl = new StringBuilder(env.getProperty("aa.baseurl"));
			apiUrl.append("v3/automations/deploy");

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(apiUrl.toString());
			httpPost.setHeader(CONTENT_TYPE, env.getProperty("aa.content-type"));
			httpPost.setHeader("X-Authorization", skillRunDTO.getAccessToken());

			List<String> userIds = new ArrayList<>();
			userIds.add(skillRunDTO.getUserId());
			JSONObject object = new JSONObject();

			JSONObject startJson = new JSONObject();
			startJson.put("fileId", skillRunDTO.getSkillId());
			startJson.put("runAsUserIds", userIds);
			startJson.put("poolIds", new ArrayList<>());
			startJson.put("overrideDefaultDevice", Boolean.FALSE);
			startJson.put("callbackInfo", object);
			startJson.put("botInput", object);

			StringEntity entity = new StringEntity(startJson.toString());
			httpPost.setEntity(entity);
			CloseableHttpResponse response = client.execute(httpPost);
			if (Objects.nonNull(response)) {
				responseDTO = new SkillRunResponseDTO();
				if (200 == response.getStatusLine().getStatusCode()) {
					logger.info("Process started");
					responseDTO.setResult("STARTED");
				} else if (401 == response.getStatusLine().getStatusCode()) {
					logger.error("Invalid access token. Login again");
					responseDTO.setError(populateErrorDTO(401, "Session expired. Please login again"));
					responseDTO.setResult("FAILED");
				} else {
					logger.error("Failed to start the process");
					responseDTO.setError(populateErrorDTO(400, "Invalid request parameters"));
					responseDTO.setResult("FAILED");
				}
			}
			client.close();
		} catch (IOException e) {
			logger.error("Failed to start the process {}", e.getMessage());
			responseDTO.setError(populateErrorDTO(500, "Internal server error"));
			responseDTO.setResult("FAILED");
		}
		return responseDTO;
	}

	/**
	 * Method to filter the result based on taskBot
	 * 
	 * @param skill
	 * @return
	 */
	private List<SkillInfoDTO> processSkills(Skill skill) {
		List<SkillInfoDTO> skills = new ArrayList<>();
		skill.getValue().stream().filter(skillDtl -> "application/vnd.aa.taskbot".equals(skillDtl.getType()))
				.forEach(skillDtl -> {
					SkillInfoDTO skillDTO = new SkillInfoDTO();
					skillDTO.setSkillId(skillDtl.getId());
					skillDTO.setSkillName(skillDtl.getName());
					skillDTO.setSkillDesc(skillDtl.getDescription());
					// Get folder name from path
					String[] folders = skillDtl.getPath().split("\\\\");
					skillDTO.setFolderName(folders[folders.length - 2]);
					skills.add(skillDTO);
				});
		return skills;
	}

	/**
	 * Method to populate error dto
	 * 
	 * @param errorCode
	 * @param description
	 * @return
	 */
	private SkillErrorDTO populateErrorDTO(int errorCode, String description) {
		SkillErrorDTO error = new SkillErrorDTO();
		error.setCode(errorCode);
		error.setDescription(description);
		return error;
	}
}
