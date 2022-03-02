package fi.metropolia.herbreferenceguide.note;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import fi.metropolia.herbreferenceguide.*;
import fi.metropolia.herbreferenceguide.database.Note;
import fi.metropolia.herbreferenceguide.database.AppDatabase;

/**
 * <h1>Adding notes</h1>
 * This class implements the application of asking for user note inputs and saving that note data to
 * local database.
 *
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-02-21
 */
public class NoteAddingActivity extends AppCompatActivity {

    /**
     * Set title for activity and register event click listener
     * @param savedInstanceState bundle on Create
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_adding);
        setTitle(getString(R.string.addNote_label));
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> saveNote());
    }

    /**
     * This function retrieves the data from user input and saves them to room database.
     * After that, it automatically returns back to NoteActivity.
     */
    private void saveNote() {
        AppDatabase noteDatabase = AppDatabase.getINSTANCE(this);
        EditText title = findViewById(R.id.edtTitleAdd);
        EditText description = findViewById(R.id.edtDescriptionAdd);
        Note newNote = new Note(0,title.getText().toString(),description.getText().toString());
        noteDatabase.noteDao().insertNote(newNote);
        returnToNoteActivity();
    }

    /**
     * This function launches NoteActivity class.
     */
    private void returnToNoteActivity() {
        Intent intent = new Intent(NoteAddingActivity.this, NoteActivity.class);
        startActivity(intent);
    }
}