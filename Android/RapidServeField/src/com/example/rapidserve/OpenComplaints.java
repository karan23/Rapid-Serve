package com.example.rapidserve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.data.Complaints;
import com.example.data.CustomBaseAdapter;
import com.example.rapidserve.RestClient.RequestMethod;
import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class OpenComplaints extends android.support.v4.app.Fragment {

	ArrayList<Complaints> complaintsList = new ArrayList<Complaints>();
	LatLng currLatLng ;
	ListView listview ;
	CustomBaseAdapter adapter ;
	@Override
	public View getView() {
		// TODO Auto-generated method stub
		return super.getView();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.opencomplaint, container, false);
		Location loc = ((LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE)).getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (loc != null) {
			currLatLng = new LatLng(loc.getLatitude(), loc.getLongitude());
		}
		if(currLatLng==null){
			currLatLng = new LatLng(28.518532,  77.294374);
		}
		
		listview = (ListView) rootView.findViewById(R.id.listview);
	    adapter = new CustomBaseAdapter(getActivity(), complaintsList);
	    listview.setAdapter(adapter);
	    listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), JobDetail.class);
				Complaints complaint = complaintsList.get(arg2);
				i.putExtra("name", complaint.userName);
				i.putExtra("add", complaint.Adress);
				i.putExtra("type", complaint.type);
				i.putExtra("detail", complaint.detail);
				i.putExtra("phone", complaint.phoneNumber);
				i.putExtra("currentlatlng", currLatLng);
				i.putExtra("latlng", complaint.latlng);		
				i.putExtra("id", complaint.id);
                startActivity(i);
			}
		});
		return rootView;
	}

@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	callWebservice();
}
	private void callWebservice() {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String url = utils.APP_URL + "getAllComplaintByAgentId/" + utils.getAppParam(getActivity(), "nameFeildId")+"/opened";
				String resp = "";
				RestClient restClient = new RestClient(url);
				try {
					restClient.Execute(RequestMethod.GET);
					resp = restClient.getResponse();
					if (resp.length() > 0) {
						JSONArray jsa = new JSONArray(resp);
						complaintsList.clear();
						for (int i = 0; i < jsa.length(); i++) {
							Complaints complaints = new Complaints();
							JSONObject jo = jsa.getJSONObject(i);
							JSONObject complaintJsonObj = (JSONObject) jo.get("complaint");
							JSONObject customerJsonObj = (JSONObject) jo.get("customer");
							complaints.id = complaintJsonObj.getInt("id");
							complaints.type = complaintJsonObj.getString("complaintType");
							complaints.detail = complaintJsonObj.getString("complaintText");
							complaints.userName = customerJsonObj.getString("contactName");
							complaints.Adress = customerJsonObj.getString("address");
							complaints.phoneNumber = Long.parseLong(customerJsonObj.getString("contactNumber"));
							complaints.latlng = new LatLng(Double.parseDouble(customerJsonObj.getString("latitude")), Double.parseDouble(customerJsonObj
									.getString("longitude")));
							complaints.distance = Math.round(utils.getDistanceBetweenLocationInMeter(complaints.latlng, currLatLng));
							complaintsList.add(complaints);
						}
						Collections.sort(complaintsList, new CustomDistanceComparator());
						getActivity().runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								adapter.setList(complaintsList);
								adapter.notifyDataSetChanged();
							}
						});
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
		});
        t.start();
	}

	private  class CustomDistanceComparator implements Comparator<Complaints> {
		@Override
		public int compare(Complaints p1, Complaints p2) {
			return (int) (p1.distance - p2.distance);

		}
	}
}
