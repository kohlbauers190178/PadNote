package net.htlgkr.kohlbauers190178.padnote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    List<NoteModel> noteModels;

    public MyAdapter(Context context, List<NoteModel> noteModels) {
        this.context = context;
        this.noteModels = noteModels;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

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
