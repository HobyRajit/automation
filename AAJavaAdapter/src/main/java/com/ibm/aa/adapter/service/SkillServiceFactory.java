package com.ibm.aa.adapter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory to return the skill service based on source
 * 
 * @author Rakesh R Pai
 *
 */
@Component
public class SkillServiceFactory {

	Logger logger = LoggerFactory.getLogger(SkillServiceFactory.class);
	
	@Autowired
	private AASkillServiceImpl aaSkillServiceImpl;
	
	/**
	 * Factory to get the service implementation based on connector
	 * 
	 * @param connectorName
	 * @return
	 */
	public SkillService getSkillService(String connectorName) {
		if ("AutomationAnyWhere".equalsIgnoreCase(connectorName)) {
			logger.info("Fetching Processes from UiPath");
			return aaSkillServiceImpl;
		}
		logger.error("No source found");
		return null;
	}
}
