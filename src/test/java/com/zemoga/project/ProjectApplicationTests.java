package com.zemoga.project;

import static org.assertj.core.api.Assertions.assertThat;

import com.zemoga.project.controllers.PortfolioApiController;
import com.zemoga.project.controllers.PortfolioController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

	@Autowired
	private PortfolioController portfolioController;

	@Autowired
	private PortfolioApiController portfolioApiController;

	@Test
	void contextLoads() {
		assertThat(portfolioController).isNotNull();
		assertThat(portfolioApiController).isNotNull();
	}

}
