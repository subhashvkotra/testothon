package com.testautothon.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class WriteJSON {

	@SuppressWarnings("unchecked")
	public JsonObject prepareBiographiesJSON(String name, String handle_name, int follower_count, int following_count) {
		JsonObject biographies = new JsonObject();
		biographies.addProperty("name", name);
		biographies.addProperty("handle_name", handle_name);
		biographies.addProperty("follower_count", follower_count);
		biographies.addProperty("following_count", following_count);
		
		return biographies;
	}
	
	@SuppressWarnings("unchecked")
	public void prepareOutputJson(int retweetCount, int likeCount, String name, String handle_name, int follower_count, int following_count) {
		/*JSONObject output = new JSONObject();
		output.put("top_retweet_count", retweetCount);
		output.put("top_like_count", likeCount);*/

		JsonObject output = new JsonObject();
		JsonElement listofBiographies = prepareBiographiesJSON(name, handle_name, follower_count, following_count);
		output.addProperty("top_retweet_count", retweetCount);
		output.addProperty("top_like_count", likeCount);
		output.add("biographies", listofBiographies);
	}

}
