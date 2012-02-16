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
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LayoutsTest extends Activity {

	Button openLinearLayoutButton;
	Button openLinearLayoutLandscapeButton;
	Button openRelativeLayoutButton;
	Button openTableLayoutButton;
	Button openFrameLayoutButton;
	Button openGridLayoutButton;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        openLinearLayoutButton= (Button)findViewById(R.id.LinearLayoutButton);
    	openLinearLayoutLandscapeButton= (Button)findViewById(R.id.LinearLayoutLandscapeButton);
    	openRelativeLayoutButton= (Button)findViewById(R.id.RelativeLayoutButton);
    	openTableLayoutButton= (Button)findViewById(R.id.TableLayoutButton);
    	openFrameLayoutButton= (Button)findViewById(R.id.FrameLayoutButton);
    	openGridLayoutButton = (Button)findViewById(R.id.GridLayoutButton);
        
        
        MyOnclickListener listener = new MyOnclickListener();
        
    	openLinearLayoutButton.setOnClickListener(listener);
    	openLinearLayoutLandscapeButton.setOnClickListener(listener);
    	openRelativeLayoutButton.setOnClickListener(listener);
    	openTableLayoutButton.setOnClickListener(listener);
    	openFrameLayoutButton.setOnClickListener(listener);
    	openGridLayoutButton.setOnClickListener(listener);
    }
    
    private class MyOnclickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			Intent intent = null;
			switch (v.getId()) {
			case R.id.LinearLayoutButton:
				intent = new Intent(getApplicationContext(), LinearLayoutTest.class);
				break;
			case R.id.LinearLayoutLandscapeButton:
				intent = new Intent(getApplicationContext(), LinearLayoutHorizontalTest.class);
				break;
			case R.id.RelativeLayoutButton:
				intent = new Intent(getApplicationContext(), RelativeLayoutTest.class);
				break;
			case R.id.TableLayoutButton:
				intent = new Intent(getApplicationContext(), TableLayoutTest.class);
				break;
			case R.id.FrameLayoutButton:
				intent = new Intent(getApplicationContext(), FrameLayoutTest.class);
				break;
			case R.id.GridLayoutButton:
				intent = new Intent(getApplicationContext(), GridLayoutTest.class);
				break;
			default:
				break;
			}
			if(intent != null){
				startActivity(intent);
			}
		}
    }
	
}
