package com.example.rapidserve;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.rapidserve.RestClient.RequestMethod;
import com.rapidserve.user.R;

public class ViewFragment extends Fragment {
	private Context mContext;
	private JSONArray mJArray;
	private JSONObject mJObj;
	private ListView mListView;
	private CustomListAdapter mListAdapter;
	private ArrayList<Complaint> mComplaintList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_view, container,
				false);
		mListView = (ListView) rootView.findViewById(R.id.list_complaints);
		mContext = getActivity();
		mComplaintList = new ArrayList<Complaint>();
		mListAdapter = new CustomListAdapter(mContext, mComplaintList);
		mListView.setAdapter(mListAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(mContext, ComplaintActivity.class);
				intent.putExtra("type",
						mComplaintList.get(position).complaintType);
				intent.putExtra("text",
						mComplaintList.get(position).complaintText);
				intent.putExtra("status", mComplaintList.get(position).status);
				intent.putExtra("code",
						mComplaintList.get(position).satisfiedText);
				startActivity(intent);
			}
		});
		return rootView;
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		new HttpRequestTask().execute();
	}

	public class HttpRequestTask extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			String loginUrl = Utils.WEB_URL + "getAllComplaintByUserId/"
					+ Utils.getAppParam(mContext, "customerId");
			RestClient httpClient = new RestClient(loginUrl);
			try {
				httpClient.Execute(RequestMethod.GET);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return httpClient.getResponse();
		}

		protected void onPreExecute() {

		}

		protected void onPostExecute(String result) {
			if ((result != null) && (result.length() > 0)) {
				parseJson(result);
				mListAdapter.setList(mComplaintList);
				mListAdapter.notifyDataSetChanged();
			}
		}
	}

	public void parseJson(String result) {

		
		try {
			mJArray = new JSONArray(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mComplaintList.clear();
		if (mJArray != null) {
			for (int i = 0; i < mJArray.length(); i++) {
				Complaint complaint = new Complaint();
				try {
					mJObj = mJArray.getJSONObject(i);
					mJObj = mJObj.getJSONObject("complaint");
					complaint.id = mJObj.getInt("id");
					complaint.complaintText = mJObj.getString("complaintText");
					complaint.complaintType = mJObj.getString("complaintType");
					complaint.complaintTime = mJObj.getString("complaintTime");
					complaint.lastUpdated = mJObj.getString("lastUpdated");
					complaint.status = mJObj.getString("status");
					complaint.satisfiedText = mJObj.getString("satisfiedText");
					complaint.satisfied = mJObj.getString("satisfied");

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mComplaintList.add(complaint);
			}

		}
	}
}
