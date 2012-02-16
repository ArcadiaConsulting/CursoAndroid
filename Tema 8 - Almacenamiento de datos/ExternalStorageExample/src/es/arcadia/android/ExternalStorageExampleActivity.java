/*
 * Created on February 15, 2012
 *
 * ============================================================================
 *
 * Copyright (c) 2012 Services And Technology Solutions Arcadia Consulting S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice, and every other copyright notice found in this
 * software, and all the attributions in every file, and this permission notice
 * shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED,INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH
 * THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * ============================================================================
 *
 * <http://www.arcadiaconsulting.es>
 * @author Carlos Pérez Horga (carlos@arcadiaconsulting.es)
 *
 */

package es.arcadia.android;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ExternalStorageExampleActivity extends Activity {
	
	Button loadButton;
	Button saveButton;
	Button loadPublicButton;
	Button savePublicButton;
	EditText textFile;
	
	boolean mExternalStorageAvailable;
    boolean mExternalStorageWriteable;
    
    final static String FILENAME = "DemoMemo.txt";
    final static String FILENAME_PUBLIC = "DemoMemoPublic.txt"; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        loadButton = (Button) findViewById(R.id.loadButton);
    	saveButton = (Button) findViewById(R.id.saveButton);
    	textFile = (EditText) findViewById(R.id.textFileEditText);
    	loadPublicButton = (Button) findViewById(R.id.publicLoadButton);
    	savePublicButton = (Button) findViewById(R.id.publicSaveButton);
        
    	loadButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//check if can read from external storage
				isAvailableExternalStorage();
				if(mExternalStorageAvailable){
					try{
						StringBuilder strBuilder = new StringBuilder();
						//obtain the external folder for the current app and the file to read
						File file = new File(getExternalFilesDir(null), FILENAME);
						FileInputStream fis = new FileInputStream(file);
						BufferedInputStream bis = new BufferedInputStream(fis);
						DataInputStream dis = new DataInputStream(bis);
	
						//read the file line by line
						while (dis.available() != 0) {
							strBuilder.append(dis.readLine()).append("\n");
						}
						
						textFile.setText(strBuilder.toString());
						fis.close();
						bis.close();
						dis.close();
					}catch (Exception e) {
						Toast.makeText(getApplicationContext(), 
								"error al cargar el fichero", 
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
    	
    	saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//check if has write access
				isAvailableExternalStorage();
				if(mExternalStorageWriteable){
					try {
						//obtain the external folder for the current app and the file to write
						File file = new File(getExternalFilesDir(null), FILENAME);
						FileOutputStream fos = new FileOutputStream(file);
						//store the text
						fos.write(textFile.getText().toString().getBytes());
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), 
								"error al guardar el fichero", 
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
    	
    	
    	loadPublicButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//check if can read from external storage
				isAvailableExternalStorage();
				if(mExternalStorageAvailable){
					try{
						StringBuilder strBuilder = new StringBuilder();
						//obtain the root external storage directory
						File file = new File(Environment.getExternalStorageDirectory(), FILENAME_PUBLIC);
						FileInputStream fis = new FileInputStream(file);
						BufferedInputStream bis = new BufferedInputStream(fis);
						DataInputStream dis = new DataInputStream(bis);
	
						//read line by line
						while (dis.available() != 0) {
							strBuilder.append(dis.readLine()).append("\n");
						}
						
						textFile.setText(strBuilder.toString());
						fis.close();
						bis.close();
						dis.close();
					}catch (Exception e) {
						Toast.makeText(getApplicationContext(), 
								"error al cargar el fichero", 
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
    	
    	savePublicButton.setOnClickListener(new OnClickListener() {	
			@Override
			public void onClick(View v) {
				//check if can write to external storage
				isAvailableExternalStorage();
				if(mExternalStorageAvailable){
					try {
						//obtain a reference to the file in the root of external storage
						File file = new File(Environment.getExternalStorageDirectory(), FILENAME_PUBLIC);
						FileOutputStream fos = new FileOutputStream(file);
						fos.write(textFile.getText().toString().getBytes());
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
						Toast.makeText(getApplicationContext(), 
								"error al guardar el fichero", 
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
        
        
        
    }
    
    /**
     * This method checks if can read/write on the external storage
     */
    private void isAvailableExternalStorage(){
    	//init the variables as false
    	mExternalStorageAvailable = false;
        mExternalStorageWriteable = false;
        
        //obtain the external storage status
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {
        	Toast.makeText(getApplicationContext(), "No SD Card mounted", Toast.LENGTH_LONG).show();
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
    }
}