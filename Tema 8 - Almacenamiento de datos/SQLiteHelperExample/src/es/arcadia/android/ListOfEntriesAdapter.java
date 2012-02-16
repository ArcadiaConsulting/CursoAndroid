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
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListOfEntriesAdapter extends ListActivity{
	
	Cursor cursor;
	SQLiteDatabase dataBase;
	
	@Override
	protected void onResume() {
		super.onResume();
		//open the database
		SQLHelper helper = new SQLHelper(this);
		dataBase = helper.getReadableDatabase();
		//obtain a cursor of the table (must include the _id if want to use an simpleCursorAdapter!!!!)
		cursor = dataBase.query(SQLHelper.TABLE_NAME, 
				new String[]{ SQLHelper.DB_FIELD_ID, SQLHelper.DB_FIELD_NAME, SQLHelper.DB_FIELD_NUMBER}, 
				null, 
				null, 
				null, 
				null,
				null);
		
		//attach the cursor with the list
		ListAdapter adapter = new SimpleCursorAdapter(this, R.layout.row_layout, 
				cursor, 
				new String[]{SQLHelper.DB_FIELD_NAME, SQLHelper.DB_FIELD_NUMBER}, 
				new int[]{R.id.rowLayoutNameTextView, R.id.rowLayoutNumberTextView});
		setListAdapter(adapter);
		
		getListView().setBackgroundColor(Color.DKGRAY);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		//close the cursor and the database
		if(cursor != null)
			cursor.close();
		if(dataBase != null)
			dataBase.close();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		//launch a new activity with the selected id
		Intent intent = new Intent(ListOfEntriesAdapter.this, DetailEntry.class);
		intent.putExtra("id", id);
		startActivity(intent);
	}

}
