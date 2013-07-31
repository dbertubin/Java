package com.petrockz.chucknorris;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import com.petrockz.chucknorris.lib.JokeDisplay;
import com.petrockz.chucknorris.lib.NetworkConnection;
import com.petrockz.chucknorris.lib.ReadWrite;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	// GLOBAL VARS 
	Context _context;


	
	Boolean _connected = false;
	HashMap<String, String> _history;
//	LayoutParams _lp;
	GridLayout _grid;
	TextView _header;
	EditText _nameField;
	TextView _joke;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// NOW USING THE LAYOUT XML
		setContentView(R.layout.form);
		 _context = this;
		 _history =  getHistory();

		 
//		 // HEADER 
//		 _header = new TextView(_context);
//		 _header.setText(R.string.header_text);
		 
		 
		 // FIRST NAME FIELD
		 _nameField = (EditText) findViewById(R.id._firstName);
		 		
		 
		 // Get handler for personalization
		 
		 Button getButton = (Button) findViewById(R.id._getButton);
		 getButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

				grabJoke(_nameField.getText().toString());	
			}
			 
		 });
		 
		 // Random with no name 
		 Button randomButton = (Button)findViewById(R.id._getRandom);
		 randomButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				grabJokeRandom();
			}
		});
		 
				 
		 // Detect NetConn
		 
		 _connected = NetworkConnection.getConnectionStatus(_context);
		 if (_connected) {
			Log.i("NETWORK CONNECTION", NetworkConnection.getConnectionType(_context));
		} else{
			
			// AlertDialog if not connected
            AlertDialog.Builder alert = new AlertDialog.Builder(_context);
            alert.setTitle("Oops!");
            alert.setMessage("Please Chuck, I mean check your network connection and try again.");
            alert.setCancelable(false);
            alert.setPositiveButton("Hiyah!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            alert.show();

            // Disable button
            getButton.setClickable(false);
            randomButton.setClickable(false);
			

		}
		 
		 
//		 _joke = new JokeDisplay(_context);
//		 _joke.setLeft(0);
//		 
//	
	}

	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	/**
	 * Gets the history.
	 *
	 * @return the history
	 */
	@SuppressWarnings("unchecked")
	private HashMap<String, String> getHistory (){
		Object stored = ReadWrite.readStringObject(_context, "history", false);
		 
		HashMap<String, String> history;
		if (stored == null) {
			Log.i("HISTORY", "NO HISTORY FILE FOUND");
			history = new HashMap<String, String>();
		} else {
			history = (HashMap<String, String>) stored;
		}
		return history;
	}
	
	/**
	 * Grab joke.
	 *
	 * @param firstName the first name
	 * @param lastName the last name
	 */
	private void grabJoke(String firstName){
		String baseURL = "http://api.icndb.com/jokes/random";
		String nameQuery = "?firstName="+firstName+"&amp;lastName=";

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
	
	
	private void grabJokeRandom(){
		String baseURL = "http://api.icndb.com/jokes/random";
		URL finalURL;
		try {
			finalURL = new URL(baseURL);
			Log.i("FINAL URL", finalURL.toString());
			JokeRequest jr = new JokeRequest();
			jr.execute(finalURL);
			
			
		} catch (MalformedURLException e) {
			// TODO: handle exception
			Log.e("URL ERROR", "ERROR");
			finalURL = null;
		}
	}
	
	/**
	 * The Class JokeRequest.
	 */
	private class JokeRequest extends AsyncTask<URL, Void, String>{

		/* (non-Javadoc)
		 * @see android.os.AsyncTask#doInBackground(Params[])
		 */
		@Override
		protected String doInBackground(URL... urls) {
			String response = "";
			for (URL url : urls) {
				response = NetworkConnection.getURLStringResponse(url);
			}
			return response;
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String result){
			Log.i("URL RESPONSE", result);
			try {
				
				JSONObject json = new JSONObject(result);
				JSONObject results = json.getJSONObject("value");
				String joke = results.getString("joke");
				String formattedJoke = joke.replaceAll("&quot;", "''");
				String jokeId = results.getString("id");
				_joke = (TextView)findViewById(R.id.joke);
				_joke.setText(formattedJoke);
				_history.put(jokeId, formattedJoke);
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
