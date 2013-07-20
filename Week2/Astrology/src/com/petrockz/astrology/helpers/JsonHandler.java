package com.petrockz.astrology.helpers;

import org.json.JSONObject;
import org.json.JSONException;
import com.petrockz.astrology.helpers.Signs;

public class JsonHandler {

	public class JSON {

		//builds JSON mainObject 
		public JSONObject buildJSON() {

			JSONObject mainObject = new JSONObject();
			try {
				JSONObject dataObject = new JSONObject();

				// Create a temporary object
				for (Signs items : Signs.values()) {
					JSONObject tempObject = new JSONObject();

					//Sets data into JSON temp object 
					tempObject.put("signCount", items.setSignCount());
					tempObject.put("signName", items.setSignName());
					
					//Sets temp object into data object 
					dataObject.put(items.name().toString(), tempObject);
				}
				
				mainObject.put("data", dataObject);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return mainObject;
		}

		//reads the json from the object
		public String readJSON(String selected){
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
	
	
}
