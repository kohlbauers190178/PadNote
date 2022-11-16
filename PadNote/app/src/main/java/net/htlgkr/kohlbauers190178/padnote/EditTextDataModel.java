package net.htlgkr.kohlbauers190178.padnote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditTextDataModel extends ViewModel {


    private MutableLiveData<NoteModel> _note = new MutableLiveData<>();
    public LiveData<NoteModel> note = _note;

    public void updateNote(NoteModel noteModel) {
        _note.postValue(noteModel);
    }

}
