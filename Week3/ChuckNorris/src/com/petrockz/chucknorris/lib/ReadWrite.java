package com.petrockz.chucknorris.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.util.Log;

public class ReadWrite {
	// this might also be a the JAR 
	@SuppressWarnings("resource")
	public static Boolean storeStringFile(Context context, String fileName, String content, Boolean external){
		try {
			File file;
			FileOutputStream fos;
			if(external){
				file = new File(context.getExternalFilesDir(null),fileName);
				fos = new FileOutputStream(file);
			} else {
				fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
				
			}
			fos.write(content.getBytes());			
		} catch (IOException e) {
			// TODO: handle exception
			Log.e("WRITE ERROR", e.toString());
		}
		
		return true;
		
	}
	
	@SuppressWarnings("resource")
	public static Boolean storeObjectFile(Context context, String fileName, Object content, Boolean external){
		
		try {
			File file;
			FileOutputStream fos;
			ObjectOutputStream oos;
			if(external){
				file = new File(context.getExternalFilesDir(null),fileName);
				fos = new FileOutputStream(file);
			} else {
				fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);	
			}
			oos = new ObjectOutputStream(fos);
			oos.writeObject(content);
			Log.i("CONTENT WRITTEN", content.toString());
			oos.close();
			fos.close();

		} catch (IOException e) {
			// TODO: handle exception
			Log.e("WRITE ERROR", fileName);
		}
		
		
		return true;
		
	}
	
}
