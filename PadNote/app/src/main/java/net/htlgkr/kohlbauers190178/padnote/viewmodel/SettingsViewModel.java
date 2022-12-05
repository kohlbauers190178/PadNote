package net.htlgkr.kohlbauers190178.padnote.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    static int STATE_DATEFORMAT_ddMMyyyy = 100_1;
    static int STATE_DATEFORMAT_yyyyMMdd = 100_2;

    static int STATE_TIMEFORMAT_HHmm = 200_1;
    static int STATE_TIMEFORMAT_Hmmxm = 200_2;


    private MutableLiveData<Integer> _stateDateFormat = new MutableLiveData<>();
    public LiveData<Integer> stateDateFormat = _stateDateFormat;

    public void updateDateFormatState(int state) {
        _stateDateFormat.postValue(state);
    }

    private MutableLiveData<Integer> _stateTimeFormat = new MutableLiveData<>();
    public LiveData<Integer> stateTimeFormat = _stateTimeFormat;

    public void updateTimeFormatState(int state) {
        _stateTimeFormat.postValue(state);
    }
}
