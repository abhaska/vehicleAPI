package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PricingServiceApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<Price> json;


	@Test
	public void contextLoads() {
	}

	@Test
	public void getPricing() throws Exception {
		Price p = getPrice();
		String uri = "/services/price?vehicleId=1";
		mvc.perform(
				get(uri)
				.content(json.write(p).getJson())
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}

	private Price getPrice() {
		Price p = new Price();
		p.setCurrency("d");
		p.setPrice(new BigDecimal("24.455"));
		p.setVehicleId(1L);

		return p;
	}

	}


