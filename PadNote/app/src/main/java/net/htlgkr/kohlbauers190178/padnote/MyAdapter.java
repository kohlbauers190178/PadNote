package net.htlgkr.kohlbauers190178.padnote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Consumer;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {


    List<Note> notes;
    NoteDataViewModel noteDataViewModel;
    FragmentStateViewModel viewModel;


    public MyAdapter(List<Note> notes, NoteDataViewModel noteDataViewModel, FragmentStateViewModel viewModel, FragmentManager fragmentManager) {
        this.notes = notes;
        this.noteDataViewModel = noteDataViewModel;
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        view.findViewById(R.id.cardView).setOnClickListener(this);


        return new MyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.txtViewTitle.setText(notes.get(position).getTitle());
        holder.txtViewDescription.setText(notes.get(position).getDescription());
        holder.txtViewText.setText(notes.get(position).getText());

        for (int i = 0; i < holder.parentLayout.getChildCount(); i++) {
            if (holder.parentLayout.getChildAt(i) instanceof CardView) {
                holder.parentLayout.getChildAt(i).setOnClickListener(holder.listener);
            }

        }

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    @Override
    public void onClick(View view) {

/*
        CardView cardView = (CardView) view;

        ConstraintLayout constraintLayout = (ConstraintLayout) cardView.getChildAt(0);

        String title = "";
        String description = "";
        String text = "";

        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            TextView textView = (TextView) constraintLayout.getChildAt(i);
            int id = textView.getId();
            if (id == R.id.txtViewTitle) {
                title = textView.getText().toString();
            } else if (id == R.id.txtViewDescription) {
                description = textView.getText().toString();
            } else if (id == R.id.txtViewText) {
                text = textView.getText().toString();
            }
        }


        //noteDataViewModel.updateSelectedNote(new Note(title, description, text));
        //viewModel.showEdit();*/
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtViewTitle;
        TextView txtViewDescription;
        TextView txtViewText;
        ConstraintLayout parentLayout;

        View.OnClickListener listener;



        public MyViewHolder(View itemView) {
            super(itemView);
            txtViewTitle = itemView.findViewById(R.id.txtViewTitle);
            txtViewDescription = itemView.findViewById(R.id.txtViewDescription);
            txtViewText = itemView.findViewById(R.id.txtViewText);
            parentLayout = itemView.findViewById(R.id.parentLayout);

            listener = this;
        }

        @Override
        public void onClick(View view) {

            noteDataViewModel.updateSelectedNoteNr(getLayoutPosition());
            viewModel.showEdit();
        }
    }

}
