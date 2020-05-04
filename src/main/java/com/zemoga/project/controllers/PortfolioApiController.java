package com.zemoga.project.controllers;

import com.zemoga.project.dto.PortfolioDto;
import com.zemoga.project.services.IPortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to handle REST requests sent to Portfolio API
 */
@RestController
@RequestMapping("portfolios")
public class PortfolioApiController {

	private IPortfolioService portfolioService;

	public PortfolioApiController(final IPortfolioService portfolioService) {
		this.portfolioService = portfolioService;
	}

	/**
	 * Endpoint to obtain information about a portfolio.
	 * @param portfolioId portfolio identifier
	 * @return
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PortfolioDto> getPortfolio(@PathVariable("id") final Integer portfolioId) {
		return new ResponseEntity<>(portfolioService.getPortfolio(portfolioId), HttpStatus.OK);
	}

	/**
	 * Endpoint to update or create a new portfolio
	 * @param portfolioId portfolio identifier
	 * @param portfolioDto portfolio data
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<PortfolioDto> updatePortfolio(@PathVariable("id") final Integer portfolioId,
														@RequestBody final PortfolioDto portfolioDto) {
		PortfolioDto p = portfolioService.savePortfolio(portfolioId, portfolioDto);
		return new ResponseEntity<>(p, HttpStatus.OK);
	}
}
