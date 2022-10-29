package net.htlgkr.kohlbauers190178.padnote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TextViewDataModel extends ViewModel {

    private MutableLiveData<String> _text = new MutableLiveData<>();
    public LiveData<String> text = _text;


    public void updateText(String ptext) {
        _text.postValue(ptext);
    }
}
