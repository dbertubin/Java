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

import java.lang.reflect.Array;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.util.Log;
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
	ArrayList<Integer> grades;
	int gradeValue; 
	
	String[]gradeArray = {"A","B","C","D","F"};
	String fieldText;

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
        

        
        final EditText et = new EditText(this);
        et.setHint("Enter Letter Grade");
        ll.addView(et);
        
        Button b = new Button(this);
        b.setText("Add Grade"); 
       
        grades = new ArrayList<Integer>();
        fieldText = new String();
        fieldText = et.getText().toString();
        
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if (et.getText().toString().length() ==0 || et.getText().toString().length() != 1){
					et.setText("");
					et.setHint("You must Type in a Letter Grade ex. A,B,C,D or F.");
		
				} else {
				
					if(et.getText().toString().toUpperCase().equals("A")){
					 gradeValue = 4;
					 grades.add(gradeValue);
					 et.setText("");
				        et.setHint("Enter Letter Grade");
					}
					
					else if(et.getText().toString().toUpperCase().equals("B")){
						 gradeValue = 3;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint("Enter Letter Grade");
						}
					
					else if(et.getText().toString().toUpperCase().equals("C")){
						 gradeValue = 2;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint("Enter Letter Grade");
						}
						
					else if(et.getText().toString().toUpperCase().equals("D")){
						 gradeValue = 1;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint("Enter Letter Grade");
						}
					else if(et.getText().toString().toUpperCase().equals("F")){
						 gradeValue = 0;
						 grades.add(gradeValue);
						 et.setText("");
					        et.setHint("Enter Letter Grade");
						}
					else {
						et.setText("");
						et.setHint("Please enter either A,B,C,D or F!");
					}
					
			        System.out.println(grades);
			        
			
				} 
				
			}
		});
	

        Button calc = new Button(this);
        calc.setText("Calculate GPA");
        calc.setOnClickListener(new View.OnClickListener() {
			
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
