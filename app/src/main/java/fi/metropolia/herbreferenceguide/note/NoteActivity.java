package fi.metropolia.herbreferenceguide.note;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;
import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Note;

/**
 * <h1>Display all notes</h1>
 * This activity displays all notes stored in room database and from input of users.
 * To accomplish the front-end layout to back-end loading data from database,
 * the concept of RecyclerView with its event click listener is acquired.
 *
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-02-21
 */
public class NoteActivity extends AppCompatActivity implements RecyclerViewInterface {
    private List<Note> noteList;
    protected static final String NOTE_POSITION = "Note position";
    protected static final String TITLE_UPDATE = "Note Title";
    protected static final String DESCRIPTION_UPDATE = "Note Description";

    /**
     * Menu title, note data is displayed. Registers click event listener for floating action
     * button.
     * @param savedInstanceState savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(getString(R.string.note_label));
        loadNoteData();
        initRecyclerView();
        FloatingActionButton fabAddNote = findViewById(R.id.fabAddNote);
        fabAddNote.setOnClickListener(view -> openNoteActivity());
    }

    /**
     * Loads note data from database and assigns it to note list.
     */
    private void loadNoteData() {
        AppDatabase noteDatabase = AppDatabase.getINSTANCE(NoteActivity.this);
        noteList = noteDatabase.noteDao().getAllNote();
    }

    /**
     * Initialises recyclerview with layout manager.
     * Creates a new note adapter instance and assigns that adapter to recyclerView.
     * Code reference:
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Create dynamic lists with RecyclerView </a>
     */
    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.noteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteAdapter noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);
    }

    /**
     * Launches NoteAddingActivity
     */
    public void openNoteActivity() {
        Intent intent = new Intent(this, NoteAddingActivity.class);
        startActivity(intent);
    }

    /**
     *  Registers event click for Recycler View item click
     * @param position position where item is clicked
     * @see <a href="https://www.youtube.com/watch?v=7GPUpvcU1FE&ab_channel=PracticalCoding">
     * RecyclerView Item Click | Best Practice Way</a>
     */
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(NoteActivity.this, NoteUpdatingActivity.class);
        int noteId = noteList.get(position).getNoteId();
        String noteTitle = noteList.get(position).getNoteTitle();
        String noteDescription = noteList.get(position).getNoteDescription();
        intent.putExtra(NOTE_POSITION, noteId);
        intent.putExtra(TITLE_UPDATE, noteTitle);
        intent.putExtra(DESCRIPTION_UPDATE, noteDescription);
        startActivity(intent);
    }
}