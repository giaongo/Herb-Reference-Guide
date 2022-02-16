package fi.metropolia.herbreferenceguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import fi.metropolia.herbreferenceguide.note.NoteActivity;

public class MainActivity extends AppCompatActivity {
    private MaterialCardView mcHerbs, mcFruits, mcVegetables, mcNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardClick cardClick = new CardClick();
        mcHerbs = findViewById(R.id.mcHerbs);
        mcFruits = findViewById(R.id.mcFruits);
        mcVegetables =  findViewById(R.id.mcVegetables);
        mcNote = findViewById(R.id.mcNotes);
        mcHerbs.setOnClickListener(cardClick);
        mcFruits.setOnClickListener(cardClick);
        mcVegetables.setOnClickListener(cardClick);
        mcNote.setOnClickListener(cardClick);
    }

    private class CardClick  implements View.OnClickListener  {
        @Override
        public void onClick(View view) {
            if(view.getId() == R.id.mcHerbs) {
                Toast.makeText(MainActivity.this, "Herb clicked", Toast.LENGTH_SHORT).show();
            } else if(view.getId() == R.id.mcFruits) {
                Toast.makeText(MainActivity.this, "Fruit clicked", Toast.LENGTH_SHORT).show();
            } else if(view.getId() == R.id.mcVegetables) {
                Toast.makeText(MainActivity.this, "Vegetable clicked", Toast.LENGTH_SHORT).show();
            } else if(view.getId() == R.id.mcNotes)  {
                Intent intent = new Intent(MainActivity.this, NoteActivity.class);
                startActivity(intent);
            }
        }
    }
}