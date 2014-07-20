package com.example.rapidserve;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rapidserve.user.R;


public class CustomListAdapter extends BaseAdapter {

	private Context mContext;
	private List<Complaint> mComplaintList;

	public CustomListAdapter(Context mContext, List<Complaint> mComplaintList) {
		this.mContext = mContext;
		this.mComplaintList = mComplaintList;
	}

	public void setList(List<Complaint> mComplaintList) {
		this.mComplaintList = mComplaintList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mComplaintList.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) mContext
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.app_list_item, null);
		}

		TextView txtTitle = (TextView) convertView
				.findViewById(R.id.comaplaintTitile);
		TextView txtStatus = (TextView) convertView
				.findViewById(R.id.complaintStatus);
		TextView txtDesc = (TextView) convertView
				.findViewById(R.id.shortDesc);
		txtTitle.setText(mComplaintList.get(position).complaintType);
		txtStatus.setText(mComplaintList.get(position).status);
		txtDesc.setText(mComplaintList.get(position).complaintText);
		return convertView;
	}

}
