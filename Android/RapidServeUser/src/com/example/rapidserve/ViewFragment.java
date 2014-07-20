package com.example.rapidserve;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rapidserve.RestClient.RequestMethod;
import com.rapidserve.user.R;

public class ViewFragment extends Fragment {
	private Context mContext;
	private JSONArray mJArray;
	private JSONObject mJObj;
	private ListView mListView;
	private CustomListAdapter mListAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_view, container,
				false);
		mListView = (ListView) rootView.findViewById(R.id.list_complaints);
		mContext = getActivity();
		Complaint complaint = new Complaint("","");
		ArrayList<Complaint> array = new ArrayList<Complaint>();
		array.add(complaint);
		mListAdapter = new CustomListAdapter(mContext, array);
		mListView.setAdapter(mListAdapter);
		return rootView;
	}
@Override
public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	new HttpRequestTask().execute();
}
	public class HttpRequestTask extends AsyncTask<Void, Void, String> {
		ProgressDialog progressDialog = ProgressDialog.show(mContext,
				"Connecting", "Please wait..");

		@Override
		protected String doInBackground(Void... params) {
			String loginUrl = Utils.WEB_URL + "getAllComplaintByUserId/"
					+ Utils.getAppParam(mContext, "customerId") + "/opened";
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
			progressDialog.show();
		}

		protected void onPostExecute(String result) {
			progressDialog.cancel();
			if ((result != null) && (result.length() > 0)) {

				ArrayList<Complaint> mComplaintList = parseJson(result);
				mListAdapter.setList(mComplaintList);
				mListAdapter.notifyDataSetChanged();
			}
		}
	}

	ArrayList<Complaint> parseJson(String result) {

		ArrayList<Complaint> mArray = new ArrayList<Complaint>();

		
		try {
			mJArray = new JSONArray(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (mJArray != null) {
			for (int i = 0; i < mJArray.length(); i++) {
				Complaint complaint = new Complaint();
				try {
					mJObj = mJArray.getJSONObject(i);
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
				mArray.add(complaint);
			}
			
		}
		return mArray;
	}
}
