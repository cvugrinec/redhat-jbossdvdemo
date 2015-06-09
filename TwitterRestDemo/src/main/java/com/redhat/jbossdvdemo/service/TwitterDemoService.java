package com.redhat.jbossdvdemo.service;


import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Service;

import twitter4j.Logger;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class TwitterDemoService {
	
	final static Logger logger = Logger.getLogger(TwitterDemoService.class);
	
	Twitter twitter = null;
	
	private void init(){
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
			.setOAuthConsumerKey("PLEASE USE YOUR OWN OAUTH KEY HERE")
			.setOAuthConsumerSecret("PLEASE USER YOUR OWN CONSUMER SECRET HERE")
			.setOAuthAccessToken("REQUEST THIS WITH THE TWITTER OAUTH TOOLING")
			.setOAuthAccessTokenSecret("REQUEST THIS WITH THE TWITTER OAUTH TOOLING");
		twitter = new TwitterFactory(cb.build()).getInstance();
	}
	
	public JSONObject getTopicMessages(String hashTagTopic) throws TwitterException, JSONException{
		if(twitter==null)
			init();
	    Query query = new Query(hashTagTopic);
	    query.count(100);
	    QueryResult qResult = twitter.search(query);
	    JSONArray jsonArray = new JSONArray();
	    
        JSONObject result = new JSONObject();
        JSONObject root = new JSONObject();
	    
	    for (Status status : qResult.getTweets()) {
		    JSONObject jsonObj = new JSONObject();
		    logger.info("user: "+status.getUser().getScreenName());
		    logger.info("message: "+status.getText());
		    jsonObj.append("user", status.getUser().getScreenName());
	    	jsonObj.append("message", status.getText());
	    	jsonArray.put(jsonObj);
	    }
	    result.put("results",jsonArray);
	    root.put("Root", result);
	    return root;
	}
	
}
