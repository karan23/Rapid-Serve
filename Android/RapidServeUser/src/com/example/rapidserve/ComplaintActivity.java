package com.example.rapidserve;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.rapidserve.user.R;

public class ComplaintActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_complaint);
		TextView typeTextView = (TextView) findViewById(R.id.complaintType_textView);
		TextView textTextView = (TextView) findViewById(R.id.complaintText_textView);
		TextView statusTextView = (TextView) findViewById(R.id.complaintStatus_textView);
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			typeTextView.setText(extras.getString("type"));
			textTextView.setText(extras.getString("text"));
			statusTextView.setText(extras.getString("status"));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.complaint, menu);
		return true;
	}

}
