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

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This example needs and uses the Example NotePadExample so is needed to 
 * install and run it first to can use this example.
 * @author cperez
 *
 */
public class NotepadExampleConsumerActivity extends Activity {
	
	//the codes to identify the activity result
	private final static int PICK_NOTE_REQUEST_CODE = 1;
	private final static int INSERT_NOTE_REQUEST_CODE = 2;
	
	Button listNotesButton;
	Button addNotesButton;
	Button editNoteButton;
	Button editNoteTitleButton;
	Button pickNoteButton;
	TextView noteIDTextView;
	
	//when user insert/pick a note stores the note's uri 
	Uri selectedUri;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    	listNotesButton = (Button) findViewById(R.id.listNotesButton);
    	addNotesButton = (Button) findViewById(R.id.createNoteButton);
    	editNoteButton = (Button) findViewById(R.id.editNoteButton);
    	editNoteTitleButton = (Button) findViewById(R.id.chnageTitleNoteButton);
    	pickNoteButton = (Button) findViewById(R.id.pickNoteButton);
    	noteIDTextView = (TextView) findViewById(R.id.NoteIdTextView);
        
    	listNotesButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//launch the intent that fix into intentFilter to show the list of notes
		        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("content://com.google.provider.NotePad/notes"));
		        if(isIntentAvailable(intent))
		        	startActivity(intent);
		        else
					Toast.makeText(NotepadExampleConsumerActivity.this, 
							"No se encuentra la aplicación de notas.", Toast.LENGTH_LONG).show();
			}
		});
    	
    	addNotesButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//launch the intent that fix into intentFilter to add a new note
				Intent intent = new Intent(Intent.ACTION_INSERT, Uri.parse("content://com.google.provider.NotePad/notes/"));
				if(isIntentAvailable(intent))
					startActivityForResult(intent, INSERT_NOTE_REQUEST_CODE);
				else
					Toast.makeText(NotepadExampleConsumerActivity.this, 
							"No se encuentra la aplicación de notas.", Toast.LENGTH_LONG).show();
			}
		});
    	
    	editNoteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(selectedUri != null){
					//launch the intent that fix into intentFilter to edit the selected note
					Intent intent = new Intent(Intent.ACTION_EDIT, selectedUri);
					if(isIntentAvailable(intent))
					startActivity(intent);
				}else{
					Toast.makeText(NotepadExampleConsumerActivity.this, 
							"No se ha seleccionado una nota", Toast.LENGTH_LONG).show();
				}
			}
		});
    	
    	editNoteTitleButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(selectedUri != null){
					//launch the intent that fix into intentFilter to edit title of the selected note
					Intent intent = new Intent("com.android.notepad.action.EDIT_TITLE", selectedUri);
					intent.addCategory(Intent.CATEGORY_ALTERNATIVE);
					startActivity(intent);
				}else{
					Toast.makeText(NotepadExampleConsumerActivity.this, 
							"No se ha seleccionado una nota", Toast.LENGTH_LONG).show();
				}
			}
		});
        
    	pickNoteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//launch the intent that fix into intentFilter to pick a note from list
				Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://com.google.provider.NotePad/notes"));
				if(isIntentAvailable(intent))
					startActivityForResult(intent, PICK_NOTE_REQUEST_CODE);
				else
					Toast.makeText(NotepadExampleConsumerActivity.this, 
							"No se encuentra la aplicación de notas.", Toast.LENGTH_LONG).show();
			}
		});
    }
    
    /**
     * Check if an Intent is available to use
     * @param intent the Intent to check if is available 
     * @return true if is available, false otherwise
     */
    private boolean isIntentAvailable(Intent intent) {
        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	
    	if((requestCode == PICK_NOTE_REQUEST_CODE || requestCode == INSERT_NOTE_REQUEST_CODE) 
    			&& resultCode == Activity.RESULT_OK){
    		if(data.getData() != null){
    			//store the uri of the note to use later
    			selectedUri = data.getData();
    			noteIDTextView.setText(selectedUri.toString().subSequence(
        				selectedUri.toString().lastIndexOf("/")+1, selectedUri.toString().length()));
    		}
    	}
    }
}