package com.pangares.resultados;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


//@SpringBootTest
public class ResultadosEndpointTest {

//	@Autowired
//	private AbstractResultadosEndpoint endpoint;
	
////	@Test
//	public void isPresentResultadoMegaSenaJsonFileTest() {
//		try {
//			File file = ResourceUtils.getFile("classpath:json/resultados-megasena.json");
//			//String str = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
//			
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//			mapper.setSerializationInclusion(Include.NON_NULL);
//			
////			mapper.readValue(file, null)
//			
//			
//			
//			assertTrue(file.exists());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail();
//		}
//	}
//	
//	
//	@Test
//	public void getAllMegaSenaResultadosByDownloadBySite() {
//		
//		try {
//			List<MegaSenaResultado> resultados = this.endpoint.getAllMegaSenaResultadosByDownloadBySite();
//			assertNotNull(resultados);
//			
//		} catch (FailingHttpStatusCodeException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			fail();
//		}
//	}
	
}
