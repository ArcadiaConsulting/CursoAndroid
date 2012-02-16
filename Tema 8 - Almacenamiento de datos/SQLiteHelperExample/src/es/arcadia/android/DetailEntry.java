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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetailEntry extends Activity{
	
	private Button deleteEntry;
	private TextView nameTextView;
	private TextView numberTextView;
	private long id;
	private SQLiteDatabase dataBase;
	private Cursor cursor;
	private SQLHelper helper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//check if the extra comes to the activity
		id = getIntent().getLongExtra("id", -1);
		if(id == -1){
			finish();
		}
		
		//binding the layout and widgets
		setContentView(R.layout.detail_entry_layout);
		deleteEntry = (Button) findViewById(R.id.detailDeleteButton);
		nameTextView = (TextView) findViewById(R.id.detailTextViewName);
		numberTextView = (TextView) findViewById(R.id.detailTextViewNumber);
		
		
		//opening the DB
		helper = new SQLHelper(DetailEntry.this);
		dataBase = helper.getReadableDatabase();
		//obtain a cursor of the table (must include the _id if want to use an simpleCursorAdapter!!!!)
		cursor = dataBase.query(SQLHelper.TABLE_NAME, 
				new String[]{ SQLHelper.DB_FIELD_ID, SQLHelper.DB_FIELD_NAME, SQLHelper.DB_FIELD_NUMBER}, 
				SQLHelper.DB_FIELD_ID+" = ?", 
				new String[]{String.valueOf(id)}, 
				null, 
				null,
				null);
		//if cursor has results
		if( cursor.moveToNext()){
			int nameIndex = cursor.getColumnIndex(SQLHelper.DB_FIELD_NAME);
			int numberIndex = cursor.getColumnIndex(SQLHelper.DB_FIELD_NUMBER);
			
			String name = cursor.getString(nameIndex);
			String number = cursor.getString(numberIndex);
			
			nameTextView.setText(name);
			numberTextView.setText(number);
		}
		
		//close cursor and database
		cursor.close();
		dataBase.close();
		
		deleteEntry.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
		        SQLiteDatabase database = helper.getWritableDatabase();

		        //perform the delete searching by name
		        database.delete(SQLHelper.TABLE_NAME, 
		        		SQLHelper.DB_FIELD_ID+"=?", 
		        		new String[]{String.valueOf(id)});
		        //close the DB
		        database.close();
		        //show the toast with the number of entries deleted
				Toast.makeText(getApplicationContext(), 
						"entry deleted", 
						Toast.LENGTH_LONG).show();
				//ends the current activity
				finish();
			}
		});
		
	}

}
