/*
 * project	Astrology
 * 
 * package 	com.petrockz.astrology.helpers
 * 
 * @author 	Derek Bertubin
 * 
 * date 	Jul 18, 2013
 */
package com.petrockz.astrology.helpers;


import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.petrockz.astrology.helpers.FormElements;
import com.petrockz.astrology.helpers.Signs;
import com.petrockz.astrology.helpers.JsonHandler;


// TODO: Auto-generated Javadoc
/**
 * The Class FormElements.
 */
public class FormElements {
	
	/**
	 * Single entry with button.
	 *
	 * @param context =the context
	 * @param hint =the hint
	 * @param buttonText = the button text
	 * @return the linear =layout
	 */
	public static LinearLayout singleEntryWithButton(Context context, String hint, String buttonText){
		
		// Layout 
		LinearLayout ll = new LinearLayout(context);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll.setLayoutParams(lp);
		
		
		// EditText ID = 1
		EditText et = new EditText(context);
		lp = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		et.setHint(hint);
		et.setLayoutParams(lp);
		et.setId(1);
		
		//Button ID = 2
		Button b = new Button(context);
		b.setText(buttonText);
		b.setId(2);
		b.setTag(et);
		
		ll.addView(et);
		ll.addView(b);
		

		
		return ll;
		
	}

		
		/**
		 * Radio group options.
		 *
		 * @param context the context
		 * @param location the location
		 * @return the radio group
		 */
		public static RadioGroup radioGroupOptions(Context context, String[] signNames) {
			RadioGroup radioGroup = new RadioGroup(context);

			for (int i = 0; i < signNames.length; i++) {
				RadioButton radioButton = new RadioButton(context);

				radioButton.setText(signNames[i]);
	            if (i==0){
	            	radioButton.setChecked(true);
				}
	            radioButton.setId(i+1);
	            radioGroup.addView(radioButton);
			}

			return radioGroup;
		}
		
		/**
		 * Show results.
		 *
		 * @param context the context
		 * @return the text view
		 */
		public static TextView showResults(Context context) {
			TextView textView = new TextView(context);
			textView.setId(3);

			return textView;
		}
}


