package com.zemoga.project.services;

import com.zemoga.project.dto.PortfolioDto;
import com.zemoga.project.exceptions.PortfolioException;
import com.zemoga.project.model.Portfolio;
import com.zemoga.project.store.PortfolioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PortfolioServiceTest {

	@Mock
	private PortfolioRepository portfolioRepository;

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private TwitterService twitterService;

	@InjectMocks
	private PortfolioService portfolioService;

	@Test
	public void getPortfolio_shouldReturnPortfolio_whenFound() {
		Portfolio portfolio = createPortfolio(1, "desc1", "image1", "name1", "twitter");

		when(portfolioRepository.findById(1)).thenReturn(Optional.of(portfolio));
		when(modelMapper.map(portfolio, PortfolioDto.class)).thenReturn(createPortfolioDto(portfolio));
		when(twitterService.getUserTweets("twitter")).thenReturn(new ArrayList<>());

		PortfolioDto dto = portfolioService.getPortfolio(1);

		assertThat(dto.getPortfolioId(), is(portfolio.getIdPortfolio()));
		assertThat(dto.getDescription(), is(portfolio.getDescription()));
		assertThat(dto.getImageUrl(), is(portfolio.getImageURL()));
		assertThat(dto.getTitle(), is(portfolio.getTitle()));
		assertThat(dto.getTwitterUserName(), is(portfolio.getTwitterUserName()));
	}

	@Test
	public void getPortfolio_shouldReturnException_whenNotFound() {
		when(portfolioRepository.findById(2)).thenReturn(Optional.empty());

		try {
			portfolioService.getPortfolio(2);
		} catch (Exception e) {
			assertTrue(e instanceof PortfolioException);
		}
	}

	@Test
	public void savePortfolio_shouldUpdatePortfolio_whenFound() {
		Portfolio portfolio = createPortfolio(1, "desc1", "image1", "name1", "twitter");
		PortfolioDto portfolioDto = createPortfolioDto(createPortfolio(1, "desc2", "image2", "name2", "twitter2"));

		when(portfolioRepository.findById(1)).thenReturn(Optional.of(portfolio));
		when(portfolioRepository.save(portfolio)).thenReturn(portfolio);
		when(modelMapper.map(portfolio, PortfolioDto.class)).thenReturn(portfolioDto);
		when(twitterService.getUserTweets("twitter2")).thenReturn(new ArrayList<>());

		PortfolioDto dto = portfolioService.savePortfolio(1, portfolioDto);

		assertThat(dto.getPortfolioId(), is(portfolio.getIdPortfolio()));
		assertThat(dto.getDescription(), is(portfolio.getDescription()));
		assertThat(dto.getImageUrl(), is(portfolio.getImageURL()));
		assertThat(dto.getTitle(), is(portfolio.getTitle()));
		assertThat(dto.getTwitterUserName(), is(portfolio.getTwitterUserName()));
	}

	@Test
	public void savePortfolio_shouldCreatePortfolio_whenNotFound() {
		Portfolio portfolio = createPortfolio(0, "desc2", "image2", "name2", "twitter2");
		PortfolioDto portfolioDto1 = createPortfolioDto(createPortfolio(0, "desc2", "image2", "name2", "twitter2"));
		PortfolioDto portfolioDto2 = createPortfolioDto(createPortfolio(2, "desc2", "image2", "name2", "twitter2"));

		when(portfolioRepository.findById(2)).thenReturn(Optional.empty());
		when(portfolioRepository.save(portfolio)).thenReturn(portfolio);
		when(modelMapper.map(portfolioDto1, Portfolio.class)).thenReturn(portfolio);
		when(modelMapper.map(portfolio, PortfolioDto.class)).thenReturn(portfolioDto2);
		when(twitterService.getUserTweets("twitter2")).thenReturn(new ArrayList<>());

		PortfolioDto dto = portfolioService.savePortfolio(2, portfolioDto1);

		assertThat(dto.getPortfolioId(), is(2));
		assertThat(dto.getDescription(), is(portfolio.getDescription()));
		assertThat(dto.getImageUrl(), is(portfolio.getImageURL()));
		assertThat(dto.getTitle(), is(portfolio.getTitle()));
		assertThat(dto.getTwitterUserName(), is(portfolio.getTwitterUserName()));
	}

	private Portfolio createPortfolio(int id, String description, String image, String name, String twitterName) {
		Portfolio portfolio = new Portfolio();
		portfolio.setIdPortfolio(id);
		portfolio.setDescription(description);
		portfolio.setImageURL(image);
		portfolio.setTitle(name);
		portfolio.setTwitterUserName(twitterName);

		return portfolio;
	}

	private PortfolioDto createPortfolioDto(Portfolio portfolio) {
		PortfolioDto portfolioDto = new PortfolioDto();
		portfolioDto.setPortfolioId(portfolio.getIdPortfolio());
		portfolioDto.setDescription(portfolio.getDescription());
		portfolioDto.setImageUrl(portfolio.getImageURL());
		portfolioDto.setTitle(portfolio.getTitle());
		portfolioDto.setTwitterUserName(portfolio.getTwitterUserName());

		return portfolioDto;
	}
}
