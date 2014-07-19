package com.example.rapidserve;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.rapidserve.MainActivity.AppSectionsPagerAdapter;
import com.rapidserve.user.R;

public class SubmitFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		String category_spinner[];
		


		View rootView = inflater.inflate(R.layout.fragment_submit, container,
				false);

		// set up spinner
		category_spinner = new String[5];
		category_spinner[0] = "Service/Installation";
		category_spinner[1] = "Package/ Add ons";
		category_spinner[2] = "Product/Software";
		category_spinner[3] = "Dealer Distributor";
		category_spinner[4] = "Account Related";

		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,category_spinner);

		Spinner spinner = (Spinner) rootView.findViewById(R.id.formSpinner);
		// ArrayAdapter<CharSequence> adapter = new
		// ArrayAdapter<CharSequence>(this,
		// android.R.layout.simple_spinner_item, category_spinner);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setPrompt("Select an category");
		spinner.setAdapter(adapter);

		return rootView;

	}
}
