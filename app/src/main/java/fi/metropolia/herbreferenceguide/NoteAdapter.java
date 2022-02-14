package fi.metropolia.herbreferenceguide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fi.metropolia.herbreferenceguide.database.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private List<Note> noteList;

    public NoteAdapter(List<Note> noteData) {
        this.noteList = noteData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_recycler_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(noteList.get(position).note_title);
        holder.tvDescription.setText(noteList.get(position).note_description);
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}
