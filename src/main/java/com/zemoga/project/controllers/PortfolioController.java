package com.zemoga.project.controllers;

import com.zemoga.project.dto.PortfolioDto;
import com.zemoga.project.services.IPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller to handle web requests
 */
@Controller
public class PortfolioController {

	@Autowired
	IPortfolioService portfolioService;

	/**
	 * Obtains a portfolio based on its id
	 * @param portfolioId portfolio identifier
	 * @param model view model
	 * @return
	 */
	@GetMapping("/portfolio")
	public String getPortfolio(@RequestParam(name="id", required = false, defaultValue="1") Integer portfolioId, Model model) {
		PortfolioDto portfolioDto = portfolioService.getPortfolio(portfolioId);
		model.addAttribute("name", portfolioDto.getTitle());
		model.addAttribute("imageUrl", portfolioDto.getImageUrl());
		model.addAttribute("description", portfolioDto.getDescription());
		model.addAttribute("tweets", portfolioDto.getTweets());

		return "portfolio";
	}

}
