package com.zemoga.project.services;

import org.springframework.social.twitter.api.Tweet;

import java.util.List;

/**
 * Interface to handle Twitter operations
 */
public interface ITwitterService {

	/**
	 * Gets user time line
	 * @param twitterUserName
	 * @return list of last 5 tweets
	 */
	List<Tweet> getUserTweets(final String twitterUserName);

}
