package net.htlgkr.kohlbauers190178.padnote;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewNoteFragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewNoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewNoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewNoteFragment newInstance(String param1, String param2) {
        NewNoteFragment fragment = new NewNoteFragment();
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

    FragmentStateViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_note, container, false);

        view.findViewById(R.id.btnNewNoteSave).setOnClickListener(this);
        view.findViewById(R.id.btnAddDate).setOnClickListener(this);
        view.findViewById(R.id.btnAddTime).setOnClickListener(this);
        viewModel = new ViewModelProvider(requireActivity()).get(FragmentStateViewModel.class);
        noteDataViewModel = new ViewModelProvider(requireActivity()).get(NoteDataViewModel.class);

        return view;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnNewNoteSave) {
            if (saveNote()) {
                viewModel.showMain();
            }
        } else if (view.getId() == R.id.btnAddDate) {
            long date = pickDate();
        } else if (view.getId() == R.id.btnAddTime) {
            MyTime time = pickTime();
        }

    }

    //TODO: implement getting date and time
    private long pickDate() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date (Optional)").build();
        datePicker.show(requireActivity().getSupportFragmentManager(), "");
        if (datePicker.getSelection() == null) {
            return 0;
        }
        return datePicker.getSelection();
    }

    private MyTime pickTime() {
        MaterialTimePicker timePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H).setHour(12).setMinute(10).setTitleText("Select Time").build();
        timePicker.show(requireActivity().getSupportFragmentManager(), "");

        return null;
      //  return new MyTime(timePicker.getHour(), timePicker.getMinute());
    }


    NoteDataViewModel noteDataViewModel;

    private boolean saveNote() {
        //saves the note

        TextInputEditText txtInputTitle = getActivity().findViewById(R.id.txtInputTitle);
        TextInputEditText txtInputDescription = getActivity().findViewById(R.id.txtInputDescription);
        String title;
        String description;
        try {

            title = txtInputTitle.getText().toString();
            description = txtInputDescription.getText().toString();

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(getActivity(), "please enter both values", Toast.LENGTH_SHORT).show();
                return false;
            }

            //new object from textinput-values
            /*JSONObject jsonObject = new JSONObject();
            jsonObject.put(JSONConstants.TITLE, title);
            jsonObject.put(JSONConstants.DESCRIPTION, description);
            jsonObject.put(JSONConstants.TEXT, "");*/


            ArrayList<Note> allNotes = noteDataViewModel.allNotes.getValue();

            if (allNotes != null) {
                allNotes.add(new Note(title, description, ""));
            } else {
                allNotes = new ArrayList<>();
                allNotes.add(new Note(title, description, ""));
            }

            Gson gson = new Gson();

            String jsonstring = gson.toJson(allNotes);

            int i = 0;

            JsonManager.writeToJson(getContext(), jsonstring);



            /*String json = JsonManager.readFromJson(getContext());

            if (json == null) {
                JSONObject topObject = new JSONObject();
                JSONArray newArray = new JSONArray(Arrays.asList(jsonObject));
                topObject.put("notes", newArray);

                JsonManager.writeToJson(getContext(), topObject);
            } else {
                //gets the objectarray from the file
                JSONObject temp = new JSONObject(json);
                JSONArray arr = temp.getJSONArray("notes");

                List<Object> jsonObjectList = new ArrayList<>();

                //creates a list from all objects from the object[]
                for (int i = 0; i < arr.length(); i++) {
                    jsonObjectList.add(arr.get(i));
                }
                jsonObjectList.add(jsonObject);
                //adds the new object to the list and creates a new jsonarray
                JSONArray jsonArray = new JSONArray(jsonObjectList);

                JSONObject main = new JSONObject();
                main.put("notes", jsonArray);

                JsonManager.writeToJson(getContext(), main);
            }*/


            return true;
        } catch (Exception e) {
            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}