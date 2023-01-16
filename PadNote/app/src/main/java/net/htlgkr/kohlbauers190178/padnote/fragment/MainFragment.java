package net.htlgkr.kohlbauers190178.padnote.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.htlgkr.kohlbauers190178.padnote.MyAdapter;
import net.htlgkr.kohlbauers190178.padnote.R;
import net.htlgkr.kohlbauers190178.padnote.model.Note;
import net.htlgkr.kohlbauers190178.padnote.util.JsonManager;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.FragmentStateViewModel;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.NoteDataViewModel;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.SettingsViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainFragment() {
        // Required empty public constructor
    }

    ArrayList<Note> notes = new ArrayList<>();

    private void loadNotes() {
        String loaded = JsonManager.readFromJson(getContext());


        if (loaded == null) {
            return;
        }

        TypeToken<Collection<Note>> token = new TypeToken<Collection<Note>>() {
        };
        Gson gson = new Gson();
        notes = gson.fromJson(loaded, token.getType());
<<<<<<< HEAD
=======

>>>>>>> 84c78b3e5c8214b1f7cf7ddd28aba9dece9fec9c
        noteDataViewModel.updateAllNotes(notes);
    }

    RecyclerView recyclerView;
    NoteDataViewModel noteDataViewModel;

    FragmentStateViewModel fragmentStateViewModel;
    SettingsViewModel settingsViewModel;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        view.findViewById(R.id.btnAddNote).setOnClickListener(this);
        view.findViewById(R.id.btnShowSettings).setOnClickListener(this);
        noteDataViewModel = new ViewModelProvider(requireActivity()).get(NoteDataViewModel.class);
        fragmentStateViewModel = new ViewModelProvider(requireActivity()).get(FragmentStateViewModel.class);
        settingsViewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);


        settingsViewModel.loadSettings(getContext());

        recyclerView = view.findViewById(R.id.rclrViewNotes);

        loadNotes();

        if (notes == null) {
            notes = new ArrayList<>();
        }

        noteDataViewModel.updateAllNotes(notes);
        MyAdapter adapter = null;
        if (settingsViewModel.SHOWEXPIREDNOTES) {
            adapter = new MyAdapter(notes, noteDataViewModel, fragmentStateViewModel, settingsViewModel);
        } else {
            adapter = new MyAdapter(notes.stream().filter(note -> note.getDateAndTime() >= System.currentTimeMillis()).collect(Collectors.toList()), noteDataViewModel, fragmentStateViewModel, settingsViewModel);
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddNote) {
            fragmentStateViewModel.showNewNote();
        } else if (view.getId() == R.id.btnShowSettings) {
            fragmentStateViewModel.showSettings();
        }
    }
}