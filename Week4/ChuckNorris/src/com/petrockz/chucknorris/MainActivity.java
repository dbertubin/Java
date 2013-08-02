package com.petrockz.chucknorris;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

import com.petrockz.chucknorris.lib.NetworkConnection;
import com.petrockz.chucknorris.lib.ReadWrite;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	// GLOBAL VARS 
	Context _context;
	Boolean _connected = false;
	ArrayList<String> _history;
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
		 
		 
//		 Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//
//		 ImageView iv = (ImageView) findViewById(R.id.image);
//		 iv.setImageBitmap(bitmap);
		
		 // FIRST NAME FIELD
		 _nameField = (EditText) findViewById(R.id._firstName);
		 	 
		 // CUSTOM HANDLER 
		 Button getButton = (Button) findViewById(R.id._getButton);
		 getButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {

				netCon();
				if (_connected) {
		
					if (_nameField.length()!= 0) {
						grabJoke(_nameField.getText().toString());
						
						// DISMISSES KEYBOARD on CLICK 
						InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(_nameField.getWindowToken(), 0);	
						
						
					} else {
						// ALERTS USER TO INPUT A VALUE 	
						Toast alertToast = Toast.makeText(_context, "You must enter a name to use this button!", Toast.LENGTH_SHORT);	
						alertToast.show();
						
						}
				} 
			}
			 
		 });
		 
		 // RANDOM BUTTON HANDLER
		 Button randomButton = (Button)findViewById(R.id._getRandom);
		 randomButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				netCon();
				if (_connected){
					grabJokeRandom();
				};
			}
		});
		 
		// SAVE BUTTON HANDLER 
		Button saveButton = (Button)findViewById(R.id._history);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(),HistoryList.class);
				startActivityForResult(intent, 0);
			}
		});
	
	}
	
	 // DETECT NETWORK CONNECTION
	
	private void netCon(){
	
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

       // DISABLE BUTTONS 
//       getButton.setClickable(false);
//       randomButton.setClickable(false);
	}		 
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
	private ArrayList<String> getHistory (){
		Object stored = ReadWrite.readStringObject(_context, "historyArray", false);

		ArrayList<String> history;
		if (stored == null) {
			Log.i("HISTORY", "NO HISTORY FILE FOUND");
			history = new ArrayList<String>();
		} else {
			history = (ArrayList<String>) stored;
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
				_joke = (TextView)findViewById(R.id.joke);
				_joke.setText(formattedJoke);
				_history.add(formattedJoke);
				ReadWrite.storeObjectFile(_context, "historyArray", _history, false);
				
				// Nexus does not have an SD card so logic to check that would have to go here before trying to write to it. 
//				ReadWrite.storeStringFile(_context, "temp", result.toString(),true);
								
				
			} catch (JSONException e) {
				// TODO: handle exception
				Log.e("JSON ERROR" ,e.toString());
			}
		}
		
	}
	
}
