package com.example.rapidserve;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.rapidserve.RestClient.RequestMethod;
import com.rapidserve.user.R;

public class SubmitFragment extends Fragment implements OnClickListener {

	Button submitBtnFB;
	EditText AddComplaint;
	Spinner spinner;
	String category_spinner[];
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		

		View rootView = inflater.inflate(R.layout.fragment_submit, container,
				false);

		// set up spinner
		category_spinner = new String[5];
		category_spinner[0] = "Service/Installation";
		category_spinner[1] = "Package/ Add ons";
		category_spinner[2] = "Product/Software";
		category_spinner[3] = "Dealer Distributor";
		category_spinner[4] = "Account Related";

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_spinner_item, category_spinner);

		 spinner = (Spinner) rootView.findViewById(R.id.formSpinner);
		// ArrayAdapter<CharSequence> adapter = new
		// ArrayAdapter<CharSequence>(this,
		// android.R.layout.simple_spinner_item, category_spinner);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setPrompt("Select an category");
		spinner.setAdapter(adapter);

		submitBtnFB = (Button) rootView.findViewById(R.id.submitBtnFB);
		submitBtnFB.setOnClickListener(this);

		/*AddTittle = (EditText) rootView.findViewById(R.id.AddTittle);*/
		AddComplaint = (EditText) rootView.findViewById(R.id.AddComplaint);
		
		return rootView;

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();

		switch (id) {

		case R.id.submitBtnFB:

			
			callPost();

			break;

		}
	}

	private void callPost() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String ac = AddComplaint.getText().toString();
				//String at = AddTittle.getText().toString();
				String customerId = Utils.getAppParam(getActivity(), "customerId");
				String spinVal = category_spinner[spinner.getSelectedItemPosition()];
				
				RestClient restClient = new RestClient(Utils.WEB_URL
						+ "addComplaint");
				restClient.AddParam("complaintText", ac);
				restClient.AddParam("complaintType", spinVal);
				restClient.AddParam("customerId",customerId );

				try {
					restClient.Execute(RequestMethod.POST);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String resp = restClient.getResponse();
				handler.sendEmptyMessage(0);

			}
		}).start();

	}
	
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
		
			((MainActivity)getActivity()).actionBar.setSelectedNavigationItem(1);
			
		}
	};
	
}
