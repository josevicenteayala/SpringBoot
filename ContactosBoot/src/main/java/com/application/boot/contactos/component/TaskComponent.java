package com.application.boot.contactos.component;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskComponent")
public class TaskComponent {

	private static final Logger LOGGER = LoggerFactory.getLogger(TaskComponent.class);
	
	@Scheduled(fixedDelay = 5000)
	public void doTask() {
		LOGGER.info("TIME IS: "+Instant.now());
	}
	
}
