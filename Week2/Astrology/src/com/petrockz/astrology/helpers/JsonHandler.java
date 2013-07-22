package com.petrockz.astrology.helpers;

import org.json.JSONObject;
import org.json.JSONException;
import com.petrockz.astrology.helpers.Signs;

public class JsonHandler {



		//builds JSON signsObject 
		public static JSONObject buildJSON() {

			JSONObject signsObject = new JSONObject();
			try {
				JSONObject queryObject = new JSONObject();

				// Create a temporary object with for loop 
				for (Signs sign : Signs.values()) {
				
					JSONObject tempObject = new JSONObject();

					//Sets data into JSON temp object 
//					tempObject.put("signCount", sign.getSignCount());
					tempObject.put("signName", sign.getSignName());
					
					//Sets temp object into data object 
					queryObject.put(sign.name().toString(), tempObject);
				}
				
				signsObject.put("data", queryObject);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return signsObject;
		}

		//reads the JSON from the object
		public static String readJSON(String selected){
			String result, signName;

			JSONObject object = buildJSON();

			try {
				signName = object.getJSONObject("data").getJSONObject(selected).getString("signName");
				

				result = "Sign: "+ signName;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = e.toString();
			}		

			return result;
		}
	
	
	
}
