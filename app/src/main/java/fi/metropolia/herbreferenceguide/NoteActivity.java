package fi.metropolia.herbreferenceguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class NoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        RecyclerView recyclerView = findViewById(R.id.noteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Note> noteList = new ArrayList<>();
        noteList.add(new Note("Orange Nutrition", "help to fight with cancer"));
        noteList.add(new Note("Fruit vitamin", "increase health resistance"));
        noteList.add(new Note("Title 3", "this is a test"));
        NoteAdapter noteAdapter = new NoteAdapter(noteList);

        recyclerView.setAdapter(noteAdapter);

    }
}