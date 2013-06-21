package com.zennenga.cows_mobile_client;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CasAuth extends Activity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Allow network in main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		
		//Android Stuff
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cas_auth);
		
		//Setup Webview
		WebView myWebView = (WebView) findViewById(R.id.webView);
		myWebView.loadUrl("https://cas.ucdavis.edu/cas/login");
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.getSettings().setSavePassword(false);
		myWebView.getSettings().setSaveFormData(false);
		myWebView.setWebViewClient(new WebViewClient() {
			   public void onPageFinished(WebView view, String url) {
				   //check if was 200 else reload or something
			       checkCookie(view);
			   }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cas_auth, menu);
		return true;
	}
	/**
	 * Takes a cookie and pulls out the CASTGC if it exists, then clears all cookies.
	 * 
	 * @param v
	 */
	public void checkCookie(View v)	{
		CookieManager cookieManager = CookieManager.getInstance();
		final String cookie = cookieManager.getCookie("https://cas.ucdavis.edu/cas");
		if (cookie != null) Log.e("Cookie", cookie);
		if (cookie != null && cookie.contains("CASTGC"))	{
			String[] parts = cookie.split("; ");
			for (String part : parts)	{
				String[] pieces = part.split("=");
				if (pieces[0].equals("CASTGC"))	{
					Log.e("Cookie",pieces[1]);
					Intent i = new Intent(v.getContext(), MainActivity.class);
					startActivity(i);
					Utility.deauth();
					cookieManager.removeAllCookie();
					cookieManager.removeSessionCookie();
				}
			}
		}
	}
}
