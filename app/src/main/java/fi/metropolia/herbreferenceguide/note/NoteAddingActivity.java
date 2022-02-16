package fi.metropolia.herbreferenceguide.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.database.Note;
import fi.metropolia.herbreferenceguide.database.AppDatabase;

public class NoteAddingActivity extends AppCompatActivity {
    private Button btnAdd;
    private EditText title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_adding);
        setTitle(getString(R.string.addNote_label));
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(view -> saveNote());
    }

    private void saveNote() {
        AppDatabase noteDatabase = AppDatabase.getINSTANCE(this);
        title = findViewById(R.id.edtTitleAdd);
        description = findViewById(R.id.edtDescriptionAdd);
        Note newNote = new Note();
        newNote.setNoteTitle(title.getText().toString());
        newNote.setNoteDescription(description.getText().toString());
        noteDatabase.noteDao().insertNote(newNote);
        returnToNoteActivity();
    }
    private void returnToNoteActivity() {
        Intent intent = new Intent(NoteAddingActivity.this, NoteActivity.class);
        startActivity(intent);
    }
}