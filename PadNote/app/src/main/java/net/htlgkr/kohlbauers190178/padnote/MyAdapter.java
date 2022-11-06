package net.htlgkr.kohlbauers190178.padnote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {

    Context context;
    List<NoteModel> noteModels;
    TextViewDataModel textViewDataModel;
    MyViewModel viewModel;

    public MyAdapter(Context context, List<NoteModel> noteModels, TextViewDataModel textViewDataModel, MyViewModel viewModel) {
        this.context = context;
        this.noteModels = noteModels;
        this.textViewDataModel = textViewDataModel;
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        view.findViewById(R.id.cardView).setOnClickListener(this);


        return new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.txtViewTitle.setText(noteModels.get(position).getTitle());
        holder.txtViewDescription.setText(noteModels.get(position).getDescription());
        holder.txtViewText.setText(noteModels.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return noteModels.size();
    }

    @Override
    public void onClick(View view) {

        CardView cardView = (CardView) view;



        ConstraintLayout constraintLayout = (ConstraintLayout) cardView.getChildAt(0);

        String title = "";
        String description = "";
        String text = "";

        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            TextView textView = (TextView) constraintLayout.getChildAt(i);
            switch (textView.getId()) {
                case R.id.txtViewTitle:
                    title = textView.getText().toString();
                    break;
                case R.id.txtViewDescription:
                    description = textView.getText().toString();
                    break;
                case R.id.txtViewText:
                    text = textView.getText().toString();
                    break;
            }
        }


        String json = JsonManager.readFromJson(context);

        //gets the objectarray from the file
        JSONObject temp = null;
        try {
            temp = new JSONObject(json);
            JSONArray arr = temp.getJSONArray("notes");
            List<JSONObject> jsonObjectList = new ArrayList<>();

            //creates a list from all objects from the object[]
            for (int i = 0; i < arr.length(); i++) {
                jsonObjectList.add(arr.getJSONObject(i));
            }


            //TODO: add edit note
            for (int i = 0; i < jsonObjectList.size(); i++) {
                JSONObject object = jsonObjectList.get(i);
                if (object.getString(JSONConstants.TITLE).equals(title)) {
                    JSONObject newJSONObject = new JSONObject();
                    newJSONObject.put(JSONConstants.TITLE, "changed title");
                    newJSONObject.put(JSONConstants.DESCRIPTION, description);
                    newJSONObject.put(JSONConstants.TEXT, text);

                    jsonObjectList.set(i, newJSONObject);
                    break;
                }
            }

            JSONArray jsonArray = new JSONArray(jsonObjectList);


            JSONObject main = new JSONObject();
            main.put("notes", jsonArray);

            /*FileOutputStream fileOutputStream = getContext().openFileOutput("note.json", Context.MODE_PRIVATE);
            fileOutputStream.write(main.toString().getBytes(StandardCharsets.UTF_8));
            fileOutputStream.close();*/
            JsonManager.writeToJson(context, main);

            String notes = NewNoteFragment.loadNotes(context);
            textViewDataModel.updateText(notes);

            viewModel.showEditPopUp();



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtViewTitle;
        TextView txtViewDescription;
        TextView txtViewText;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtViewTitle = itemView.findViewById(R.id.txtViewTitle);
            txtViewDescription = itemView.findViewById(R.id.txtViewDescription);
            txtViewText = itemView.findViewById(R.id.txtViewText);
        }
    }


}
