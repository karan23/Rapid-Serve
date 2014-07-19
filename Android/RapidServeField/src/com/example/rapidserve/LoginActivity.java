package com.example.rapidserve;

import com.example.rapidserve.RestClient.RequestMethod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity implements OnClickListener {

	private Button submitBtn;
	private String resp,nameFeildId,PhoneEdit;
	private EditText feildID,phoneId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		feildID = (EditText) findViewById(R.id.feildID); 
		phoneId = (EditText) findViewById(R.id.phoneID);
		
		submitBtn = (Button) findViewById(R.id.submitBtn);
		submitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.submitBtn:

			nameFeildId = feildID.getText().toString();
			PhoneEdit = phoneId.getText().toString();
			
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					callWebservice();
				}
			}).start();

			break;

		default:
			break;
		}

	}

	private void callWebservice() {
		
		String url =  utils.APP_URL + "findAgentById?" + "id=" + nameFeildId + "&contactNumber=" + PhoneEdit;
		resp = "";
		RestClient restClient = new RestClient(url);
		try {
			restClient.Execute(RequestMethod.GET);
			resp = restClient.getResponse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		//Log.v("here" , ""+resp);
		handler.sendEmptyMessage(0);
		
//		Intent intent;
//		utils.setAppParam(LoginActivity.this, "Login", "yes");
//		intent = new Intent(LoginActivity.this,MainActivity.class);
//		startActivity(intent);
	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String nameFeildAgent = "";
			if((resp == null) || (resp.equals(""))) {
				Toast.makeText(LoginActivity.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
			} else {
			
				nameFeildAgent = utils.parseName(resp);
				
				utils.setAppParam(LoginActivity.this, "nameFeildAgent", nameFeildAgent);
				utils.setAppParam(LoginActivity.this, "nameFeildId", nameFeildId);
				utils.setAppParam(LoginActivity.this, "Login", "yes");
				
				Intent intent;
				intent = new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
			
			
		}
	};
}
