package fi.metropolia.herbreferenceguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import fi.metropolia.herbreferenceguide.database.Note;
import fi.metropolia.herbreferenceguide.database.NoteDatabase;

public class NoteAddingActivity extends AppCompatActivity {
    private Button btnSave;
    private EditText title, description;
    protected final static String TITLE_MESSAGE = "title";
    protected final static String DESCRIPTION_MESSAGE = "description";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_adding);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> saveNote());
    }

    private void saveNote() {
        NoteDatabase noteDatabase = NoteDatabase.getINSTANCE(this);
        title = findViewById(R.id.edtTitle);
        description = findViewById(R.id.edtDescription);
        Note newNote = new Note();
        newNote.note_title = title.getText().toString();
        newNote.note_description = description.getText().toString();
        noteDatabase.noteDao().insertNote(newNote);
        returnToNoteActivity();
    }
    private void returnToNoteActivity() {
        Intent intent = new Intent(NoteAddingActivity.this, NoteActivity.class);
        intent.putExtra(TITLE_MESSAGE,title.toString());
        intent.putExtra(DESCRIPTION_MESSAGE,description.toString());
        startActivity(intent);
    }
}