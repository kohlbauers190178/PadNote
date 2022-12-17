package net.htlgkr.kohlbauers190178.padnote.viewmodel;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import net.htlgkr.kohlbauers190178.padnote.constants.SettingsConstants;
import net.htlgkr.kohlbauers190178.padnote.util.SettingsManager;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SettingsViewModel extends ViewModel {

    private SettingsManager settingsManager = new SettingsManager();

    public final String DATEPATTERN_EUROPEAN = "dd.MM.yyyy";
    public final String DATEPATTERN_US = "MM-dd-yyyy";

    public final String TIMEPATTERN_24HOURFORMAT = "HH:mm";
    public final String TIMEPATTERN_12HOURFORMAT = "hh:mm a";

    public void loadSettings(Context context) {

        Map<String, Boolean> booleanSettings = settingsManager.loadBooleanSettings(context);

        if (booleanSettings.containsKey(SettingsConstants.SETTING_24HOURFORMAT)) {
            if (booleanSettings.get(SettingsConstants.SETTING_24HOURFORMAT)) {
                TWNTYFOURHOURFORMAT = true;
                currentTimeFormatter = DateTimeFormatter.ofPattern(TIMEPATTERN_24HOURFORMAT);
            } else {
                TWNTYFOURHOURFORMAT = false;
                currentTimeFormatter = DateTimeFormatter.ofPattern(TIMEPATTERN_12HOURFORMAT);
            }
        }

        if (booleanSettings.containsKey(SettingsConstants.SETTING_USDATEFORMAT)) {
            if (booleanSettings.get(SettingsConstants.SETTING_USDATEFORMAT)) {
                USDATEFORMAT = true;
                currentDateFormatter = DateTimeFormatter.ofPattern(DATEPATTERN_US);
            } else {
                USDATEFORMAT = false;
                currentDateFormatter = DateTimeFormatter.ofPattern(DATEPATTERN_EUROPEAN);
            }
        }

        if (booleanSettings.containsKey(SettingsConstants.SETTING_SHOWEXPIREDNOTE)) {
            SHOWEXPIREDNOTES = booleanSettings.get(SettingsConstants.SETTING_SHOWEXPIREDNOTE);
        }

    }

    public void saveSettings(Context context) {
        Map<String, Boolean> booleanSettings = new HashMap<>();

        booleanSettings.put(SettingsConstants.SETTING_24HOURFORMAT, TWNTYFOURHOURFORMAT);
        booleanSettings.put(SettingsConstants.SETTING_USDATEFORMAT, USDATEFORMAT);
        booleanSettings.put(SettingsConstants.SETTING_SHOWEXPIREDNOTE, SHOWEXPIREDNOTES);

        settingsManager.saveBooleanSettings(context, booleanSettings);
    }

    public boolean TWNTYFOURHOURFORMAT = true;
    public boolean USDATEFORMAT = false;
    public boolean SHOWEXPIREDNOTES = true;

    /*static int STATE_DATEFORMAT_ddMMyyyy = 100_1;
    static int STATE_DATEFORMAT_yyyyMMdd = 100_2;

    static int STATE_TIMEFORMAT_HHmm = 200_1;
    static int STATE_TIMEFORMAT_Hmmxm = 200_2;*/


    //initialize with default values if something goes wrong
    public DateTimeFormatter currentDateFormatter = DateTimeFormatter.ofPattern(DATEPATTERN_EUROPEAN);
    public DateTimeFormatter currentTimeFormatter = DateTimeFormatter.ofPattern(TIMEPATTERN_24HOURFORMAT);


}
