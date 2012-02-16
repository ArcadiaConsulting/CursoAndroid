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

import es.arcadia.android.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEntry extends Activity {
	
	
	EditText nameEditText;
	EditText numberEditText;
	Button saveButton;
	Button clearButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);
        
    	nameEditText = (EditText) findViewById(R.id.nameEditText);
    	numberEditText = (EditText) findViewById(R.id.numberEditText);
    	saveButton = (Button) findViewById(R.id.saveEntryButton);
    	clearButton = (Button) findViewById(R.id.clearEntryButton);
        
        clearButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				nameEditText.setText("");
				numberEditText.setText("");
			}
		});
        
        saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//opens the DB
				SQLHelper helper = new SQLHelper(AddEntry.this);
		        SQLiteDatabase database = helper.getWritableDatabase();
		        //obtain the data to storage
		        int number = Integer.parseInt(numberEditText.getText().toString());
		        String name = nameEditText.getText().toString();
		        //Put the data into a ContentValues
		        ContentValues values = new ContentValues();
		        values.put(SQLHelper.DB_FIELD_NAME, name);
		        values.put(SQLHelper.DB_FIELD_NUMBER, number);
		        //make the insert
				long id = database.insert(SQLHelper.TABLE_NAME, null, values );
				//close the DB
		        database.close();
				Toast.makeText(getApplicationContext(), "The entry with ID "+id+" was created", Toast.LENGTH_SHORT).show();
				finish();		
			}
		});
    }
}