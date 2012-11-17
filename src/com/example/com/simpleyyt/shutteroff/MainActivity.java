package com.example.com.simpleyyt.shutteroff;

import net.youmi.android.AdManager;
import net.youmi.android.AdView;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private static String shuttershiftFailedMsg = null;
	private static String shuttershiftSuccessedMsg = null;
	private CheckBox shutter_sound = null;
	private CheckBox shutter_shift = null;
	private static String shutteroffFailedMsg = null;
	private static String shutteroffSuccessedMsg = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AdManager.init(this,"abadc3ecca96df31", "49bb6c148bfa25ff", 30,  false);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);   
		setContentView(R.layout.main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.main_title_bar);
		shuttershiftFailedMsg = this.getResources().getString(
				R.string.shuttershiftFailedMsg);
		shuttershiftSuccessedMsg = this.getResources().getString(
				R.string.shuttershiftSuccessedMsg);
		shutteroffFailedMsg = this.getResources().getString(
				R.string.shutteroffFailedMsg);
		shutteroffSuccessedMsg = this.getResources().getString(
				R.string.shutteroffSuccessedMsg);
		shutter_sound = (CheckBox) findViewById(R.id.shutter_sound);
		shutter_shift = (CheckBox) findViewById(R.id.shutter_shift);
	}


	public void shutteroffButton_Clicked(View view) {
		boolean checking = !shutter_sound.isChecked();
		try {
			ShutterSound.shutterSound(checking);
			shutter_sound.setChecked(checking);
			MyToast.showMessage(String.format(shutteroffSuccessedMsg,getOperation(checking)), this);
		} catch (Exception e) {
			MyToast.showMessage(String.format(shutteroffFailedMsg,getOperation(checking)), this);
		}
	}

	public void shuttershiftButton_Clicked(View view) {
		boolean checking = !shutter_shift.isChecked();
		try {
			ShutterShift.shuttershift(checking);
			shutter_shift.setChecked(checking);
			MyToast.showMessage(String.format(shuttershiftSuccessedMsg,getOperation(checking)), this);
			RebootDialog rd = new RebootDialog(this,R.style.MyDialog);
			rd.show();
		} catch (Exception e) {
			MyToast.showMessage(String.format(shuttershiftFailedMsg,getOperation(checking)), this);
		}
	}

	public String getOperation(boolean bool)
	{
		if (bool)
			return "¿ªÆô";
		else
			return "¹Ø±Õ";
	}
	public void help_Clicked(View view) {
		Intent intent = new Intent(MainActivity.this, HelpActivity.class);
		this.startActivity(intent);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		shutter_shift.setChecked(ShutterShift.isShiftOn());
		shutter_sound.setChecked(ShutterSound.isSoundOn());
	}


	public void feedback_Clicked(View view) {
		Intent intent = new Intent("android.intent.action.VIEW",
				Uri.parse("http://weibo.com/u/1836017133"));
		this.startActivity(intent);
	}

}
