package com.redhat.jbossdvdemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import twitter4j.TwitterException;

@Controller
@Path(TwitterDemoResource.JBS_URL)
public class TwitterDemoResource
{
   public static final String JBS_URL = "/twitter";
   
   @Autowired
   TwitterDemoService service;

   @GET
   @Produces({MediaType.APPLICATION_JSON})
   @Path("search/{hashtag}/")
   public String getTopicMessages(@PathParam("hashtag") String hashtag) throws JSONException, TwitterException{
	   JSONObject jsonObj = service.getTopicMessages(hashtag);
	   return jsonObj.toString();
   }
   
}
