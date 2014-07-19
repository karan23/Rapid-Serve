package com.example.rapidserve;

import com.example.rapidserve.RestClient.RequestMethod;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class OpenComplaints extends android.support.v4.app.Fragment {

@Override
public View getView() {
	// TODO Auto-generated method stub
	return super.getView();
}
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    View rootView = inflater.inflate(
            R.layout.opencomplaint, container, false);
    

		return rootView;
	}


private void callWebservice() {

	Thread t = new Thread(new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			String url = utils.APP_URL + "getAllComplaintByAgentId/" + utils.getAppParam(getActivity(), "nameFeildId");
			String resp = "";
			RestClient restClient = new RestClient(url);
			try {
				restClient.Execute(RequestMethod.GET);
				resp = restClient.getResponse();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	});


}

}
