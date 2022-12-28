package com.pangares.resultados.batch;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pangares.resultados.repository.QuadranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MegaSenaQuandranteProcessor {
    private final QuadranteRepository repository;

    private final ObjectMapper mapper;
}
