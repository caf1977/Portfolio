package com.zemoga.project.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TwitterServiceTest {

	@Mock
	private Twitter twitter;

	@InjectMocks
	private TwitterService twitterService;

	@Test
	public void getUserTweets_shouldReturnTweets_ifNameFound() {
		String twitterUserName = "Test";
		TimelineOperations timelineOperations = Mockito.mock(TimelineOperations.class);

		when(twitter.timelineOperations()).thenReturn(timelineOperations);
		when(timelineOperations.getUserTimeline(twitterUserName, 5)).thenReturn(createTweets());

		List<Tweet> userTweets = twitterService.getUserTweets(twitterUserName);

		assertThat(userTweets).isNotNull()
							  .asList().size().isEqualTo(5);
	}

	private List<Tweet> createTweets() {
		List<Tweet> tweets = new ArrayList<>();

		for (int i=0; i<5; i++) {
			tweets.add(Mockito.mock(Tweet.class));
		}

		return tweets;
	}
}
