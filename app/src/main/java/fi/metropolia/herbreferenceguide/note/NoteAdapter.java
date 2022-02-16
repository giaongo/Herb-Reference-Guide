package fi.metropolia.herbreferenceguide.note;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.Note;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private final List<Note> noteList;
    private final RecyclerViewInterface recyclerViewInterface;

    public NoteAdapter(List<Note> noteData, RecyclerViewInterface recyclerViewInterface) {
        this.noteList = noteData;
        this.recyclerViewInterface = recyclerViewInterface;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_recycler_layout,parent,false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(noteList.get(position).getNoteTitle());
        holder.tvDescription.setText(noteList.get(position).getNoteDescription());
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle, tvDescription;
        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvDescription = itemView.findViewById(R.id.tvDescription);

            itemView.setOnClickListener(view -> {
                if(recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });
        }

    }


}
