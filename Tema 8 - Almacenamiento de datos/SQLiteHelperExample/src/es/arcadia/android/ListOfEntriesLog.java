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

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ListOfEntriesLog extends Activity{
	
	private SQLiteDatabase dataBase;
	private Cursor cursor;
	
	private String tag = "LIST_OF_ENTRIES";
	private StringBuilder entryBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//show a layout in the activity from code
		TextView test = new TextView(this);
		test.setText("Check the LogCat");
		setContentView(test);
		
		//open the database
		SQLHelper helper = new SQLHelper(this);
		dataBase = helper.getReadableDatabase();
		//obtain a cursor of the table with all fields
		cursor = dataBase.query(SQLHelper.TABLE_NAME, 
				null, 
				null, 
				null, 
				null, 
				null,
				null);
		
		//while the is entries in the cursor without read
		while (cursor.moveToNext()){
			entryBuilder = new StringBuilder();
			//read each column of the entry
			for(int i = 0; i < cursor.getColumnCount(); i++ ){
				entryBuilder.append(cursor.getString(i)).append(" - ");
			}
			//print the entry on the LogCat
			Log.d(tag , entryBuilder.toString());
		}
		
		//close the cursor and DB
		cursor.close();
		dataBase.close();
	}
	
}
