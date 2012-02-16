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
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class NotificationBarActivity extends Activity {
	
	//the id to the notification
	private static final int NOTIFICATION_ACTIVITY_ID = 1;
	
	Button notifyMeButton;
	Button cancelNotificationButton;
	
	NotificationManager mNotificationManager;
	Notification notification;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //attach the buttons
        notifyMeButton = (Button) findViewById(R.id.notifyMeButton);
        cancelNotificationButton = (Button) findViewById(R.id.cancelNotification);
        
        //obtain the notification manager
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        
        //create the notification
        int icon = R.drawable.ic_launcher;
        CharSequence tickerText = "Notificacion";
        long when = System.currentTimeMillis();
        notification = new Notification(icon, tickerText, when);
        
        //initializes the notification
        Context context = getApplicationContext();
        CharSequence contentTitle = "Esto es una notificacion";
        CharSequence contentText = "Pulsame!";
        Intent notificationIntent = new Intent(this, ActivityTarget.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, 
        		notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
        
        //flag to auto-cancel when user select the notification from notificationBar
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        
        //send the notification and add to notificationBar when press the button
        notifyMeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mNotificationManager.notify(NOTIFICATION_ACTIVITY_ID, notification);
			}
		});   
        
        //cancel a pending notification when press the cancel button
        cancelNotificationButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mNotificationManager.cancel(NOTIFICATION_ACTIVITY_ID);
			}
		});
    }

}