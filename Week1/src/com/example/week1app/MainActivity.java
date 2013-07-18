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
 * Must have at least one of the following 
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
TextView - check 
EditText -check  
Button - check 
LinearLayout - check 
*/

package com.example.week1app;

//import java.lang.reflect.Array;
import java.util.ArrayList;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
//import android.text.Editable;
//import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

// TODO: 
/**
 * The Class MainActivity.
 */
@SuppressLint("DefaultLocale")
public class MainActivity extends Activity {

	LinearLayout ll;
	LinearLayout.LayoutParams lp;
	ArrayList<Integer> grades;
	int gradeValue; 
	float sum;
	float total;
	String inputError;
	String inputPrompt;
	String fieldText;

    /* (non-Javadoc)
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        /*
         * This will create the headline in the view and will display "Calculate your GPA"
         */
        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        
        TextView textView = new TextView(this);
        textView.setText("Calculate your GPA"); 
        
        final EditText et = new EditText(this);
        et.setHint("Enter Letter Grade");
        ll.addView(et);
        
        /*
         * Add Grade Button 
         */
        Button b = new Button(this);
        b.setText("Add Grade"); 
       
        /*
         * Instantiate the ArrayList and additional vars to store data
         */
        inputError = new String(getString(R.string.inputError));
        inputPrompt = new String(getString(R.string.inputPrompt));
        
        grades = new ArrayList<Integer>();
        
        /*
         * Button on click checks takes the text from the EditText Field and converts it to a string then checks the length to
         * see if it has one character or not
         * If length = 0 then it will  check for the string value and then add a corresponding int value to the ArrayList 
         */
        b.setOnClickListener(new View.OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				
				if (et.getText().toString().length() == 0 || et.getText().toString().length() != 1){
					et.setText("");
					et.setHint(inputError);
		
				} else {
				
					if(et.getText().toString().toUpperCase().equals("A")){
					 gradeValue = 4;
					 grades.add(gradeValue);
					 et.setText("");
				        et.setHint(inputPrompt);
					}
					
					else if(et.getText().toString().toUpperCase().equals("B")){
						 gradeValue = 3;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint(inputPrompt);
						}
					
					else if(et.getText().toString().toUpperCase().equals("C")){
						 gradeValue = 2;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint(inputPrompt);
						}
						
					else if(et.getText().toString().toUpperCase().equals("D")){
						 gradeValue = 1;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint(inputPrompt);
						}
					else if(et.getText().toString().toUpperCase().equals("F")){
						 gradeValue = 0;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint(inputPrompt);
						}
					else {
						et.setText("");
						et.setHint(inputError);
					}
					
			        System.out.println(grades);
			        
			
				} 
				
			}
		});
	
        /*
         *  Calc Button adds all of the values in the array and divides them by the number of values in the array
         */

        Button calc = new Button(this);
        calc.setText("Calculate GPA");
        calc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/*
				 * Required loop 
				 */
				for (int i : grades) {
				sum += i;
				
				total = sum/grades.size();
				
				et.setText("Your GPA is "+ total);
				
				System.out.println(total);	
				}
				
			}
		});
        
        
        /*
         * Clear button resets the app by emptying the ArrayList and setting anyother values to 0
         */
        Button clear = new Button(this);
        clear.setText("Clear");
        clear.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				grades.removeAll(grades);
				sum= 0;
				total=0;
				System.out.println(grades);
				et.setText("");
				
				
			}
		});
        
        LinearLayout form = new LinearLayout(this);
        form.setOrientation(LinearLayout.VERTICAL);
        form.setLayoutParams(lp);
        
        form.addView(textView);
        form.addView(b);
        form.addView(calc);
        form.addView(clear);
        
        ll.addView(form);
        
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
