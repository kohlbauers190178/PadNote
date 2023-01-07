package net.htlgkr.kohlbauers190178.padnote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import net.htlgkr.kohlbauers190178.padnote.model.Note;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.FragmentStateViewModel;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.NoteDataViewModel;
import net.htlgkr.kohlbauers190178.padnote.viewmodel.SettingsViewModel;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {


    List<Note> notes;
    NoteDataViewModel noteDataViewModel;
    FragmentStateViewModel viewModel;
    SettingsViewModel settingsViewModel;


    public MyAdapter(List<Note> notes, NoteDataViewModel noteDataViewModel, FragmentStateViewModel viewModel, SettingsViewModel settingsViewModel) {
        this.notes = notes;
        this.noteDataViewModel = noteDataViewModel;
        this.viewModel = viewModel;
        this.settingsViewModel = settingsViewModel;
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


        long date = notes.get(position).getDateAndTime();
        //MyTime myTime = notes.get(position).getMyTime();

        LocalDateTime localDateTime = Instant.ofEpochMilli(date).atZone(ZoneId.systemDefault()).toLocalDateTime();

        if (localDateTime != null) {

            if (localDateTimeIsValid(localDateTime)) {
                DateTimeFormatter dateFormatter = settingsViewModel.currentDateFormatter;
                DateTimeFormatter timeFormatter = settingsViewModel.currentTimeFormatter;

                holder.txtViewDate.setText(localDateTime.toLocalDate().format(dateFormatter));
                holder.txtViewTime.setText(localDateTime.toLocalTime().format(timeFormatter));
            }
        }

        boolean noteDone = notes.get(position).isDone();

        holder.chchBxIsDone.setChecked(noteDone);

        if (notes.get(position).getDateAndTime() != 0) {

            if (notes.get(position).getDateAndTime() >= System.currentTimeMillis()) {
                //note isn't expired
                holder.imgViewNoteExpired.setImageResource(R.drawable.timelapse_48px);
            } else if (notes.get(position).getDateAndTime() < System.currentTimeMillis()) {
                //note is expired
                holder.imgViewNoteExpired.setImageResource(R.drawable.warning_48px);
            }
        }else{
            holder.imgViewNoteExpired.setImageDrawable(null);
        }

        for (int i = 0; i < holder.parentLayout.getChildCount(); i++) {
            if (holder.parentLayout.getChildAt(i) instanceof CardView) {
                holder.parentLayout.getChildAt(i).setOnClickListener(holder.listener);
            }

        }


    }

    private boolean localDateTimeIsValid(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().getMonthValue() != 0 && localDateTime.toLocalDate().getDayOfMonth() != 0;
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
        TextView txtViewDate;
        TextView txtViewTime;

        CheckBox chchBxIsDone;

        ImageView imgViewNoteExpired;

        ConstraintLayout parentLayout;

        View.OnClickListener listener;


        public MyViewHolder(View itemView) {
            super(itemView);
            txtViewTitle = itemView.findViewById(R.id.txtViewTitle);
            txtViewDescription = itemView.findViewById(R.id.txtViewDescription);
            txtViewText = itemView.findViewById(R.id.txtViewText);

            txtViewDate = itemView.findViewById(R.id.txtViewDate);
            txtViewTime = itemView.findViewById(R.id.txtViewTime);

            chchBxIsDone = itemView.findViewById(R.id.chckBxNoteDone);

            imgViewNoteExpired = itemView.findViewById(R.id.imgViewNoteExpired);

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
