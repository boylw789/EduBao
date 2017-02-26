package bean;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;


public class SettingUtils {

	public static String DELETE_APK = "delete_apk";
	public static String CLEAN_RAM = "clean_ram";
	public static String INSTALL_SELF = "install_self";
	public static String UPDATE_WIFI = "update_wifi";
	public static String FAST_DOWNLOAD = "fast_download";

	/**
	 * 
	 * @param context
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static boolean get(Context context, String name, boolean defaultValue) {

		final SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		boolean value = prefs.getBoolean(name, defaultValue);
		return value;
	}

	/**
	 * @param context
	 * @param name
	 * @param value
	 * @return
	 */
	public static boolean set(Context context, String name, boolean value) {

		final SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		Editor editor = prefs.edit();
		editor.putBoolean(name, value);
		return editor.commit();
	}
}
