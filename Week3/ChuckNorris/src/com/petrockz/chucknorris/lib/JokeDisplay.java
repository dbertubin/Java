package com.petrockz.chucknorris.lib;

import android.content.Context;
import android.widget.GridLayout;
import android.widget.TextView;

public class JokeDisplay extends GridLayout {
		// Create a Layout for Jokes to be displayed in. 
	
	TextView _joke;
	Context _context;

	
	public JokeDisplay(Context context){
		super(context);
		
		_context = context;
		
		this.setColumnCount(2);
		
		TextView jokeLabel = new TextView(_context); 
		_joke = new TextView(_context);
		jokeLabel.setText("Joke:");
		
		this.addView(jokeLabel);
		this.addView(_joke);
	
	}
	
	public void setJokeInGrid(String joke){
		
		_joke.setText(joke);
	}
}
