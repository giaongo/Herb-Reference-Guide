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

/**
 * This class defines RecyclerView adapter that simply creates a view holder and binds the data
 * to the view holder.
 *
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-02-21
 */
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private final List<Note> noteList;
    private final RecyclerViewInterface recyclerViewInterface;

    /**
     * Adapter constructor to initialise the instance of NoteAdapter
     * @param noteData  List of notes
     * @param recyclerViewInterface RecyclerViewInterface Interface to register click event
     */
    public NoteAdapter(List<Note> noteData, RecyclerViewInterface recyclerViewInterface) {
        this.noteList = noteData;
        this.recyclerViewInterface = recyclerViewInterface;
        notifyDataSetChanged();
    }

    /**
     * Creates new view
     * @param parent ViewGroup
     * @param viewType  int viewType
     * @return ViewHolder new view
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.note_recycler_layout,parent,false);
        return new ViewHolder(view,recyclerViewInterface);
    }

    /**
     * Sets the contents of a view
     * @param holder ViewHolder
     * @param position the element position from the dataset
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(noteList.get(position).getNoteTitle());
        holder.tvDescription.setText(noteList.get(position).getNoteDescription());
    }

    /**
     * Gets size of the dataset
     * @return int size of dataset
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @Override
    public int getItemCount() {
        return noteList.size();
    }

    /**
     * Defines ViewHolder class that gives a reference to the views
     * Code reference:
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>.
     * Event listener: <a href="https://www.youtube.com/watch?v=7GPUpvcU1FE&ab_channel=PracticalCoding">
     * RecyclerView Item Click | Best Practice Way</a>
     */
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
