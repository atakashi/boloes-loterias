package com.pangares.resultados.batch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangares.resultados.dto.MegaSenaResultadoDto;
import com.pangares.resultados.endpoint.MegaSenaResultadoEndpoint;
import com.pangares.resultados.entities.MegaSenaResultadoEntity;
import com.pangares.resultados.repository.MegaSenaResultadoRepository;
import com.pangares.resultados.services.MegaSenaResultadoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	private JobBuilderFactory jobBuilderFactory;
	private StepBuilderFactory stepBuilderFactory;
	private MegaSenaResultadoEndpoint endpoint;
	private MegaSenaResultadoService service;
	private MegaSenaResultadoRepository repository;
	private ObjectMapper mapper;
	
	@Autowired
	public BatchConfiguration(JobBuilderFactory jobBuilderFactory,
			StepBuilderFactory stepBuilderFactory,
			MegaSenaResultadoEndpoint endpoint, MegaSenaResultadoService service,
			MegaSenaResultadoRepository repository, ObjectMapper mapper) {
		this.jobBuilderFactory = jobBuilderFactory;
		this.stepBuilderFactory = stepBuilderFactory;
		this.endpoint = endpoint; 
		this.service= service;
		this.repository = repository;
		this.mapper = mapper;
	}

	private static Object LOCK = new Object();
	
	@Bean
	public ItemReader<MegaSenaResultadoDto> reader() {
		log.info("reader: starting");

		return new JsonItemReaderBuilder<MegaSenaResultadoDto>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(mapper, MegaSenaResultadoDto.class))
				.resource(new FileSystemResource("docs/sorteios.json"))
				.name("megasenaResultadoJsonReader")
				.build();
		
		/* TODO: Version for CEF Endpoint
		List<MegaSenaResultadoDto> dtos = new ArrayList<>();
		Long nextNumeroConcurso = this.service.getLastNumeroConcurso() + 1;
		Exception exception = null;
		do {
			try {
				
				MegaSenaResultadoDto dto = null;
				synchronized (LOCK) {
					dto = this.endpoint.getResultado(nextNumeroConcurso);
					LOCK.wait(1000);	// 1 second = 1000 milliseconds
				}

				dtos.add(dto);
				nextNumeroConcurso++;
			} catch (Exception e) {
				exception = e;
				log.warn("exception: " + e);
			}
		} while (exception == null);
		
		log.info("reader: end successful!");
		return new ListItemReader<MegaSenaResultadoDto>(dtos);
		 */
	}
	
	@Bean
	public ItemProcessor processor() {
		return new MegaSenaResultadoItemProcessor<MegaSenaResultadoDto, MegaSenaResultadoEntity>();
	}
	
	@Bean
	public ItemWriter<MegaSenaResultadoEntity> writer() {
		RepositoryItemWriterBuilder<MegaSenaResultadoEntity> builder =
				new RepositoryItemWriterBuilder<MegaSenaResultadoEntity>(); 
		
		RepositoryItemWriter<MegaSenaResultadoEntity> writer = builder
				.repository(repository)
				.methodName("save")
				.build();
		
		return writer;
	}
	
	@Bean
	public Job importMegaSenaResultados() {
		return this.jobBuilderFactory.get("importMegaSenaResultados")
				.incrementer(new RunIdIncrementer())
				.flow(step1())
				.end()
				.build();
	}
	
	@Bean
	public Step step1() {
		return this.stepBuilderFactory.get("step1")
				.chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}
	
}
