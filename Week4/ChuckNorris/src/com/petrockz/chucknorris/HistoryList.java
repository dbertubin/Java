package com.petrockz.chucknorris;

import java.util.ArrayList;
import com.petrockz.chucknorris.lib.ReadWrite;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryList extends Activity {

	ListView _listView;
	Context _context;
	ArrayList<String> _history;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.listview);
	    _history = getHistory();		
//		_listView = (ListView) findViewById(R.id.listview);
//		ArrayAdapter<String> arrayAdapter =      
//		new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, _arrayList);
//		_listView.setAdapter(arrayAdapter); 
	    

	}
	    

	
	@SuppressWarnings("unchecked")
	private ArrayList<String> getHistory (){
		Object stored = ReadWrite.readStringObject(_context, "historyArray", false);

		ArrayList<String> history;
		if (stored == null) {
			Log.i("HISTORY", "NO HISTORY FILE FOUND");
			history = new ArrayList<String>();
		} else {
			history = (ArrayList<String>) stored;
		}
		return history;
	}
	
	
}

