package com.example.rapidserve;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LoginActivity extends Activity implements OnClickListener {

	
	Button submitBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		submitBtn = (Button) findViewById(R.id.submitBtn);
		submitBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		Intent intent;
		switch (id) {
		case R.id.submitBtn:

			utils.setAppParam(LoginActivity.this, "Login", "yes");
			intent = new Intent(LoginActivity.this,MainActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
