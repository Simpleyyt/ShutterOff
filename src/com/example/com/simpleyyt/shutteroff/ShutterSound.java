package com.example.com.simpleyyt.shutteroff;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.res.Resources;

public class ShutterSound {
	private final static String path_1st = "/data/data/com.sec.android.app.camera/shared_prefs";
	private final static String filename_1st = "com.sec.android.app.camera_preferences.xml";
	private final static String path_2nd = "/system/media/audio/ui";
	private final static String filename_2nd = "camera_click.ogg";
	private final static String filename_bak = "shutteroff.bak";
	
	private final static String getPermissionCom_1st = "chmod 777 /data\n"
			+ "chmod 777 /data/data\n"
			+ "chmod 777 /data/data/com.sec.android.app.camera\n"
			+ "chmod 777 /data/data/com.sec.android.app.camera/shared_prefs\n"
			+ "chmod 777 /data/data/com.sec.android.app.camera/shared_prefs/com.sec.android.app.camera_preferences.xml\n";

	private final static String givePermissionCom_1st = "chmod 771 /data\n"
			+ "chmod 771 /data/data\n"
			+ "chmod 751 /data/data/com.sec.android.app.camera\n"
			+ "chmod 771 /data/data/com.sec.android.app.camera/shared_prefs\n"
			+ "chmod 660 /data/data/com.sec.android.app.camera/shared_prefs/com.sec.android.app.camera_preferences.xml\n";

	private final static String getPermissionCom_2nd = "chmod 777 /system\n" 
			+ "chmod 777 /system/media\n"
			+ "chmod 777 /system/media/audio\n"
			+ "chmod 777 /system/media/audio/ui\n"
			+ "chmod 777 /system/media/audio/ui/camera_click.ogg\n";
	
	private final static String givePermissionCom_2nd =  "chmod 755 /system\n" 
			+ "chmod 755 /system/media\n"
			+ "chmod 755 /system/media/audio\n"
			+ "chmod 755 /system/media/audio/ui\n"
			+ "chmod 666 /system/media/audio/ui/camera_click.ogg\n";

	
	public void ShutterSound() {
	}

	public static void shutteroff_1st() throws Exception {
		File file = new File(path_1st, filename_1st);
		SystemManager.RootCommand(getPermissionCom_1st);
		String content = SystemManager.readFile(file);
		SystemManager.writeFile(file, content.replaceAll(
				"shuttersound_key\" value=\"[0-9]\"",
				"shuttersound_key\" value=\"0\""));
		SystemManager.RootCommand(givePermissionCom_1st);
	}

	public static void shutteron_1st() throws Exception {
		File file = new File(path_1st, filename_1st);
		SystemManager.RootCommand(getPermissionCom_1st);
		String content = SystemManager.readFile(file);
		SystemManager.writeFile(file, content.replaceAll(
				"shuttersound_key\" value=\"[0-9]\"",
				"shuttersound_key\" value=\"1\""));
		SystemManager.RootCommand(givePermissionCom_1st);
	}
	
	public static void shutteroff_2nd() throws Exception {
		SystemManager.RootCommand(getPermissionCom_2nd);
		File file = new File(path_2nd, filename_2nd);
		File file_bak = new File(path_2nd,filename_bak);
		file.renameTo(file_bak);
		SystemManager.RootCommand(givePermissionCom_2nd);
	}
	
	public static void shutteron_2nd() throws Exception {
		SystemManager.RootCommand(getPermissionCom_2nd);
		File file = new File(path_2nd, filename_2nd);
		File file_bak = new File(path_2nd,filename_bak);
		file_bak.renameTo(file);
		SystemManager.RootCommand(givePermissionCom_2nd);
	}
	
	public static void shutterOff() throws Exception {
		try {
			shutteroff_1st();
		} catch (Exception e) {
			shutteroff_2nd();
		}
	}
	
	public static void shutterOn() throws Exception {
		try {
			shutteron_1st();
		} catch (Exception e) {
			shutteron_2nd();
		}
	}

	public static boolean isSoundOn() {
		try {
			File file = new File(path_1st, filename_1st);
			SystemManager.RootCommand(getPermissionCom_1st);
			String content = SystemManager.readFile(file);
			Pattern p = Pattern.compile("shuttersound_key\" value=\"([0-9])\"");
			Matcher m = p.matcher(content);
			if(!m.find() || m.group(1).equals("0"))
			{
				SystemManager.RootCommand(givePermissionCom_1st);
				return false;
			}
			SystemManager.RootCommand(givePermissionCom_1st);
			return true;
		} catch (Exception e) {
			try {
				File file = new File(path_2nd, filename_2nd);
				SystemManager.RootCommand(getPermissionCom_2nd);
				if (file.exists())
				{
					SystemManager.RootCommand(givePermissionCom_2nd);
					return true;
				} else {
					SystemManager.RootCommand(givePermissionCom_2nd);
					return false;
				}
			} catch(Exception ex) {
				return false;
			}
		}
	}

	public static void shutterSound(boolean soundOn) throws Exception {
		if (soundOn)
			shutterOn();
		else
			shutterOff();
	}
}
