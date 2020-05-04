package com.zemoga.project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for model transformations
 */
@Configuration
public class AppConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
