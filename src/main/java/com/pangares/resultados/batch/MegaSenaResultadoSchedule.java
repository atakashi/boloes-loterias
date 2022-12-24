package com.pangares.resultados.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MegaSenaResultadoSchedule {

	@Scheduled(cron = "0 30 0 * * *")
	public void schedulingForResultados() {
		SpringApplication.run(BatchConfiguration.class, null);
	}
	
}
