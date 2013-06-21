package com.zennenga.cows_mobile_client;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.webkit.CookieManager;
/**
 * 
 * MainActivity
 * 
 * Program Entry Point, leads to CasAuth and RoomSelect.
 * 
 * @author its-zach
 *
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void viewEvents(View v)	{
		Intent i = new Intent(v.getContext(), RoomSelect.class);
		startActivity(i);
	}
	
	public void doEvent(View v)	{
		Intent i = new Intent(v.getContext(), CasAuth.class);
		startActivity(i);
	}
}
