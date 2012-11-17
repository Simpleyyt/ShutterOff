package com.example.com.simpleyyt.shutteroff;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;

public class MyToast {
	public static void showMessage(String text,Context context) {
		TextView textView = new TextView(context);
		textView.setText(text);
		textView.setBackgroundResource(R.drawable.list_blockbg_single_selector);
		textView.setTextColor(Color.BLACK);
		textView.setPadding(15, 15, 15, 15);
		Toast toast = new Toast(context);
		toast.setView(textView);
		toast.show();
	}
}
