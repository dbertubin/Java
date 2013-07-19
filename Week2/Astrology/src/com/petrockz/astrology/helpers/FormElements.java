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

}
