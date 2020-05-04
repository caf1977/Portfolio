package com.zemoga.project.controllers;

import com.zemoga.project.dto.PortfolioDto;
import com.zemoga.project.exceptions.PortfolioException;
import com.zemoga.project.services.IPortfolioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PortfolioControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPortfolioService portfolioServiceMock;

	@Test
	public void getPortfolio_shouldReturnTemplate_whenSuccessful() throws Exception {
		when(portfolioServiceMock.getPortfolio(1)).thenReturn(createPortfolio());

		this.mockMvc.perform(get("/portfolio?id={id}", 1))
					.andExpect(status().isOk())
					.andExpect(content().string(containsString("Zemoga's Portfolio Test")))
					.andExpect(model().attribute("name", "user name"))
					.andExpect(model().attribute("description", "user description"))
					.andExpect(model().attribute("imageUrl", "user image"));

		verify(portfolioServiceMock, times(1)).getPortfolio(1);
	}

	@Test
	public void getPortfolio_shouldFail_whenIdIsInvalid() {
		when(portfolioServiceMock.getPortfolio(2)).thenThrow(new PortfolioException(2));

		try {
			this.mockMvc.perform(get("/portfolio?id={id}", 2));
		} catch (Exception e) {
			assertTrue(e.getCause() instanceof PortfolioException);
		}

		verify(portfolioServiceMock, times(1)).getPortfolio(2);
	}

	private PortfolioDto createPortfolio() {
		PortfolioDto portfolioDto = new PortfolioDto();
		portfolioDto.setPortfolioId(1);
		portfolioDto.setDescription("user description");
		portfolioDto.setImageUrl("user image");
		portfolioDto.setTitle("user name");

		return portfolioDto;
	}
}
