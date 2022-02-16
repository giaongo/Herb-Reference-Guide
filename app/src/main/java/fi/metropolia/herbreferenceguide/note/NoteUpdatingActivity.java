package fi.metropolia.herbreferenceguide.note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.database.AppDatabase;

public class NoteUpdatingActivity extends AppCompatActivity {
    Button btnUpdate, btnDelete;
    EditText titleUpdate, descriptionUpdate;
    int notePosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_updating);
        loadData();
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(view -> updateNoteToDatabase());

    }

    private void loadData() {
        Intent intent = getIntent();
        notePosition = intent.getIntExtra(NoteActivity.NOTE_POSITION,0);
        String title = intent.getStringExtra(NoteActivity.TITLE_UPDATE);
        String description = intent.getStringExtra(NoteActivity.DESCRIPTION_UPDATE);

        titleUpdate = findViewById(R.id.edtTitleUpdate);
        descriptionUpdate = findViewById(R.id.edtDescriptionUpdate);
        titleUpdate.setText(title);
        descriptionUpdate.setText(description);
    }

    private void updateNoteToDatabase() {
        String updatedTitle = titleUpdate.getText().toString();
        String updatedDescription = descriptionUpdate.getText().toString();
        AppDatabase database = AppDatabase.getINSTANCE(NoteUpdatingActivity.this);
        int result = database.noteDao().updateNote(updatedTitle, updatedDescription, notePosition);
        Toast.makeText(NoteUpdatingActivity.this, "Update : " + result, Toast.LENGTH_SHORT).show();
        returnToNoteActivity();
    }

    private void returnToNoteActivity() {
        Intent intent = new Intent(NoteUpdatingActivity.this, NoteActivity.class);
        startActivity(intent);
    }

}