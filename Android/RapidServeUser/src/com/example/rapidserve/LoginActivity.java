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
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private EditText mUsernameText, mPhoneText;
	private Button mLoginButton;
	private String mUsername, mPhone, mUri;
	private JSONObject mJSONObject;
	private StringEntity mStringEntity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUsernameText = (EditText) findViewById(R.id.usernameText);
		mPhoneText = (EditText) findViewById(R.id.phoneText);
		mLoginButton = (Button) findViewById(R.id.loginButton);
		
		mLoginButton.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				mUsername = mUsernameText.getText().toString();
				mPhone = mPhoneText.getText().toString();
				JSONObject json = new JSONObject();
				try {
	                json.put("username", mUsername);
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
				makeRequest(mUri, mStringEntity);
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public HttpResponse makeRequest(String uri, StringEntity stringEntity) {
	    try {
	    	HttpClient client = new DefaultHttpClient();
	        HttpPost httpPost = new HttpPost(uri);
	        httpPost.setEntity(stringEntity);
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

}
