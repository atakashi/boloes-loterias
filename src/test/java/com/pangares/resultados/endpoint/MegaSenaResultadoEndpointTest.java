package com.pangares.resultados.endpoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.pangares.resultados.configuration.ResultadosConfig;
import com.pangares.resultados.dto.MegaSenaResultadoDto;

@RestClientTest(MegaSenaResultadoEndpoint.class)
@ActiveProfiles(value = "test")
@Import(ResultadosConfig.class)
public class MegaSenaResultadoEndpointTest {

	@Value(value = "${caixa.resultados.api.base.url}")
	protected String BASE_URL;
	
	@Value(value = "${caixa.resultados.api.endpoint.megasena}")
	private String RESULTADO_ENDPOINT;

	@Autowired
	private MegaSenaResultadoEndpoint endpoint;
	
	@Autowired
	protected RestTemplate restTemplate;

	@Autowired
	private MockRestServiceServer mockRestServiceServer;
	
    private Long numeroConcurso;
	private MegaSenaResultadoDto dtoMock;
	
	@Test
	public void getResultadoTest() {

		this.mockRestServiceServer.expect(MockRestRequestMatchers.requestTo(BASE_URL + String.format(RESULTADO_ENDPOINT, numeroConcurso)))
				.andRespond(MockRestResponseCreators.withSuccess(
						"{\"dataApuracao\": \"30/06/2004\", \"numero\": 576, \"dezenasSorteadasOrdemSorteio\": [\"18\",\"43\",\"52\",\"39\",\"07\",\"08\"], \"listaDezenas\": [\"07\",\"08\",\"18\",\"39\",\"43\",\"52\"]}", 
						MediaType.APPLICATION_JSON));

		MegaSenaResultadoDto resultadoDto = this.endpoint.getResultado(numeroConcurso);

		assertEquals(dtoMock, resultadoDto);
	}
	
	@Test
	public void throwRestClientExceptionTest() {
		 
		this.mockRestServiceServer.expect(MockRestRequestMatchers.requestTo(BASE_URL + String.format(RESULTADO_ENDPOINT, Long.MAX_VALUE)))
				.andRespond(MockRestResponseCreators.withException(new IOException()));

		assertThrows(ResourceAccessException.class, () -> endpoint.getResultado(Long.MAX_VALUE));
		this.mockRestServiceServer.reset();

		this.mockRestServiceServer.expect(MockRestRequestMatchers.requestTo(BASE_URL + String.format(RESULTADO_ENDPOINT, Long.valueOf(2))))
				.andRespond(MockRestResponseCreators.withStatus(HttpStatus.BAD_REQUEST));

		assertThrows(RestClientException.class, () -> endpoint.getResultado(Long.valueOf(2)));
	}

	@BeforeEach
	public void setup() {
		this.numeroConcurso = Long.valueOf(576);
		this.dtoMock = MegaSenaResultadoDto.builder()
				.numeroConcurso(numeroConcurso)
				.dataApuracao(LocalDate.of(2004, 06, 30))
				.dezenasSorteadasOrdemSorteio(Arrays.<Integer>asList(18, 43, 52, 39, 7, 8))
				.listaDezenas(Arrays.<Integer>asList(7, 8, 18, 39, 43, 52))
				.build();
	}
	
	@AfterEach
	public void setupAfter() {
		this.mockRestServiceServer.reset();
	}
	
}
