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
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ToastNotificationDialogsActivity extends Activity {

	//the id for the Dialogs
	static final int DIALOG_LIST_ID = 0;
	static final int DIALOG_LIST_RADIO_ID = 1;
	static final int DIALOG_CUSTOM_ID = 2;

	//the list of colors to show into the dialogs
	final CharSequence[] items = { "Red", "Green", "Blue" };

	Button toastLongButton;
	Button toastShortButton;
	Button dialog1Button;
	Button dialog2Button;
	Button dialog3Button;
	Button customDialogButton;
	Button customToastButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		toastLongButton = (Button) findViewById(R.id.toastLong);
		toastShortButton = (Button) findViewById(R.id.toastShort);
		dialog1Button = (Button) findViewById(R.id.dialog1);
		dialog2Button = (Button) findViewById(R.id.dialog2);
		dialog3Button = (Button) findViewById(R.id.dialog3);
		customDialogButton = (Button) findViewById(R.id.customDialog);
		customToastButton = (Button) findViewById(R.id.customToastButton);

		toastLongButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Se ha pulsado un boton", Toast.LENGTH_LONG).show();
			}
		});

		toastShortButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), R.string.toastText, Toast.LENGTH_SHORT).show();
			}
		});

		dialog1Button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(
						ToastNotificationDialogsActivity.this);
				builder.setMessage("Are you sure you want to exit?")
						.setCancelable(false) //can't be cancel pressing back button
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										//ends the current Activity
										ToastNotificationDialogsActivity.this.finish();
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										//cancel the Dialog (close it)
										dialog.cancel();
									}
								}
						);
				//with show method create and show in screen
				builder.show();
			}
		});

		dialog2Button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_LIST_ID);
			}
		});

		dialog3Button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_LIST_RADIO_ID);
			}
		});

		customDialogButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_CUSTOM_ID);
			}
		});

		customToastButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.custom_toast, null);
				//the widgets of the customToast can be modified in code
				TextView text = (TextView) layout.findViewById(R.id.CustomToastMessage);
				text.setText("Texto cambiado");

				Toast toast = new Toast(getApplicationContext());
				toast.setDuration(Toast.LENGTH_LONG);
				//add to the Toast the created layout
				toast.setView(layout);
				//show the customToast in screen
				toast.show();
			}
		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//with the id parameter can know the Dialog to create
		switch (id) {
		case DIALOG_LIST_ID:
			builder.setTitle("Pick a color");
			builder.setIcon(R.drawable.ic_launcher);
			//set the list of colors as items of the dialog and create a listener
			builder.setItems(items, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					//the item param returns the position of the list clicked
					Toast.makeText(getApplicationContext(), items[item],Toast.LENGTH_SHORT).show();
				}
			});
			//creates (but not show in screen) the dialog
			dialog = builder.create();
			break;
		case DIALOG_LIST_RADIO_ID:
			builder.setTitle("Pick a color");
			//set the list of colors as a radioButton list, with no one selected (-1)
			builder.setSingleChoiceItems(items, -1,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int item) {
							Toast.makeText(getApplicationContext(),items[item], Toast.LENGTH_SHORT).show();
						}
					});
			dialog = builder.create();
			break;
		case DIALOG_CUSTOM_ID:
			//create a custom dialog without using the AlertDialog.Builder
			dialog = new Dialog(ToastNotificationDialogsActivity.this);
			//set the dialog the desired layout
			dialog.setContentView(R.layout.custom_dialog);
			Button customDialogButton = (Button) dialog.findViewById(R.id.customDialogInsideButton);
			customDialogButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getApplicationContext(),
							"pulsado el boton del custom dialog",
							Toast.LENGTH_SHORT).show();
				}
			});
			dialog.setTitle("Custom Dialog");
			break;
		default:
			dialog = null;
			break;
		}
		return dialog;
	}

}