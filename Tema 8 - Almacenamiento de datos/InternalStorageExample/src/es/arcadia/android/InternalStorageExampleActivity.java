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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InternalStorageExampleActivity extends Activity {
	
	Button loadButton;
	Button saveButton;
	EditText textFile;
	
	//the name of the file to use
	static final String FILENAME = "my_file_memo.txt";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        loadButton = (Button) findViewById(R.id.loadButton);
    	saveButton = (Button) findViewById(R.id.saveButton);
    	textFile = (EditText) findViewById(R.id.textFileEditText);
    	

    	saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				try {
					//obtain the internal storage dir
					FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
					//write the text into the file
					fos.write(textFile.getText().toString().getBytes());
					fos.close();
					Toast.makeText(getApplicationContext(), 
							"fichero guardado satisfactoriamente", 
							Toast.LENGTH_SHORT).show();
				} catch (IOException e) {
					e.printStackTrace();
					Toast.makeText(getApplicationContext(), 
							"Ocurrió un error al guardar el fichero", 
							Toast.LENGTH_SHORT).show();
				}
			}
		});
    	
    	loadButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					StringBuilder strBuilder = new StringBuilder();
					//obtain the internal storage dir
					FileInputStream fis = openFileInput(FILENAME);
					BufferedInputStream bis = new BufferedInputStream(fis);
					DataInputStream dis = new DataInputStream(bis);

					//read file line by line
					while (dis.available() != 0) {
						strBuilder.append(dis.readLine()).append("\n");
					}
					
					textFile.setText(strBuilder.toString());
					fis.close();
					bis.close();
					dis.close();
					Toast.makeText(getApplicationContext(), 
							"fichero cargado satisfactoriamente", 
							Toast.LENGTH_SHORT).show();
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), 
							"Ocurrió un error al cargar el fichero", 
							Toast.LENGTH_SHORT).show();
				}
			}
		});
    	
    }
}