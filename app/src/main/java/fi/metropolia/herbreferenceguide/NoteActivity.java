package fi.metropolia.herbreferenceguide;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

import fi.metropolia.herbreferenceguide.database.Note;
import fi.metropolia.herbreferenceguide.database.NoteDatabase;

public class NoteActivity extends AppCompatActivity {
    private FloatingActionButton fabAddNote;
    private List<Note> noteList;

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        loadNoteData();
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(getString(R.string.note_label));
        loadNoteData();
        initRecyclerView();
        fabAddNote = findViewById(R.id.fabAddNote);
        fabAddNote.setOnClickListener(view -> {
            openSomeActivityForResult();
        });
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.noteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        NoteAdapter noteAdapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(noteAdapter);
    }
    private void loadNoteData() {
        NoteDatabase noteDatabase = NoteDatabase.getINSTANCE(NoteActivity.this);
        noteList = noteDatabase.noteDao().getAllNote();
    }
    public void openSomeActivityForResult() {
        Intent intent = new Intent(this, NoteAddingActivity.class);
        someActivityResultLauncher.launch(intent);
    }
}