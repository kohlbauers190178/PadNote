package net.htlgkr.kohlbauers190178.padnote.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.htlgkr.kohlbauers190178.padnote.model.Note;

import java.util.ArrayList;

public class NoteDataViewModel extends ViewModel {


    private MutableLiveData<ArrayList<Note>> _allNotes = new MutableLiveData<>();
    public LiveData<ArrayList<Note>> allNotes = _allNotes;

    public int selectedNoteNr = -1;

    public void updateSelectedNoteNr(int number) {
        this.selectedNoteNr = number;
    }

    public void updateAllNotes(ArrayList<Note> noteModels) {
        _allNotes.postValue(noteModels);
    }

}
