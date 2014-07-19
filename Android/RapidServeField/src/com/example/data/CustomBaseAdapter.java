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

	public CustomBaseAdapter(Context context, ArrayList<Complaints> list,
			int caseNo, int size) {

		this.context = context;
		this.list = list;

	}

	@Override
	public int getCount() {

		return list.size();
	}

	@Override
	public Object getItem(int pos) {

		return list.get(pos);
	}

	@Override
	public long getItemId(int pos) {

		return list.indexOf(getItem(pos));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.deal_menu, null);
		}

		return convertView;
	}


}
