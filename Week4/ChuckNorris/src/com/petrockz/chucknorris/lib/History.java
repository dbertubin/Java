package com.petrockz.chucknorris.lib;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class History extends LinearLayout {
	
	Spinner _list;
	Context _context;
	
	
	public History(Context context) {
		super(context);
		_context = context;
		_list = new Spinner(_context);
		
		this.addView(_list);

	}
	
	
	
	
	
	
}
