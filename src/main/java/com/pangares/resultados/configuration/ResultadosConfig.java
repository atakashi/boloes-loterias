package com.pangares.resultados.configuration;


import static com.pangares.resultados.configuration.ResultadoConstants.BRAZILIAN_DATE_FORMAT;

import java.time.format.DateTimeFormatter;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Configuration
public class ResultadosConfig {
	
	@Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizerForBrasilianDateFormat() {
        return builder -> {
            builder.simpleDateFormat(BRAZILIAN_DATE_FORMAT);
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(BRAZILIAN_DATE_FORMAT)));
        };
    }

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
