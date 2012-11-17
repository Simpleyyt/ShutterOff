package com.example.com.simpleyyt.shutteroff;

import com.example.com.simpleyyt.shutteroff.R;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class ShutterOffWidget extends AppWidgetProvider {

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onDisabled(Context context) {
		// TODO Auto-generated method stub
		super.onDisabled(context);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Intent intent = new Intent();
		intent.setClass(context, ShutterOffService.class);
		PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, 0);
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.widget);
		remoteViews.setOnClickPendingIntent(R.id.btn_1, pendingIntent);
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		
	}
    
}
