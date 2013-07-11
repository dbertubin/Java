/*
 * project	Week1App
 * 
 * package 	com.example.week1app
 * 
 * @author 	Derek Bertubin
 * 
 * date 	Jul 10, 2013
 */

/*
Java Variables
Java Loops
Java Conditionals
Java Functions
*/

/*
int or float variable
boolean variable
String variable 
loop (any type)
condition 
function
click event handler - Check 
*/

/*
Android resource value
TextView
EditText
Button
LinearLayout
*/

package com.example.week1app;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

// TODO: Auto-generated Javadoc
/**
 * The Class MainActivity.
 */
public class MainActivity extends Activity {

	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       // This will create the headline in the view and will display "Calculate your GPA"  
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setLayoutParams(lp);
        
        TextView textView = new TextView(this);
        textView.setText("Calculate your GPA"); 
        

        
        EditText et = new EditText(this);
        et.setHint("Enter Letter Grade");
        ll.addView(et);
        
        Button b = new Button(this);
        b.setText("Add Grade"); 
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});

        
        LinearLayout form = new LinearLayout(this);
        form.setOrientation(LinearLayout.HORIZONTAL);
        form.setLayoutParams(lp);
        
        form.addView(textView);
        form.addView(b);
        
        ll.addView(form);
        
        setContentView(ll);
    }


   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
