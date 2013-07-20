/*
 * project	Astrology
 * 
 * package 	com.petrockz.astrology
 * 
 * @author 	Derek Bertubin
 * 
 * date 	Jul 18, 2013
 */
package com.petrockz.astrology;
import com.petrockz.astrology.helpers.FormElements;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
 
// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Parent Layout 
		LinearLayout ll = new LinearLayout(this);
		
		LinearLayout editTextWithButton = FormElements.singleEntryWithButton(this, "Enter Name Here", "Send");
//		EditText nameEditText = (EditText) editTextWithButton.findViewById(1);
		Button nameButton = (Button) editTextWithButton.findViewById(2);
		
		  
		nameButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText nameEditText = (EditText) v.getTag();
				Log.i("BUTTON CLICKED", nameEditText.getText().toString());
				
			}
		});
		
		ll.addView(editTextWithButton);
		
		setContentView(ll);
		
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

}
