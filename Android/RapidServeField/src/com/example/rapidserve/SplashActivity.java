package com.example.rapidserve;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splashscreen);

		// Send a message in 3.5 sec to start Home Page
		new Handler().postDelayed(new Runnable() {
			public void run() {

				Intent intent;
				//if (!utils.isLogged(SplashActivity.this)) {
					intent = new Intent(SplashActivity.this,
							LoginActivity.class);

//				} else {
//					intent = new Intent(SplashActivity.this, MainActivity.class);
//
//				}

				startActivity(intent);
				overridePendingTransition(R.anim.activity_open_translate,
						R.anim.activity_close_scale);
				finish();
				// handler.sendEmptyMessage(0);
			}
		}, 3500);

	}

}
