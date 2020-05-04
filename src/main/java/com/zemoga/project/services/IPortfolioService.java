package com.zemoga.project.services;

import com.zemoga.project.dto.PortfolioDto;

/**
 * Interface which contains available portfolio's services
 */
public interface IPortfolioService {

	/**
	 * Obtains a portfolio based on its id
	 * @param id portfolio identifier
	 * @return existing portfolio
	 */
	PortfolioDto getPortfolio(final Integer id);

	/**
	 * Updates or creates a portfolio
	 * @param id portfolio identifier
	 * @param newPortfolioDto portfolio data
	 * @return existing or new portfolio containing data provided
	 */
	PortfolioDto savePortfolio(final Integer id, final PortfolioDto newPortfolioDto);

}
