package fi.metropolia.herbreferenceguide.note;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Note;

public class NoteUpdatingActivity extends AppCompatActivity {
    private EditText titleUpdate, descriptionUpdate;
    private int notePosition;
    private String title, description;
    private AppDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_updating);
        loadData();
        database = AppDatabase.getINSTANCE(NoteUpdatingActivity.this);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        btnUpdate.setOnClickListener(view -> updateNoteToDatabase());
        btnDelete.setOnClickListener(view -> deleteNoteFromDatabase());
    }

    private void loadData() {
        Intent intent = getIntent();
        notePosition = intent.getIntExtra(NoteActivity.NOTE_POSITION,0);
        title = intent.getStringExtra(NoteActivity.TITLE_UPDATE);
        description = intent.getStringExtra(NoteActivity.DESCRIPTION_UPDATE);

        titleUpdate = findViewById(R.id.edtTitleUpdate);
        descriptionUpdate = findViewById(R.id.edtDescriptionUpdate);
        titleUpdate.setText(title);
        descriptionUpdate.setText(description);
    }

    private void updateNoteToDatabase() {
        String updatedTitle = titleUpdate.getText().toString();
        String updatedDescription = descriptionUpdate.getText().toString();
        database.noteDao().updateNote(updatedTitle, updatedDescription, notePosition);
        Toast.makeText(NoteUpdatingActivity.this, "Update note successfully", Toast.LENGTH_SHORT).show();
        returnToNoteActivity();
    }

    private void deleteNoteFromDatabase() {
        database.noteDao().delete(new Note(notePosition,title,description));
        Toast.makeText(NoteUpdatingActivity.this, "Delete note successfully", Toast.LENGTH_SHORT).show();
        returnToNoteActivity();
    }
    private void returnToNoteActivity() {
        Intent intent = new Intent(NoteUpdatingActivity.this, NoteActivity.class);
        startActivity(intent);
    }

}