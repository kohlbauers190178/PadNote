package net.htlgkr.kohlbauers190178.padnote;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditNotesPopUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNotesPopUpFragment extends DialogFragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditNotesPopUpFragment() {
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
    public static EditNotesPopUpFragment newInstance(String param1, String param2) {
        EditNotesPopUpFragment fragment = new EditNotesPopUpFragment();
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

    ArrayList<NoteModel> noteModels = new ArrayList<>();

    private void loadNoteEditButtons() {
        try {
            JSONObject jsonObject = new JSONObject(JsonManager.readFromJson(getContext()));
            JSONArray jsonArray = jsonObject.getJSONArray("notes");


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject temp = jsonArray.getJSONObject(i);
                NoteModel noteModel = new NoteModel(temp.getString("title"), temp.getString("description"), temp.getString("text"));
                noteModels.add(noteModel);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    ConstraintLayout layout;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_notes_pop_up, container, false);

        layout = view.findViewById(R.id.cnstrntLayoutEditNotesPopUp);
        recyclerView = view.findViewById(R.id.rclrViewNotes);

        loadNoteEditButtons();

        MyAdapter adapter = new MyAdapter(getContext(), noteModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


    @Override
    public void onClick(View view) {

    }
}