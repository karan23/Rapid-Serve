package com.example.rapidserve;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.rapidserve.user.R;

public class Utils {
	static final String WEB_URL = "http://192.168.1.242:8080/rapid-serve/endpoint/";

	static void showToast(Context context, String msg) {
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	public static boolean isLogged(Context context) {
		  
		  boolean checkedLogin = false;
		  String val = Utils.getAppParam(context, "login");

		  if (val == null) {
		   val = "false";
		  }

		  if (val.equalsIgnoreCase("true")) {
		   checkedLogin = true;
		  } else {
		   checkedLogin = false;
		  }

		  return checkedLogin;
		 }
	
	static void setAppParam(Context ctxt, String name, String value) {
		SharedPreferences settings = ctxt.getSharedPreferences(
				ctxt.getString(R.string.sharedpref), 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString(name, value);
		editor.commit();
	}

	static String getAppParam(Context ctxt, String param) {
		SharedPreferences settings = ctxt.getSharedPreferences(
				ctxt.getString(R.string.sharedpref), 0);

		return settings.getString(param, null);
	}
}
