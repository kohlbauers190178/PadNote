package net.htlgkr.kohlbauers190178.padnote;

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

import net.htlgkr.kohlbauers190178.padnote.model.Note;
import net.htlgkr.kohlbauers190178.padnote.util.JsonManager;

import java.util.ArrayList;
import java.util.Collection;

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

        TypeToken<Collection<Note>> token = new TypeToken<Collection<Note>>(){};
        Gson gson = new Gson();
        notes = gson.fromJson(loaded,token.getType());
            /*
            JSONObject jsonObject = new JSONObject(loaded);
            JSONArray jsonArray = jsonObject.getJSONArray("notes");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject temp = jsonArray.getJSONObject(i);
                Note noteModel = new Note(temp.getString(JSONConstants.TITLE), temp.getString(JSONConstants.DESCRIPTION), temp.getString(JSONConstants.TEXT));
                notes.add(noteModel);
            }*/

        noteDataViewModel.updateAllNotes(notes);
    }

    RecyclerView recyclerView;
    NoteDataViewModel noteDataViewModel;

    FragmentStateViewModel viewModel;

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

        //view.findViewById(R.id.txtViewMain).setOnClickListener(this);
        view.findViewById(R.id.btnAddNote).setOnClickListener(this);
        noteDataViewModel = new ViewModelProvider(requireActivity()).get(NoteDataViewModel.class);
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentStateViewModel.class);

        recyclerView = view.findViewById(R.id.rclrViewNotes);

        loadNotes();

        if(notes==null){
            notes = new ArrayList<>();
        }

        noteDataViewModel.updateAllNotes(notes);
        MyAdapter adapter = new MyAdapter(notes, noteDataViewModel, viewModel, getActivity().getSupportFragmentManager());



        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddNote) {
            viewModel.showNewNote();
        }
    }
}