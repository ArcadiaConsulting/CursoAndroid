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
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	Button linear;
	Button relative;
	Button grid;
	Button table;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		linear = (Button) findViewById(R.id.linearButton);
		relative = (Button) findViewById(R.id.relativeButton);
		grid = (Button) findViewById(R.id.GridButton);
		table = (Button) findViewById(R.id.table);

		linear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent activity = new Intent(getApplicationContext(),
						LinearActivity.class);
				startActivity(activity);
			}
		});

		relative.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent activity = new Intent(getApplicationContext(),
						RelativeActivity.class);
				startActivity(activity);
			}
		});

		table.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent activity = new Intent(getApplicationContext(),
						TableActivity.class);
				startActivity(activity);
			}
		});

		grid.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent activity = new Intent(getApplicationContext(),
						GridActivity.class);
				startActivity(activity);
			}
		});

	}

}
