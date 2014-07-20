package com.example.rapidserve;


import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;
	public ViewPager mViewPager;
	protected ActionBar actionBar;// = getActionBar();
	LocationListener mlocListener;
	LocationManager mlocManager;
	static MainActivity act ; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		act = this ;
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_main);
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());
		mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		mlocListener = new MyLocationListener();
		mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 600000, 500, mlocListener);
		// Set up the action bar.
		actionBar = getActionBar();
		// Specify that the Home/Up button should not be enabled, since there is
		// no hierarchical
		// parent.
		actionBar.setHomeButtonEnabled(false);

		// Specify that we will be displaying tabs in the action bar.
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// When swiping between different app sections, select the
				// corresponding tab.
				// We can also use ActionBar.Tab#select() to do this if we have
				// a reference to the
				// Tab.
				actionBar.setSelectedNavigationItem(position);

			}
		});

		actionBar.addTab(actionBar.newTab().setText("Open Issues").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Close Issues").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Reports").setTabListener(this));
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mlocManager.removeUpdates(mlocListener);
		super.onDestroy();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		moveTaskToBack(true);
	}
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
		mAppSectionsPagerAdapter.notifyDataSetChanged();
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	public class AppSectionsPagerAdapter extends FragmentStatePagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public android.support.v4.app.Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null ;
			switch (arg0) {
			case 0:
				 fragment = new OpenComplaints();
				break;
			case 1:
				 fragment = new ClosedComplaints();
				break;
			case 2:
				 fragment = new Reports();
				break;

			default:
				break;
			}

			return fragment;
		}

	}

	
	/* Class My Location Listener */

	public class MyLocationListener implements LocationListener

	{


		@Override
		public void onLocationChanged(Location loc)

		{
	
		}

		@Override
		public void onProviderDisabled(String provider)

		{

			Toast.makeText(getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onProviderEnabled(String provider)

		{

			Toast.makeText(getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)

		{

		}
	}
	
}
