package net.htlgkr.kohlbauers190178.padnote.util;

import android.content.Context;
import android.content.SharedPreferences;

import net.htlgkr.kohlbauers190178.padnote.constants.SettingsConstants;

import java.util.HashMap;
import java.util.Map;

public class SettingsManager {

    final String SHARED_PREFS = "sharedPrefs";

    final boolean DEFAULT_24HOURFORMAT = true;
    final boolean DEFAULT_USDATEFORMAT = false;
    final boolean DEFAULT_SHOWEXPIREDNOTE = true;

    public Map<String, Boolean> loadBooleanSettings(Context context) {
        Map<String, Boolean> settings = new HashMap<>();

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        settings.put(SettingsConstants.SETTING_24HOURFORMAT, sharedPreferences.getBoolean(SettingsConstants.SETTING_24HOURFORMAT, DEFAULT_24HOURFORMAT));
        settings.put(SettingsConstants.SETTING_USDATEFORMAT, sharedPreferences.getBoolean(SettingsConstants.SETTING_USDATEFORMAT, DEFAULT_USDATEFORMAT));
        settings.put(SettingsConstants.SETTING_SHOWEXPIREDNOTE, sharedPreferences.getBoolean(SettingsConstants.SETTING_SHOWEXPIREDNOTE, DEFAULT_SHOWEXPIREDNOTE));

        return settings;
    }

    public void saveBooleanSettings(Context context, Map<String, Boolean> settings) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (Map.Entry<String, Boolean> setting : settings.entrySet()) {
            editor.putBoolean(setting.getKey(), setting.getValue());
        }
        editor.apply();
    }
}
