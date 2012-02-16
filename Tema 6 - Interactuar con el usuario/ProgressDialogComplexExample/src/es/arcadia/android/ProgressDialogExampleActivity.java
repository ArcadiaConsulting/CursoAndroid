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
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProgressDialogExampleActivity extends Activity {

	//the id of the ProgressDialog 
    static final int PROGRESS_DIALOG = 0;
    
    Button button;
    ProgressThread progressThread;
    ProgressDialog progressDialog;
   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Setup the button that starts the progress dialog
        button = (Button) findViewById(R.id.progressDialog);
        button.setOnClickListener(new OnClickListener(){
            public void onClick(View v) {
                showDialog(PROGRESS_DIALOG);
            }
        }); 
    }
   
    protected Dialog onCreateDialog(int id) {
        switch(id) {
        case PROGRESS_DIALOG:
        	//create the progressDialog
            progressDialog = new ProgressDialog(ProgressDialogExampleActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMessage("Loading...");
            return progressDialog;
        default:
            return null;
        }
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch(id) {
        case PROGRESS_DIALOG:
        	//initiates the progressDialog and starts the thread that updates it
            progressDialog.setProgress(0);
            progressThread = new ProgressThread(handler);
            progressThread.start();
        }
    }

    // Define the Handler that receives messages from the thread and update the progress
    final Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            int total = msg.arg1;
            //updates the ProgressDialog progress
            progressDialog.setProgress(total);
            if (total >= 100){
            	//when the progress rise 100, close the dialog and finish the thread
                dismissDialog(PROGRESS_DIALOG);
                progressThread.setState(ProgressThread.STATE_DONE);
            }
        }
    };

    /**
     * Class that performs the progress counting from 0 to 100
     */
    private class ProgressThread extends Thread {
        Handler mHandler;
        final static int STATE_DONE = 0;
        final static int STATE_RUNNING = 1;
        int mState;
        int total;
       
        /**
         * Constructor of the thread.
         * @param h the handler to send messages to the Activity
         */
        ProgressThread(Handler h) {
            mHandler = h;
        }
       
        public void run() {
            mState = STATE_RUNNING;   
            total = 0;
            while (mState == STATE_RUNNING) {
                try {
                	//wait 0.1 seconds
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Log.e("ERROR", "Thread Interrupted");
                }
                //send a message with the current progress to the handler
                Message msg = mHandler.obtainMessage();
                msg.arg1 = total;
                mHandler.sendMessage(msg);
                total++;
            }
        }
        
        /**
         * Set the passed state to the thread (used to finish it)
         * @param state the new state, 1 RUNNING , 0 DONE
         */
        public void setState(int state) {
            mState = state;
        }
    }
}