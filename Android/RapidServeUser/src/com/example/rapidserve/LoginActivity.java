package com.example.rapidserve;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.rapidserve.RestClient.RequestMethod;
import com.rapidserve.user.R;

public class LoginActivity extends Activity {
	private EditText mCustomerIdText, mPhoneText;
	private Button mLoginButton;
	private String mCustomerId, mPhone;
	private JSONObject mJObj;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mContext = this;
		mCustomerIdText = (EditText) findViewById(R.id.customerIdText);
		mPhoneText = (EditText) findViewById(R.id.phoneText);
		mLoginButton = (Button) findViewById(R.id.loginButton);
		mLoginButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mCustomerId = mCustomerIdText.getText().toString();
				mPhone = mPhoneText.getText().toString();
				if(mCustomerId.length() !=0 && mPhone.length() !=0) {
					new HttpRequestTask().execute();
				}
				else {
					Utils.showToast(mContext, "Please enter Customer Id and contact number!!");
				}
			}
		});

	}

	public class HttpRequestTask extends AsyncTask<Void, Void, String> {
		ProgressDialog progressDialog = ProgressDialog.show(mContext,
				"Authenticating", "Please wait..");
		int responseCode;
		@Override
		protected String doInBackground(Void... params) {
			String loginUrl = Utils.WEB_URL + "findUserById?id=" + mCustomerId + "&contactNumber=" + mPhone ;
			RestClient httpClient = new RestClient(loginUrl);
			try {
				httpClient.Execute(RequestMethod.GET);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Log.d("TAG",  httpClient.getResponse());
			responseCode = httpClient.getResponseCode();
			return httpClient.getResponse();
		}
		
		protected void onPreExecute() {
			progressDialog.show();
		}
		protected void onPostExecute(String result) {
			progressDialog.cancel();
			Log.d("Tag", result);
			if(result == null || result.length() == 0) {
				Utils.showToast(mContext, "Wrong credidentials!");
			}
			else {
				Utils.setAppParam(mContext, "customerId", mCustomerId);
				Utils.setAppParam(mContext, "customerPhone", mPhone);
				Utils.setAppParam(mContext, "login", "true");
				Intent intent = new Intent(mContext, MainActivity.class);
				mContext.startActivity(intent);	
				overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
			}
		}
	}
}
