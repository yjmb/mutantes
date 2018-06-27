package com.mercadolibre.mutantes;

import com.mercadolibre.mutantes.api.DnaRequest;
import com.mercadolibre.mutantes.api.DnaResponse;
import com.mercadolibre.mutantes.service.DnaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MutantesApplicationTests {

	@Autowired
	private DnaService dnaService;


	@Test
	public void testIsMutant() {
		String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
		DnaRequest dnaRequest = new DnaRequest();
		dnaRequest.setDnaSequencing(dna);
		DnaResponse dnaResponse = dnaService.isMutant(dnaRequest);
		Assert.assertNotNull(dnaResponse);

	}

}
