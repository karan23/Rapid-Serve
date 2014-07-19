package com.example.rapidserve;

import com.rapidserve.user.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splashscreen);
		
		
		// Send a message in 3.5 sec to start Home Page
	    new Handler().postDelayed(new Runnable(){ 
	    	public void run() {
	    		Intent iLogin = new Intent(SplashActivity.this,LoginActivity.class); 
	    		startActivity(iLogin);
	    		overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
	    		finish();
	    		//handler.sendEmptyMessage(0);
	        } 
	    }, 3500);
		
	}
	
	/*private Handler handler = new Handler() { 
        @Override
        public void handleMessage(Message msg) {
        	
        	Intent 
        }
	};*/
}
