package fi.metropolia.herbreferenceguide.note;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fi.metropolia.herbreferenceguide.*;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Note;

/**
 * This class implements an application that simply allows users to update theirs notes and delete
 * their notes from local database.
 *
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-02-21
 */
public class NoteUpdatingActivity extends AppCompatActivity {
    private EditText titleUpdate, descriptionUpdate;
    private int notePosition;
    private String title, description;
    private AppDatabase database;

    /**
     * Sets title, displays existing data and registers event listeners for button Update and Delete
     * @param savedInstanceState Bundle savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_updating);
        setTitle(getString(R.string.updateNote_label));
        loadData();
        database = AppDatabase.getINSTANCE(NoteUpdatingActivity.this);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        btnUpdate.setOnClickListener(view -> updateNoteToDatabase());
        btnDelete.setOnClickListener(view -> deleteNoteFromDatabase());
    }

    /**
     * Retrieves data from intent and sets content to Note title and description
     */
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

    /**
     * Updates new note data to local database and automatically returns to NoteActivity at last.
     */
    private void updateNoteToDatabase() {
        String updatedTitle = titleUpdate.getText().toString();
        String updatedDescription = descriptionUpdate.getText().toString();
        database.noteDao().updateNote(updatedTitle, updatedDescription, notePosition);
        Toast.makeText(NoteUpdatingActivity.this, "Update note successfully", Toast.LENGTH_SHORT).show();
        returnToNoteActivity();
    }

    /**
     * Deletes the note data from local database and automatically returns to NoteActivity at last.
     */
    private void deleteNoteFromDatabase() {
        database.noteDao().delete(new Note(notePosition,title,description));
        Toast.makeText(NoteUpdatingActivity.this, "Delete note successfully", Toast.LENGTH_SHORT).show();
        returnToNoteActivity();
    }

    /**
     * Triggers intent and launch NoteActivity class.
     */
    private void returnToNoteActivity() {
        Intent intent = new Intent(NoteUpdatingActivity.this, NoteActivity.class);
        startActivity(intent);
    }

}