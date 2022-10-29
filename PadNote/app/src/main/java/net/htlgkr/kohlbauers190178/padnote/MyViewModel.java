package net.htlgkr.kohlbauers190178.padnote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MyViewModel extends androidx.lifecycle.ViewModel {

    public static final int MAINSTATE = 0;
    public static final int SHOW_NEW_NOTE = 1;
    public static final int SHOW_SAVE_NOTE = 2;

    private MutableLiveData<Integer> _state = new MutableLiveData<>(MAINSTATE);
    public LiveData<Integer> state = _state;

    public void showNewNote(){
        _state.postValue(SHOW_NEW_NOTE);
    }

    public void showSaveNote(){
        _state.postValue(SHOW_SAVE_NOTE);
    }


}
