package com.example.com.simpleyyt.shutteroff;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;
import com.example.com.simpleyyt.shutteroff.ShutterSound;

public class ShutterOffService extends Service {
	private static String shutteroffFailedMsg = null;
	private static String shutteroffSuccessedMsg = null;
    public ShutterOffService() {

    }

    @Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
    	shutteroffFailedMsg = this.getResources().getString(R.string.shutteroffFailedMsg);
    	shutteroffSuccessedMsg = this.getResources().getString(R.string.shutteroffSuccessedMsg);
    	try {
    		ShutterSound.shutterOff();
        	MyToast.showMessage(String.format(shutteroffSuccessedMsg,"¹Ø±Õ"), this);
    	} catch(Exception e) {
    		MyToast.showMessage(String.format(shutteroffFailedMsg,"¹Ø±Õ"), this);
    	}
    	this.stopSelf();
	}

	@Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
