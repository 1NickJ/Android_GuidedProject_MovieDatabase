package com.lambdaschool.movieslist;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ThemeUtils {

    public static final String THEME_KEY = "Theme";

    public static int getOtherTheme(int currentTheme) {
        int newTheme;
        if(currentTheme == R.style.AppThemeLarge) {
            newTheme = R.style.AppThemeSmall;
        } else {
            newTheme = R.style.AppThemeLarge;
        }
        return newTheme;
    }

    public static void onActivityCreateSetTheme(Activity activity) {
        activity.setTheme(getCurrentTheme(activity));
    }

    public static int getCurrentTheme(Activity activity) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        int cTheme = prefs.getInt(THEME_KEY, R.style.AppThemeSmall);
        return cTheme;
    }

    public static void storeCurrentTheme(Activity activity, int theme) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        final SharedPreferences.Editor edit = prefs.edit();
        edit.putInt(THEME_KEY, theme);
        edit.apply();
    }

    public static void refreshActivity(Activity activity) {
        Intent intent = activity.getIntent();
        activity.finish();
        activity.startActivity(intent);
    }

    public static void toggleTheme(Activity activity) {
        int theme = getCurrentTheme(activity);
        theme = getOtherTheme(theme);
        storeCurrentTheme(activity, theme);
        refreshActivity(activity);
    }
}
