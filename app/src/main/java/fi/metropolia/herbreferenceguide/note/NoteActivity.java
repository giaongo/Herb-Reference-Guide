package fi.metropolia.herbreferenceguide.note;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Note;

public class NoteActivity extends AppCompatActivity implements RecyclerViewInterface {
    private List<Note> noteList;
    protected static final String NOTE_POSITION = "Note position";
    protected static final String TITLE_UPDATE = "Note Title";
    protected static final String DESCRIPTION_UPDATE = "Note Description";
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    loadNoteData();
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(getString(R.string.note_label));
        loadNoteData();
        initRecyclerView();
        FloatingActionButton fabAddNote = findViewById(R.id.fabAddNote);
        fabAddNote.setOnClickListener(view -> openNoteActivityForResult());
    }

    private void loadNoteData() {
        AppDatabase noteDatabase = AppDatabase.getINSTANCE(NoteActivity.this);
        noteList = noteDatabase.noteDao().getAllNote();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.noteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteAdapter noteAdapter = new NoteAdapter(noteList, this);
        recyclerView.setAdapter(noteAdapter);
    }

    public void openNoteActivityForResult() {
        Intent intent = new Intent(this, NoteAddingActivity.class);
        activityResultLauncher.launch(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(NoteActivity.this, NoteUpdatingActivity.class);
        int noteId = noteList.get(position).getNoteId();
        String noteTitle = noteList.get(position).getNoteTitle();
        String noteDescription = noteList.get(position).getNoteDescription();

        intent.putExtra(NOTE_POSITION, noteId);
        intent.putExtra(TITLE_UPDATE, noteTitle);
        intent.putExtra(DESCRIPTION_UPDATE, noteDescription);
        activityResultLauncher.launch(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
                finish();
                return true;
        }
        return false;
    }
}