package com.petrockz.chucknorris;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.petrockz.chucknorris.lib.GetJokeForm;
import com.petrockz.chucknorris.lib.JokeDisplay;
import com.petrockz.chucknorris.lib.NetworkConnection;
import com.petrockz.chucknorris.lib.ReadWrite;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	Context _context;
	LinearLayout _appLayout;
	GetJokeForm _getJoke;
	JokeDisplay _joke;
	Boolean _connected = false;
	HashMap<String, String> _history;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 _context = this;
		 _appLayout = new LinearLayout(_context);
		 _history = new HashMap<String, String>();
		 
		 _getJoke = new GetJokeForm(_context, "First Name", "Last Name", "Get Chucked!", "Get Chucked Randomly!");
		
		 // Add Get handler for personalization
		 
//		 EditText firstName = _getJoke.getFirstName();
//		 EditText lastName = _getJoke.getLastName();
		 Button getButton = _getJoke.getButton();
//		 Button randomButton = _getJoke.getRandom();
		 
		 getButton.setOnClickListener(new OnClickListener(){
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Log.i("CLICK HANDLED",_getJoke.getFirstName().getText().toString() +" " + _getJoke.getLastName().getText().toString());
				grabJoke(_getJoke.getFirstName().getText().toString(), _getJoke.getLastName().getText().toString());
			}
			 
		 });
		
		 // Detect NetConn
		 
		 _connected = NetworkConnection.getConnectionStatus(_context);
		 if (_connected) {
			Log.i("NETWORK CONNECTION", NetworkConnection.getConnectionType(_context));
		}
		 
		 
		 _joke = new JokeDisplay(_context);
		 
		 _appLayout.addView(_getJoke);	 
		 _appLayout.addView(_joke);
		 _appLayout.setOrientation(LinearLayout.VERTICAL);
		setContentView(_appLayout);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void grabJoke(String firstName, String lastName){
		String baseURL = "http://api.icndb.com/jokes/random";
		String nameQuery = "?firstName="+firstName+"&amp;lastName="+lastName+"";
//		String qs;
//		
//		try {
//			qs = URLEncoder.encode(nameQuery, "UTF-8");
//			Log.i("URL", qs);
//		} catch (Exception e) {
//			// TODO: handle exception
//			Log.e("BAD URL", "Encoding issue");
//			qs = "";
//		}
		URL finalURL;
		try {
			finalURL = new URL(baseURL+nameQuery);
			Log.i("FINAL URL", finalURL.toString());
			JokeRequest jr = new JokeRequest();
			jr.execute(finalURL);
			
			
		} catch (MalformedURLException e) {
			// TODO: handle exception
			Log.e("URL ERROR", "ERROR");
			finalURL = null;
		}
		
	}
	
	private class JokeRequest extends AsyncTask<URL, Void, String>{

		@Override
		protected String doInBackground(URL... urls) {
			String response = "";
			for (URL url : urls) {
				response = NetworkConnection.getURLStringResponse(url);
			}
			return response;
		}
		
		@Override
		protected void onPostExecute(String result){
			Log.i("URL RESPONSE", result);
			try {
				
				JSONObject json = new JSONObject(result);
				JSONObject results = json.getJSONObject("value");
				String joke = results.getString("joke");
				String jokeId = results.getString("id");
				Log.i("JSON RESULT", joke);
				_history.put(jokeId, joke);
				ReadWrite.storeObjectFile(_context, "history", _history, false);
				
				// Nexus does not have an SD card so logic to check that would have to go here before trying to write to it. 
//				ReadWrite.storeStringFile(_context, "temp", result.toString(),true);
								
				
			} catch (JSONException e) {
				// TODO: handle exception
				Log.e("JSON ERROR" ,e.toString());
			}
		}
		
	}
	
}
