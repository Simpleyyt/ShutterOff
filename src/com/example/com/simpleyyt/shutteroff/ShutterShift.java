package com.example.com.simpleyyt.shutteroff;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShutterShift {

	private final static String getPermissionCom = "chmod 777 /data\n"
			+ "chmod 777 /data/local.prop\n" + "chmod 777 /system\n"
			+ "chmod 777 /system/build.prop\n";

	private final static String givePermissionCom = "chmod 771 /data\n"
			+ "chmod 755 /system\n" + "chmod 644 /system/build.prop\n";

	private final static String filepath_1st = "/data/";
	private final static String filename_1st = "local.prop";
	private final static String filepath_2nd = "/system/";
	private final static String filename_2nd = "build.prop";

	public static boolean isShiftOn() {
		try {
			SystemManager.RootCommand(getPermissionCom);
			File file = new File(filepath_1st, filename_1st);
			String fileContent = SystemManager.readFile(file);
			Pattern p = Pattern.compile("ro.camera.sound.forced=([0-9])");
			Matcher m = p.matcher(fileContent);
			if (!m.find() || !m.group(1).equals("0"))
				return false;
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void shuttershiftOn() throws Exception {
		SystemManager.RootCommand(getPermissionCom);
		File file = new File(filepath_1st, filename_1st);
		File file_sys = new File(filepath_2nd, filename_2nd);
		String fileContent = null;
		String content_sys = null;
		try {
			fileContent = SystemManager.readFile(file);
			if (fileContent.contains("ro.camera.sound.forced=")) {
				String newContent = fileContent.replaceAll(
						"ro.camera.sound.forced=[0-9]",
						"ro.camera.sound.forced=0");
				SystemManager.writeFile(file, newContent);
			}
			try {
				content_sys = SystemManager.readFile(file_sys);
				if (content_sys.contains("ro.camera.sound.forced=")) {
					String newContent_sys = content_sys.replaceAll(
							"ro.camera.sound.forced=[0-9]",
							"ro.camera.sound.forced=0");
					SystemManager.writeFile(file_sys, newContent_sys);
				}
			} catch (FileNotFoundException e) {

			}
		} catch (FileNotFoundException e) {
			file.createNewFile();
			fileContent = "ro.camera.sound.forced=0";
			SystemManager.writeFile(file, fileContent);
		}
		SystemManager.RootCommand(givePermissionCom);
	}

	public static void shuttershiftOff() throws Exception {
		SystemManager.RootCommand(getPermissionCom);
		File file = new File(filepath_1st, filename_1st);
		File file_sys = new File(filepath_2nd, filename_2nd);
		String fileContent = null;
		String content_sys = null;
		try {
			fileContent = SystemManager.readFile(file);
			if (fileContent.contains("ro.camera.sound.forced=")) {
				String newContent = fileContent.replaceAll(
						"ro.camera.sound.forced=[0-9]",
						"ro.camera.sound.forced=1");
				SystemManager.writeFile(file, newContent);
			}
			
			content_sys = SystemManager.readFile(file_sys);
			if (content_sys.contains("ro.camera.sound.forced=")) {
				String newContent_sys = content_sys.replaceAll(
						"ro.camera.sound.forced=[0-9]",
						"ro.camera.sound.forced=1");
				SystemManager.writeFile(file_sys, newContent_sys);
			}
		} catch (FileNotFoundException e) {
		}
		SystemManager.RootCommand(givePermissionCom);
	}

	public static void shuttershift(boolean shifton) throws Exception {
		if (shifton)
			shuttershiftOn();
		else
			shuttershiftOff();
	}
}
