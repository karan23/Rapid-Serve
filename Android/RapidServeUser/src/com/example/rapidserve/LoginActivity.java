package com.example.rapidserve;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
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

import com.rapidserve.user.R;

public class LoginActivity extends Activity {
	private EditText mCustomerIdText, mPhoneText;
	private Button mLoginButton;
	private String mCustomerId, mPhone;
	private StringEntity mStringEntity;
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
				JSONObject json = new JSONObject();
				try {
					json.put("customer_id", mCustomerId);
					json.put("phone", mPhone);

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mStringEntity = new StringEntity(json.toString());
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.d("TAG", json.toString());
				//new HttpRequestTask().execute();
				Intent intent = new Intent(mContext, MainActivity.class);
				mContext.startActivity(intent);				
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	public class HttpRequestTask extends AsyncTask<Void, Void, HttpResponse> {
		ProgressDialog progressDialog = ProgressDialog.show(mContext,
				"Authenticating", "Please wait..");

		@Override
		protected HttpResponse doInBackground(Void... params) {

			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(Utils.LOGIN_URL);
				httpPost.setEntity(mStringEntity);
				httpPost.setHeader("Accept", "application/json");
				httpPost.setHeader("Content-type", "application/json");
				return client.execute(httpPost);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPreExecute() {
			progressDialog.show();
		}
		protected void postExecute(HttpResponse result) {
			progressDialog.hide();
			
		}
	}
}
