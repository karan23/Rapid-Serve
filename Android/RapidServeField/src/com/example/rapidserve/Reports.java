package com.example.rapidserve;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class Reports extends android.support.v4.app.Fragment {

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
    Bundle args = getArguments();

		return rootView;
	}
}
