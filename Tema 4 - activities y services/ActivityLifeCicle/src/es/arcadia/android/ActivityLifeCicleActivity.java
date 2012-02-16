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
import android.os.Bundle;
import android.util.Log;

public class ActivityLifeCicleActivity extends Activity {
	
	private static final String ActivityName = "ActivityLifeCicleActivity";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Log.d(ActivityName, "onCreate");
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	Log.d(ActivityName, "onResume");
    }
    
    @Override
    protected void onRestart() {
    	super.onRestart();
    	Log.d(ActivityName, "onRestart");
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	Log.d(ActivityName, "onPause");
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	Log.d(ActivityName, "onStart");
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.d(ActivityName, "onStop");
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	Log.d(ActivityName, "onDestroy");
    }
}