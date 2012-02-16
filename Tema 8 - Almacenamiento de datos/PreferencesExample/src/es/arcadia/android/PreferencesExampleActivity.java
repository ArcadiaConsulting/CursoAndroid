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
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PreferencesExampleActivity extends Activity {
	
	//variables used to manage the color changes
	String colors[] = {"BLACK", "WHITE", "RED", "BLUE", "YELLOW"};  
	enum ColorType {TEXT, BACKGROUND};
	ColorType type;
	
	LinearLayout layout;
	TextView text1;
	TextView text2;
	TextView text3;
	
	//the preferences file to use
	SharedPreferences preferences;
	
	//keys of the preferences values
	final static String COLOR_BACKGROUND_KEY = "color_back_key";
	final static String COLOR_TEXT_KEY = "color_text_key";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        layout = (LinearLayout) findViewById(R.id.LinerLayout);
        text1 = (TextView) findViewById(R.id.textView1);
    	text2 = (TextView) findViewById(R.id.textView2);
    	text3 = (TextView) findViewById(R.id.textView3);
    	
    	//read the previously saves preferences of style
    	preferences = getPreferences(MODE_PRIVATE);
    	String backValue = preferences.getString(COLOR_BACKGROUND_KEY, "BLACK");
    	String textValue = preferences.getString(COLOR_TEXT_KEY, "WHITE");
    	changeStyle(ColorType.BACKGROUND, backValue);
    	changeStyle(ColorType.TEXT, textValue);
    }
    
    /**
     * method that performs the color change
     * @param type the type of style to change (background, text)
     * @param color the color to put
     */
    private void changeStyle(ColorType type, String color){
    	if(type.equals(ColorType.BACKGROUND))
    		layout.setBackgroundColor(Color.parseColor(color));
		else{
			text1.setTextColor(Color.parseColor(color));
			text2.setTextColor(Color.parseColor(color));
			text3.setTextColor(Color.parseColor(color));
		}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	//show the menu
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
    	//obtain the user menu selection
    	if(item.getItemId() == R.id.backgroundColor)
    		type = ColorType.BACKGROUND;
    	else 
    		type = ColorType.TEXT;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	//add the color list to the dialog
    	builder.setTitle("Select color:").setItems(colors, new colorSelectedListener());
    	AlertDialog dialog = builder.create();
    	dialog.show();
    	return true;
    }
    
    private class colorSelectedListener implements DialogInterface.OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			//open the preferences to edit
			preferences = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = preferences.edit();
			
			//change the style and save in preferences the user selection
			if(type.equals(ColorType.BACKGROUND)){
				changeStyle(ColorType.BACKGROUND, colors[which]);
			    editor.putString(COLOR_BACKGROUND_KEY, colors[which]);
			}
			else{
				changeStyle(ColorType.TEXT, colors[which]);
				editor.putString(COLOR_TEXT_KEY, colors[which]);
			}
			// commit the changes
		    editor.commit();
		}
    }
    
    
    
}