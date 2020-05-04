package com.zemoga.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that represents a portfolio entity
 */
@Entity
@Table(name = "portfolio")
public class Portfolio {

	@Id
	@Column(name = "idportfolio")
	private int idPortfolio;

	@Column(name = "description")
	private String description;

	@Column(name = "twitter_user_name")
	private String twitterUserName;

	@Column(name = "title")
	private String title;

	@Column(name = "image_url")
	private String imageURL;

	public int getIdPortfolio() {

		return idPortfolio;
	}

	public void setIdPortfolio(int idPortfolio) {

		this.idPortfolio = idPortfolio;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getTwitterUserName() {

		return twitterUserName;
	}

	public void setTwitterUserName(String twitterUserName) {

		this.twitterUserName = twitterUserName;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getImageURL() {

		return imageURL;
	}

	public void setImageURL(String imageURL) {

		this.imageURL = imageURL;
	}
}
