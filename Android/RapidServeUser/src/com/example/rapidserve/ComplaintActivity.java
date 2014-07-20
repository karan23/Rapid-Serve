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
		TextView typeTextView = (TextView) findViewById(R.id.complaintType);
		TextView textTextView = (TextView) findViewById(R.id.complaintText);
		TextView statusTextView = (TextView) findViewById(R.id.complaintStatus);
		TextView satisfactionTextView = (TextView) findViewById(R.id.complaintSatisfied);		
		Bundle extras = getIntent().getExtras();
		if(extras != null) {
			typeTextView.setText(extras.getString("type"));
			textTextView.setText(extras.getString("text"));
			satisfactionTextView.setText(extras.getString("code"));
			String status = extras.getString("status");
			if(status.equalsIgnoreCase("CMPLT")) {
				status = "Complete";
			}
			else if(status.equalsIgnoreCase("INPROG")) {
				status = "In Progress";
			}
			else if(status.equalsIgnoreCase("CRTD")) {
				status = "Created";
			}
			else {
				status = "In Progress";
			}
			statusTextView.setText(status);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.complaint, menu);
		return true;
	}

}
