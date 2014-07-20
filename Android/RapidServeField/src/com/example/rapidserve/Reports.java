package com.example.rapidserve;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.rapidserve.RestClient.RequestMethod;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class Reports extends android.support.v4.app.Fragment {
	TextView total, inprogress, complete, distance;
@Override
public View getView() {
	// TODO Auto-generated method stub
	return super.getView();
}
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    View rootView = inflater.inflate(
            R.layout.report, container, false);
    total = (TextView) rootView.findViewById(R.id.totaljob);
    inprogress = (TextView) rootView.findViewById(R.id.inprogressJob);
    complete = (TextView) rootView.findViewById(R.id.compleatedJob);
    distance = (TextView) rootView.findViewById(R.id.distance);

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
			String url = utils.APP_URL + "findAgentStatistics?agentId=" + utils.getAppParam(getActivity(), "nameFeildId");
			String resp = "";
			RestClient restClient = new RestClient(url);
			try {
				restClient.Execute(RequestMethod.GET);
				resp = restClient.getResponse();
				if (resp.length() > 0) {
					final JSONObject jo = new JSONObject(resp);
                     
					getActivity().runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
                            try {
								String inProgress = jo.getString("inprogressComplaints");
	                            String complString = jo.getString("completeComplaints");
	                            String totalCount = ( Integer.parseInt(complString)+Integer.parseInt(inProgress))+"";
	                            total.setText(totalCount);
	                            inprogress.setText(inProgress);
	                            complete.setText(complString);
	                            distance.setText(Integer.parseInt(complString)*10 + 2 + "Km");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
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
}
