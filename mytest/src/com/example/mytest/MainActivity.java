package com.example.mytest;

import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {
	static {
		System.loadLibrary("hello-jni");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	public native String stringFromJNI();

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO 自动生成的方法存根
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_compose:
			showText("compose");
			break;
		case R.id.action_delete:
			showText("delete");
			break;
		case R.id.action_settings:
			showText("settings");
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		// TODO 自动生成的方法存根
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}

		return super.onMenuOpened(featureId, menu);
	}

	public void showText(String text) {
		Toast.makeText(this, text, 0).show();
	}

}
