package com.zemoga.project.services;

import com.zemoga.project.dto.PortfolioDto;
import com.zemoga.project.exceptions.PortfolioException;
import com.zemoga.project.model.Portfolio;
import com.zemoga.project.store.PortfolioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortfolioService implements IPortfolioService {

	private PortfolioRepository portfolioRepository;

	private ModelMapper modelMapper;

	private ITwitterService twitterService;

	@Autowired
	public PortfolioService(final PortfolioRepository portfolioRepository, final ModelMapper modelMapper,
							final ITwitterService twitterService) {
		this.portfolioRepository = portfolioRepository;
		this.modelMapper = modelMapper;
		this.twitterService = twitterService;
	}

	public PortfolioDto getPortfolio(final Integer id) {

		PortfolioDto portfolioDto = portfolioRepository.findById(id)
													   .map(p -> {
													   		PortfolioDto dto = modelMapper.map(p, PortfolioDto.class);
													   		dto.setTweets(twitterService.getUserTweets(p.getTwitterUserName()));
													   		return dto;
													   }).orElseThrow(() -> new PortfolioException(id));

		return portfolioDto;
	}

	public PortfolioDto savePortfolio(final Integer id, final PortfolioDto newPortfolioDto) {

		Portfolio tempPortfolio = portfolioRepository.findById(id)
												  .map(p -> {
												  	p.setTitle(newPortfolioDto.getTitle());
												  	p.setDescription(newPortfolioDto.getDescription());
												  	p.setImageURL(newPortfolioDto.getImageUrl());
												  	p.setTwitterUserName(newPortfolioDto.getTwitterUserName());
												  	return portfolioRepository.save(p);
												  }).orElseGet(() -> {
													Portfolio portfolio = modelMapper.map(newPortfolioDto, Portfolio.class);
													portfolio.setIdPortfolio(id);
												  	return portfolioRepository.save(portfolio);
												  });

		PortfolioDto newPortfolio = modelMapper.map(tempPortfolio, PortfolioDto.class);
		newPortfolio.setTweets(twitterService.getUserTweets(tempPortfolio.getTwitterUserName()));

		return  newPortfolio;
	}
}
