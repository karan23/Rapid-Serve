package com.example.rapidserve;

import com.example.data.Complaints;
import com.example.rapidserve.RestClient.RequestMethod;
import com.google.android.gms.maps.model.LatLng;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class JobDetail extends Activity {

	TextView custName, custAdd, compType, compDesc;
	ImageButton call, direction, reslove;
	EditText satisfectionCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_job_detail);
		custName = (TextView) findViewById(R.id.custname);
		custAdd = (TextView) findViewById(R.id.custAdd);
		compType = (TextView) findViewById(R.id.comptype);
		compDesc = (TextView) findViewById(R.id.compDesc);
		call = (ImageButton) findViewById(R.id.call);
		direction = (ImageButton) findViewById(R.id.getdir);
		reslove = (ImageButton) findViewById(R.id.reslove);
		satisfectionCode = (EditText) findViewById(R.id.satisCodes);
		custName.setText(getIntent().getStringExtra("name"));
		custAdd.setText(getIntent().getStringExtra("add"));
		compType.setText(getIntent().getStringExtra("type"));
		compDesc.setText(getIntent().getStringExtra("detail"));
		if (getIntent().getBooleanExtra("isFromClosed", false)) {
			reslove.setVisibility(View.GONE);
			call.setVisibility(View.GONE);
			direction.setVisibility(View.GONE);
			satisfectionCode.setVisibility(View.GONE);

		}
		call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse("tel:" + getIntent().getLongExtra("phone", 0)));
				startActivity(callIntent);
			}
		});
		direction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LatLng srclatlng = getIntent().getParcelableExtra("currentlatlng");
				LatLng destlatlng = getIntent().getParcelableExtra("latlng");
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" + srclatlng.latitude + ","
						+ srclatlng.longitude + "&daddr=" + destlatlng.latitude + "," + destlatlng.longitude));
				startActivity(intent);
			}
		});
		reslove.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						RestClient clint = new RestClient(utils.APP_URL + "complaintResolvedByAgent");
						clint.AddParam("complaintId", "" + getIntent().getIntExtra("id", 0));
						clint.AddParam("agentId", utils.getAppParam(JobDetail.this, "nameFeildId"));
						clint.AddParam("satisfiedText", satisfectionCode.getText().toString());
						try {
							clint.Execute(RequestMethod.POST);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String response = clint.getResponse();
						if (response.length() > 0) {
							runOnUiThread(new Runnable() {
								public void run() {
                                   Toast.makeText(JobDetail.this, "Complaint resolved sucessfully", Toast.LENGTH_SHORT).show();
                                   finish();
                                   MainActivity.act.actionBar.setSelectedNavigationItem(1);
								}
							});
						}
					}
				});
				t.start();
			}
		});
	}

}
