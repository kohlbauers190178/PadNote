package net.htlgkr.kohlbauers190178.padnote.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import net.htlgkr.kohlbauers190178.padnote.R;
import net.htlgkr.kohlbauers190178.padnote.model.Note;
import net.htlgkr.kohlbauers190178.padnote.util.JsonManager;
import net.htlgkr.kohlbauers190178.padnote.util.MyDatePicker;
import net.htlgkr.kohlbauers190178.padnote.util.MyTime;
import net.htlgkr.kohlbauers190178.padnote.util.MyTimePicker;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.FragmentStateViewModel;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.NoteDataViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditNotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNotesFragment extends DialogFragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;


    public EditNotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditTextViewPopUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditNotesFragment newInstance(String param1, String param2, String param3) {
        EditNotesFragment fragment = new EditNotesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }


    EditText editTextTitle;
    EditText editTextDescription;
    EditText editTextText;

    TextView txtViewDate;
    TextView txtViewTime;


    NoteDataViewModel noteDataViewModel;
    FragmentStateViewModel fragmentStateViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_notes, container, false);

        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextText = view.findViewById(R.id.editTextText);

        txtViewDate = view.findViewById(R.id.txtViewInEditNoteDate);
        txtViewTime = view.findViewById(R.id.txtViewInEditNoteTime);

        txtViewDate.setOnClickListener(this);
        txtViewTime.setOnClickListener(this);

        noteDataViewModel = new ViewModelProvider(requireActivity()).get(NoteDataViewModel.class);
        fragmentStateViewModel = new ViewModelProvider(requireActivity()).get(FragmentStateViewModel.class);

        view.findViewById(R.id.btnSaveEdit).setOnClickListener(this);

        //Note noteModel = noteDataViewModel.selectedNote.getValue();

        Note noteModel = noteDataViewModel.allNotes.getValue().get(noteDataViewModel.selectedNoteNr);
        editTextTitle.setText(noteModel.getTitle());
        editTextDescription.setText(noteModel.getDescription());
        editTextText.setText(noteModel.getText());

        if (noteModel.getDate() != 0 && noteModel.getMyTime() != null) {
            txtViewDate.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(noteModel.getDate()));

            String time = noteModel.getMyTime().getHours() + ":" + String.format(Locale.getDefault(), "%02d", noteModel.getMyTime().getMinutes());
            txtViewTime.setText(time);
        }


        return view;
    }


    MyDatePicker myDatePicker;
    MyTimePicker myTimePicker;


    @Override
    public void onClick(View view) {

        Note curentNote = noteDataViewModel.allNotes.getValue().get(noteDataViewModel.selectedNoteNr);

        if (view.getId() == R.id.btnSaveEdit) {
            ArrayList<Note> notes = noteDataViewModel.allNotes.getValue();

            String title = "" + editTextTitle.getText().toString();
            String description = "" + editTextDescription.getText().toString();
            String text = "" + editTextText.getText().toString();

            if (notes == null) {
                notes = new ArrayList<>();
            }

            Note note = noteDataViewModel.allNotes.getValue().get(noteDataViewModel.selectedNoteNr);

            Note tempnote = new Note(title, description, text);

            if (note.getDate() != 0 && note.getMyTime() != null) {


                tempnote.setDate(note.getDate());
                tempnote.setMyTime(note.getMyTime());
                notes.set(noteDataViewModel.selectedNoteNr, tempnote);
            } else {
                notes.set(noteDataViewModel.selectedNoteNr, tempnote);
            }


            Gson gson = new Gson();

            JsonManager.writeToJson(getContext(), gson.toJson(notes));

            noteDataViewModel.updateAllNotes(notes);
            fragmentStateViewModel.showMain();
        } else if (view.getId() == R.id.txtViewInEditNoteDate) {
            myDatePicker = new MyDatePicker();
            myDatePicker.setDatePickerListener(selection -> {
                curentNote.setDate(selection);
                txtViewDate.setText(new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(selection));
            });
            myDatePicker.showDatePicker(requireActivity().getSupportFragmentManager());
        } else if (view.getId() == R.id.txtViewInEditNoteTime) {
            if (curentNote.getDate() != 0 && curentNote.getMyTime() != null) {
                myTimePicker = new MyTimePicker(curentNote.getMyTime().getHours(), curentNote.getMyTime().getMinutes());
            } else {
                myTimePicker = new MyTimePicker();
            }
            myTimePicker.setTimePickerListener(v -> {
                curentNote.setMyTime(new MyTime(myTimePicker.getTimePicker().getHour(), myTimePicker.getTimePicker().getMinute()));

                String time = myTimePicker.getTimePicker().getHour() + ":" + String.format(Locale.getDefault(), "%02d", myTimePicker.getTimePicker().getMinute());
                txtViewTime.setText(time);
            });
            myTimePicker.showMyTimePicker(requireActivity().getSupportFragmentManager());
        }

    }


}