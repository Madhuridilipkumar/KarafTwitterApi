package com.krf.pkg;

import static org.junit.Assert.*;
import java.util.Random;
import org.junit.Test;
import twitter4j.Category;
import twitter4j.ResponseList;
import twitter4j.TwitterException;
import twitter4j.Status;
import java.util.List;

public class MainServletTest {

	MainServlet twitterApi = new MainServlet();

	@Test
	public void testApiGetHomeTimeline() throws TwitterException {
		String posts = "";
		posts = twitterApi.apiGetHomeTimeline();
		assertTrue("Theres should be response list with timeline posts", posts != null);
	}

	@Test
	public void testApiUpdateStatus() throws TwitterException {
		String beforeStatus = twitterApi.apiGetHomeTimeline();
		Random rand = new Random(System.currentTimeMillis());
		int x = rand.nextInt();
		twitterApi.apiUpdateStatus("Titan is testing " + x);
		String afterStatus = twitterApi.apiGetHomeTimeline();
		assertTrue(afterStatus.length() > beforeStatus.length());
	}

	@Test
	public void testApiCreateUserlist() throws TwitterException {
		String beforeCreateList = twitterApi.apiGetUserlist();
		Random rand = new Random(System.currentTimeMillis());
		int x = rand.nextInt();
		twitterApi.apiCreateUserlist("ABCD" + x);
		String afterCreateList = twitterApi.apiGetUserlist();
		assertTrue(afterCreateList.length() > beforeCreateList.length());
	}

	@Test
	public void testApiDestroyUserlist() throws TwitterException {
		Random rand = new Random(System.currentTimeMillis());
		int x = rand.nextInt();
		twitterApi.apiCreateUserlist("ABCDEEEEEE" + x);
		String beforeDestroyList = twitterApi.apiGetUserlist();
		twitterApi.apiDestroyUserlist("ABCDEEEEEE" + x);
		String afterDestroyList = twitterApi.apiGetUserlist();
		assertTrue(afterDestroyList.length() < beforeDestroyList.length());
	}

	@Test
	public void testApiGetUserlist() throws TwitterException {
		String userlist = "";
		userlist = twitterApi.apiGetUserlist();
		assertNotNull(userlist, "There should be response string of User list");
	}

	@Test
	public void testSearchQuerys() throws TwitterException {
		List<Status> tweets = null;
		String keyword = "independence";
		tweets = twitterApi.searchQuerys(keyword);
		assertNotNull("There should be response list of tweets", tweets);
		assertTrue(tweets.toString().toLowerCase().contains(keyword));
	}

	@Test
	public void testGetPrivacyPolicy() throws TwitterException {
		String policy = "";
		policy = twitterApi.getPrivacyPolicy();
		assertNotNull("There should be resposnse list with timeline posts", policy);
		assertTrue("Checks for the condition that Policy content should contain the substring Privacy Policy",
				policy.contains("Privacy Policy"));
	}

	@Test
	public void testGetSuggestedUserCategories() throws TwitterException {
		ResponseList<Category> categoryList = null;
		categoryList = twitterApi.getSuggestedUserCategories();
		assertTrue("There should be list with atleast 1 category", categoryList.size() > 0);
		assertNotNull("There should be resposnse list with timeline posts", categoryList);

	}

}
