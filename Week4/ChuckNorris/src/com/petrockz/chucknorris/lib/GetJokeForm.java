package com.petrockz.chucknorris.lib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

@SuppressLint("ViewConstructor")
public class GetJokeForm extends LinearLayout{
	
		// Create Methods to handle random Joke Fetch and Personalization of first and last name fetch 
	
		EditText _firstName;
		EditText _lastName;
		Button _getButton;
		Button _randomButton;
		LinearLayout _ll;
	
	public GetJokeForm(Context context, String hintFirst, String hintLast, String getButtonText, String randomButtonText){
		
		super(context);
		
		
		
		LayoutParams lp;
		@SuppressWarnings("unused")
		LayoutParams fnlp;
		
		lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		
		// first name field 
		_firstName = new EditText(context);
		fnlp = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1.0f);
		_firstName.setHint(hintFirst);
		
	
		//Chuck Button
		_getButton = new Button(context);
	
		
		// Random Button
		_randomButton = new Button(context);
		_randomButton.setText(randomButtonText);
		
		_ll = new LinearLayout(context);	
		_ll.addView(_firstName);
		_ll.setLayoutParams(lp);
		this.addView(_ll);
		this.addView(_getButton);
		this.addView(_randomButton);
		this.setOrientation(VERTICAL);
		this.setLayoutParams(lp);
	}

	public EditText getFirstName(){
		
		return _firstName;
	}
	
	public EditText getLastName(){
		
		return _lastName;
	}
	
	public Button getButton(){
		
		return _getButton;
	}
	
	public Button getRandom(){
		
		return _randomButton;
	}
	
	
}





