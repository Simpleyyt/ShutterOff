package com.example.com.simpleyyt.shutteroff;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RebootDialog extends Dialog {
	
	private Button ok_btn = null;
	private Button cancel_btn = null;
	private Context context;
    public RebootDialog(Context context, boolean cancelable,
			OnCancelListener cancelListener) {
		super(context, cancelable, cancelListener);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public RebootDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	public RebootDialog(Context context) {
		super(context);
		this.context = context;
		// TODO Auto-generated constructor stub
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reboot_dialog);
        cancel_btn = (Button)findViewById(R.id.cancel_btn);
        ok_btn = (Button)findViewById(R.id.ok_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				RebootDialog.this.dismiss();
			}
        });
        ok_btn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				try {
					SystemManager.RootCommand("reboot\n");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        });
	}
	
}
