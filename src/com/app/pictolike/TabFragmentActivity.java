package com.app.pictolike;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.widget.TabHost;

public class TabFragmentActivity extends Activity {

	ActionBar.Tab settingsTab;  

	
	//SettingsScreenActivity fragmentSettings = new SettingsScreenActivity();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getActionBar();
		actionBar.setStackedBackgroundDrawable(getResources().getDrawable(
				R.drawable.top_bar));
		// Hide Actionbar Icon
		actionBar.setDisplayShowHomeEnabled(false);

		// Hide Actionbar Title
		actionBar.setDisplayShowTitleEnabled(false);

		// Create Actionbar Tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set Tab Icon and Titles
		 
		settingsTab = actionBar.newTab().setIcon(R.drawable.ic_settings_tab);

		// Set Tab Listeners
		 
		Fragment fragmentSettings=null;
		settingsTab.setTabListener(new TabListener(fragmentSettings));

		// Add tabs to actionbar
		 
		actionBar.addTab(settingsTab);
	}



	public class TabListener implements ActionBar.TabListener {

		android.app.Fragment fragment;

		public TabListener(android.app.Fragment fragment) {
			// TODO Auto-generated constructor stub
			this.fragment = fragment;
		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.replace(R.id.fragment_container, fragment);
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			ft.remove(fragment);
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}
	}
}
