package com.example.com.simpleyyt.shutteroff;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class HelpActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); 
        setContentView(R.layout.help);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.help_title_bar); 
    }
    
    public void back_Clicked(View view) {
    	this.finish();
    }
}
