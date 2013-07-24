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
import com.petrockz.astrology.helpers.JsonHandler;
import com.petrockz.astrology.helpers.Signs;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
 
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
		
		final TextView signText;
		
		//Parent Layout 
		LinearLayout ll = new LinearLayout(this);
		
		LinearLayout editTextWithButton = FormElements.singleEntryWithButton(this, "Enter Name Here", "Send");
//		EditText nameEditText = (EditText) editTextWithButton.findViewById(1);
		Button nameButton = (Button) editTextWithButton.findViewById(2);
		
					
		
		String[] signNames = {
				Signs.ARIES.getSignName(),
				Signs.TAURUS.getSignName(),
				Signs.GEMINI.getSignName(),
				Signs.LEO.getSignName(),
				Signs.VIRGO.getSignName(),
				Signs.LIBRA.getSignName(),
				Signs.SCORPIO.getSignName(),
				Signs.SAGITTARRIUS.getSignName(),
				Signs.CAPRICORN.getSignName(),
				Signs.AQUARIUS.getSignName(),
				Signs.PISCES.getSignName()
							};
	

		// Radio Group 
		final RadioGroup signGroup = FormElements.radioGroupOptions(this, signNames);
		
		
				
		
		signText= FormElements.showResults(this);
		signText.setText("Select a sign");
				
		// Name Button   
		nameButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int id = signGroup.getCheckedRadioButtonId();
				RadioButton rb = (RadioButton) signGroup.findViewById(id);
				String selected = rb.getText().toString();
				signText.setText("TEST");
				Log.i("SELECTED", JsonHandler.readJSON(selected));
					
			}
		});
		ll.addView(signGroup);
		ll.addView(editTextWithButton);
		
		ll.addView(signText);
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
