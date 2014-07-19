package com.example.rapidserve;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

public class utils {

	final static String APP_URL =  "http://192.168.1.242:8080/rapid-serve/endpoint/";
	
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

	/**
	 * Returns an user ID. For GAAF project this has a prefix.
	 * 
	 * @param ctxt
	 * @return
	 */
	static String getUuid(Context ctxt) {
		String uuid = null;

		uuid = ((TelephonyManager) ctxt
				.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();

		return uuid;
	}

	
	public static String parseName(String resp) {
		
		String Name = "";
		
		try {
			JSONObject jsonObject = new JSONObject(resp);
			Name = jsonObject.getString("name");
			
		} catch (JSONException e) {
            e.printStackTrace();
            Name = "";
        }
		
		return Name;
	}
}