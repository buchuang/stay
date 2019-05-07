package com.nysit.stay.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;
@Component
public class ActivitiFontConfig implements ProcessEngineConfigurationConfigurer {

	@Override
	public void configure(SpringProcessEngineConfiguration config) {
		config.setActivityFontName("宋体");
		config.setLabelFontName("宋体");
	}

}
