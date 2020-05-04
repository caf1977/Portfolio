package com.zemoga.project.dto;

import org.springframework.social.twitter.api.Tweet;

import java.util.List;

/**
 * Class that represents a portfolio view object
 */
public class PortfolioDto {

	private int portfolioId;

	private String description;

	private String imageUrl;

	private String title;

	private String twitterUserName;

	private List<Tweet> tweets;

	public int getPortfolioId() {

		return portfolioId;
	}

	public void setPortfolioId(int portfolioId) {

		this.portfolioId = portfolioId;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getImageUrl() {

		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {

		this.imageUrl = imageUrl;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getTwitterUserName() {

		return twitterUserName;
	}

	public void setTwitterUserName(String twitterUserName) {

		this.twitterUserName = twitterUserName;
	}

	public List<Tweet> getTweets() {

		return tweets;
	}

	public void setTweets(List<Tweet> tweets) {

		this.tweets = tweets;
	}
}
