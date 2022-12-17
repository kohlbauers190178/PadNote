package net.htlgkr.kohlbauers190178.padnote.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FragmentStateViewModel extends androidx.lifecycle.ViewModel {

    public static final int MAINSTATE = 0;
    public static final int SHOW_NEW_NOTE = 1;
    public static final int SHOW_EDIT_POPUP = 3;
    public static final int SHOW_SETTINGS = 9;

    private MutableLiveData<Integer> _state = new MutableLiveData<>(MAINSTATE);
    public LiveData<Integer> state = _state;


    public void showNewNote(){
        _state.postValue(SHOW_NEW_NOTE);
    }


    public void showMain(){_state.postValue(MAINSTATE);}

    public void showEdit() {
        _state.postValue(SHOW_EDIT_POPUP);
    }

    public void showSettings(){_state.postValue(SHOW_SETTINGS);}
}
