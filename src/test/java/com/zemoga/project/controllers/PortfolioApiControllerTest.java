package com.zemoga.project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemoga.project.dto.PortfolioDto;
import com.zemoga.project.exceptions.PortfolioException;
import com.zemoga.project.services.IPortfolioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PortfolioApiControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPortfolioService portfolioServiceMock;

	@Test
	public void getPortfolio_shouldReturnResponse_whenSuccessful() throws Exception {
		PortfolioDto portfolioDto = createPortfolio(1, "user description", "user image", "user name");

		when(portfolioServiceMock.getPortfolio(1)).thenReturn(portfolioDto);

		this.mockMvc.perform(get("/portfolios/{id}", 1).contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.portfolioId", is(portfolioDto.getPortfolioId())))
					.andExpect(jsonPath("$.description", is(portfolioDto.getDescription())))
					.andExpect(jsonPath("$.imageUrl", is(portfolioDto.getImageUrl())))
					.andExpect(jsonPath("$.title", is(portfolioDto.getTitle())));

		verify(portfolioServiceMock, times(1)).getPortfolio(1);
	}

	@Test
	public void getPortfolio_shouldFail_whenIdIsInvalid() {
		when(portfolioServiceMock.getPortfolio(2)).thenThrow(new PortfolioException(2));

		try {
			this.mockMvc.perform(get("/portfolios/{id}", 2));
		} catch (Exception e) {
			assertTrue(e.getCause() instanceof PortfolioException);
		}

		verify(portfolioServiceMock, times(1)).getPortfolio(2);
	}

	@Test
	public void updatePortfolio_shouldReturnPortfolio_whenSaved() throws Exception {
		PortfolioDto portfolioDto = createPortfolio(1, "user2 description", "user2 image", "user2 name");
		PortfolioDto updatedPortfolioDto = createPortfolio(0, "user2 description", "user2 image", "user2 name");

		when(portfolioServiceMock.savePortfolio(anyInt(), any(PortfolioDto.class))).thenReturn(portfolioDto);

		this.mockMvc.perform(put("/portfolios/{id}", 1)
									 .content(asJsonString(updatedPortfolioDto))
									 .contentType(MediaType.APPLICATION_JSON)
									 .accept(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.portfolioId", is(portfolioDto.getPortfolioId())))
					.andExpect(jsonPath("$.description", is(portfolioDto.getDescription())))
					.andExpect(jsonPath("$.imageUrl", is(portfolioDto.getImageUrl())))
					.andExpect(jsonPath("$.title", is(portfolioDto.getTitle())));

		verify(portfolioServiceMock, times(1)).savePortfolio(anyInt(), any(PortfolioDto.class));
	}

	private PortfolioDto createPortfolio(int id, String description, String image, String name) {
		PortfolioDto portfolioDto = new PortfolioDto();
		portfolioDto.setPortfolioId(id);
		portfolioDto.setDescription(description);
		portfolioDto.setImageUrl(image);
		portfolioDto.setTitle(name);

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
