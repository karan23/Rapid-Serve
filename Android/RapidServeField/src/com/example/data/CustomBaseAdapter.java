package com.example.data;

import java.util.ArrayList;

import com.example.rapidserve.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class CustomBaseAdapter extends BaseAdapter {

	Context context;
	ArrayList<Complaints> list;

	public CustomBaseAdapter(Context context, ArrayList<Complaints> list) {

		this.context = context;
		this.list = list;

	}

	@Override
	public int getCount() {

 		return list.size();
	}


	public void setList(ArrayList<Complaints> list){
		this.list = list ;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.opencomplaintslistrow, null);
		}

		TextView contentType = (TextView) convertView.findViewById(R.id.complaintType);
		TextView contentDetail = (TextView) convertView.findViewById(R.id.detail);
		TextView contentDistance = (TextView) convertView.findViewById(R.id.distanceText);
		
		Complaints complaint = list.get(position);
		contentType.setText(complaint.type);
		contentDetail.setText(complaint.detail);
		contentDistance.setText(Math.round(complaint.distance/1000) + " Km");
		return convertView;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


}
