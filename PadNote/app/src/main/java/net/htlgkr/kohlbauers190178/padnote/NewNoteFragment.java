package net.htlgkr.kohlbauers190178.padnote;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

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

    MyViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_note, container, false);

        view.findViewById(R.id.btnNewNoteSave).setOnClickListener(this);
        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        textViewDataModel = new ViewModelProvider(requireActivity()).get(TextViewDataModel.class);
        return view;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnNewNoteSave) {
            if (saveNote()) {
                viewModel.showMain();
            } else {
                //maybe for future
            }
        }

    }


    TextViewDataModel textViewDataModel;

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
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("title", title);
            jsonObject.put("description", description);
            jsonObject.put("text", "");


            /*FileInputStream inputStream = getContext().openFileInput("note.json");


            int size = inputStream.available();

            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);*/

            String json = JsonManager.readFromJson(getContext());

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

            /*FileOutputStream fileOutputStream = getContext().openFileOutput("note.json", Context.MODE_PRIVATE);
            fileOutputStream.write(main.toString().getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();*/
            JsonManager.writeToJson(getContext(), main);

            String notes = loadNotes(getContext());
            updateNotes(notes);
            return true;
        } catch (Exception e) {
            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void updateNotes(String notes) {
        textViewDataModel.updateText(notes);
    }

    public static String loadNotes(Context context) {

        //int size = 0;
        StringBuilder temp = new StringBuilder();

        try {
            /*FileInputStream inputStream = getContext().openFileInput("note.json");
            size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, StandardCharsets.UTF_8);*/


            String json = JsonManager.readFromJson(context);

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("notes");


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject object = jsonArray.getJSONObject(i);

                temp.append(object.getString("title")).append("\n");
                temp.append(object.getString("description")).append("\n");
                temp.append(object.getString("text")).append("\n");
                temp.append("\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return temp.toString();
    }
}