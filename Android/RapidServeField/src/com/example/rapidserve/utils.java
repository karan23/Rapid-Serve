package com.example.rapidserve;

import android.content.Context;
import android.content.SharedPreferences;

public class utils {

	public static boolean isLogged(Context context) {

		boolean checkedLogin = false;
		String val = utils.getAppParam(context, "Login");

		if (val == null) {
			val = "No";
		}

		if (val.equalsIgnoreCase("yes")) {
			checkedLogin = true;
		} else {
			checkedLogin = false;
		}

		return checkedLogin;
	}
	
	/**
	 * Sets a value pair in persistent memory
	 * 
	 * @param name
	 *            - name of the parameter
	 * @param value
	 *            - String value for the parmeter
	 * 
	 */
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
