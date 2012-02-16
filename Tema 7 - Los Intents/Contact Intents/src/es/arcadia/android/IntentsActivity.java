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
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IntentsActivity extends Activity {
	
	static final int PICK_CONTACT_REQUEST = 1;
	static final int INSERT_NUMBER_REQUEST = 2;
	
	Button contactButton;
	Button numberButton;
	TextView contactText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        contactButton = (Button) findViewById(R.id.contactButton);
        numberButton = (Button) findViewById(R.id.numberButton);
        contactText = (TextView) findViewById(R.id.contactTextView);
        
        contactButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Create an intent to "pick" a contact, as defined by the content provider URI
		        Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
		        // A result is expected from the activity
		        startActivityForResult(intent, PICK_CONTACT_REQUEST);
			}
		});
        
        numberButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IntentsActivity.this, SelectNumberActivity.class);
				// A result is expected from the Activity
				startActivityForResult(intent, INSERT_NUMBER_REQUEST);
			}
		});

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
        	if( requestCode == PICK_CONTACT_REQUEST) {
	            // Perform a query to the contact's content provider for the contact's name
	            Cursor cursor = getContentResolver().query(data.getData(),
	            		new String[] {Contacts.DISPLAY_NAME}, null, null, null);
	            if (cursor.moveToFirst()) { // To check if cursor is empty
	                int columnIndex = cursor.getColumnIndex(Contacts.DISPLAY_NAME);
	                // Obtain contact's name
	                String name = cursor.getString(columnIndex);
	                contactText.setText(name);
	            }
        	} else if(requestCode == INSERT_NUMBER_REQUEST){
        		// Obtain inserted number
        		String number = data.getStringExtra("number");
        		contactText.setText(number);
        	}
        }
    }
    
}

