package com.loqoo.streets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

public class InternetCheck {

	private Boolean isInternetPresent = false;
	private ConnectionDetector cd;


	InternetCheck()
	{
		cd = new ConnectionDetector(getApplicationContext());
	}

	void 	internetCheckFunc()
	{

		  // get Internet status
		  isInternetPresent = cd.isConnectingToInternet();
		  // check for Internet status
		  if (isInternetPresent) {
		   // Internet Connection is Present
		   // make HTTP requests
		   showAlertDialog(InternetCheck.this, "Internet Connection",
		     "You have internet connection", true);
		  } else {
		   // Internet connection is not present
		   // Ask user to connect to Internet
		   showAlertDialog(InternetCheck.this, "No Internet Connection",
		     "You don't have internet connection.", false);
  }

	}

	 /**
  * Function to display simple Alert Dialog
  * @param context - application context
  * @param title - alert dialog title
  * @param message - alert message
  * @param status - success/failure (used to set icon)
  * */
 public void showAlertDialog(Context context, String title, String message, Boolean status) {
  AlertDialog alertDialog = new AlertDialog.Builder(context).create();
  // Setting Dialog Title
  alertDialog.setTitle(title);
  // Setting Dialog Message
  alertDialog.setMessage(message);
 
  // Setting alert dialog icon
  alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
  // Setting OK Button
  alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
   public void onClick(DialogInterface dialog, int which) {
   }
  });
  // Showing Alert Message
  alertDialog.show();
 }

}