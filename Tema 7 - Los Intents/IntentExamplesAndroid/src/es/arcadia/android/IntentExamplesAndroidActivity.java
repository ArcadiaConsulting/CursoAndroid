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
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class IntentExamplesAndroidActivity extends Activity implements OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_layout);

		// obtain the images add to the Layout
		ImageView contacts = (ImageView) findViewById(R.id.contacts);
		ImageView calls = (ImageView) findViewById(R.id.calls);
		ImageView browser = (ImageView) findViewById(R.id.browser);
		ImageView email = (ImageView) findViewById(R.id.email);
		ImageView sms = (ImageView) findViewById(R.id.SMS);

		// add a onClick listener to the images to use as buttons
		contacts.setOnClickListener(this);
		calls.setOnClickListener(this);
		browser.setOnClickListener(this);
		email.setOnClickListener(this);
		sms.setOnClickListener(this);
	}

	public void onClick(View v) {
		//performs a different action depending who was clicked
		switch (v.getId()) {
		case R.id.browser:
			//open a Browser with the google spain page
			Uri uriBrowser = Uri.parse("http://www.google.es");
			Intent browserLauncher = new Intent(Intent.ACTION_VIEW, uriBrowser);
			startActivity(browserLauncher);
			break;
		case R.id.contacts:
			//show the first contact added to the phone, it hasn't anyone will crash
			Uri contactsUri = Uri.parse("content://contacts/people/1");
			Intent launchContacts = new Intent(Intent.ACTION_VIEW, contactsUri);
			startActivity(launchContacts);	 	
			break;
		case R.id.calls:
			//dial the 123 telephone number on the telephony app
			Uri callUri = Uri.parse("tel:123");
			Intent launchCalls = new Intent(Intent.ACTION_DIAL, callUri );
			startActivity(launchCalls);
			break;
		case R.id.SMS:
			//create a test SMS (don't send it)
			Intent sendSMS = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:123456"));
			sendSMS.putExtra("sms_body", "esto es un sms");
			startActivity(sendSMS);
			break;
		case R.id.email:
			//create an email (don´t send)
			//ONLY WORKS ON REAL DEVICES, NOT IN EMULATOR!!!
			Intent emailLauncher = new Intent(Intent.ACTION_SEND);
			//plain/text instead of the standard text/plain to launch Gmail instead of have to choose
			emailLauncher.setType("plain/text"); 
			emailLauncher.putExtra(Intent.EXTRA_EMAIL, new String[]{"prueba@prueba.es"});
			emailLauncher.putExtra(Intent.EXTRA_SUBJECT, "Test desde Android");
			emailLauncher.putExtra(Intent.EXTRA_TEXT, "Esto es una prueba");
			startActivity(emailLauncher);
			
			break;
		default:
			break;
		}
	}
}