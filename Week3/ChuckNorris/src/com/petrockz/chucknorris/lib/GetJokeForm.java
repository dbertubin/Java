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
		
	
	public GetJokeForm(Context context, String hintFirst, String hintLast, String getButtonText, String randomButtonText){
		
		super(context);
		
		LayoutParams lp;
		
		lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.0f);
		
		// first name field 
		_firstName = new EditText(context);
		_firstName.setLayoutParams(lp);
		_firstName.setHint(hintFirst);
		
		
		//second Name field
		_lastName = new EditText(context);
		_lastName.setLayoutParams(lp);
		_lastName.setHint(hintLast);
		
		//Chuck Button
		_getButton = new Button(context);
		lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.0f);
		_getButton.setLayoutParams(lp);		
		_getButton.setText(getButtonText);
		
		// Random Button
		_randomButton = new Button(context);
		lp = new LayoutParams(0,LayoutParams.WRAP_CONTENT,1.0f);
		_randomButton.setLayoutParams(lp);
		_randomButton.setText(randomButtonText);
		
		this.setLayoutParams(lp);
		
		this.addView(_firstName);
		this.addView(_lastName);
		this.addView(_getButton);
		this.addView(_randomButton);
		
		lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
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





