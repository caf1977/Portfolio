package com.zemoga.project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.project.dto.PortfolioDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PortfolioApiIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	private PortfolioDto portfolioDto;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.portfolioDto = createPortfolio();
	}

	@Test
	public void givenWac_whenServletContext_thenItProvidesPortfolioApiController() {
		ServletContext servletContext = wac.getServletContext();

		Assert.assertNotNull(servletContext);
		Assert.assertTrue(servletContext instanceof MockServletContext);
		Assert.assertNotNull(wac.getBean("portfolioApiController"));
	}

	@Test
	public void verifyPortfolioURIs_whenMockMVC_thenVerifyResponse() throws Exception {

		//Create or update portfolio
		this.mockMvc.perform(put("/portfolios/{id}", 5000)
									 .content(asJsonString(portfolioDto))
									 .contentType(MediaType.APPLICATION_JSON)
									 .accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.portfolioId", is(portfolioDto.getPortfolioId())));

		//Retrieve portfolio information
		this.mockMvc.perform(get("/portfolios/{id}", 5000).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.portfolioId", is(portfolioDto.getPortfolioId())))
					.andExpect(jsonPath("$.description", is(portfolioDto.getDescription())))
					.andExpect(jsonPath("$.imageUrl", is(portfolioDto.getImageUrl())))
					.andExpect(jsonPath("$.title", is(portfolioDto.getTitle())));
	}

	private PortfolioDto createPortfolio() {
		PortfolioDto portfolioDto = new PortfolioDto();
		portfolioDto.setPortfolioId(5000);
		portfolioDto.setDescription("Desc5000");
		portfolioDto.setImageUrl("Image5000");
		portfolioDto.setTitle("Name5000");
		portfolioDto.setTwitterUserName("Twitter5000");

		return portfolioDto;
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
