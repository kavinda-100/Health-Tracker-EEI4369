package com.s22010170.heathtrakerapp;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {
    private static final String PREFS_NAME = "AuthPreferences";

    private final SharedPreferences sharedPreferences;

    public SharedPrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Save a string value
    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void updateString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value); // Update the value
        editor.apply();
    }

    // Retrieve a string value (provide a default value if not found)
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    // Save an boolean value
    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    // get a boolean value
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    // Clear specific field(boolean)
    public void clearBooleanField(String[] keys) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply();
    }

    // Clear specific fields (e.g., email and username)
    public void clearFields(String[] keys) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (String key : keys) {
            editor.remove(key);
        }
        editor.apply();
    }

    // Clear all preferences (use with caution)
    public void clearPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
