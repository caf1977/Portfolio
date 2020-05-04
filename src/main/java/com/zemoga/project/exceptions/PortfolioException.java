package com.zemoga.project.exceptions;

/**
 * Class to handle exceptions
 */
public class PortfolioException extends RuntimeException {

	public PortfolioException(Integer id) {
		super("Error processing portfolio with id: " + id);
	}
}
