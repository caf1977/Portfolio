package com.zemoga.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TwitterService implements ITwitterService {

	private static final int NUMBER_OF_TWEETS = 5;

	private Twitter twitter;

	@Autowired
	public TwitterService(final Twitter twitter) {
		this.twitter = twitter;
	}

	public List<Tweet> getUserTweets(final String twitterUserName) {
		List<Tweet> userTimeline = new ArrayList<>();

		try {
			userTimeline = twitter.timelineOperations().getUserTimeline(twitterUserName, NUMBER_OF_TWEETS);
		} catch (Exception e) {
		}
		return userTimeline;
	}
}
