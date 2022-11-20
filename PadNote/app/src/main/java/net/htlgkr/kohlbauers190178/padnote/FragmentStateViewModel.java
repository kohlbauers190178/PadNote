package net.htlgkr.kohlbauers190178.padnote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class FragmentStateViewModel extends androidx.lifecycle.ViewModel {

    public static final int MAINSTATE = 0;
    public static final int SHOW_NEW_NOTE = 1;
    public static final int SHOW_EDIT_POPUP = 3;

    private MutableLiveData<Integer> _state = new MutableLiveData<>(MAINSTATE);
    public LiveData<Integer> state = _state;


    public void showNewNote(){
        _state.postValue(SHOW_NEW_NOTE);
    }


    public void showMain(){_state.postValue(MAINSTATE);}

    public void showEdit() {
        _state.postValue(SHOW_EDIT_POPUP);
    }
}
