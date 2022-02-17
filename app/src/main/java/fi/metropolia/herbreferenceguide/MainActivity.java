package fi.metropolia.herbreferenceguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.card.MaterialCardView;

import fi.metropolia.herbreferenceguide.Plant.PlantActivity;
import fi.metropolia.herbreferenceguide.note.NoteActivity;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    String[] typeChosen = {"herb", "fruit",  "veggie"};
    public static final String TYPE = "plantType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardClick cardClick = new CardClick();
        MaterialCardView mcHerbs = findViewById(R.id.mcHerbs);
        MaterialCardView mcFruits = findViewById(R.id.mcFruits);
        MaterialCardView mcVegetables =  findViewById(R.id.mcVegetables);
        MaterialCardView mcNote = findViewById(R.id.mcNotes);
        mcHerbs.setOnClickListener(cardClick);
        mcFruits.setOnClickListener(cardClick);
        mcVegetables.setOnClickListener(cardClick);
        mcNote.setOnClickListener(cardClick);
    }

    private class CardClick  implements View.OnClickListener  {
        @Override
        public void onClick(View view) {
            if(view.getId() != R.id.mcNotes) {
                intent = new Intent(MainActivity.this, PlantActivity.class);
                if(view.getId() == R.id.mcHerbs) {
                    intent.putExtra(TYPE,typeChosen[0]);
                } else if(view.getId() == R.id.mcFruits) {
                    intent.putExtra(TYPE,typeChosen[1]);
                } else {
                    intent.putExtra(TYPE,typeChosen[2]);
                }
            } else {
                intent = new Intent(MainActivity.this, NoteActivity.class);
            }
            startActivity(intent);
        }
    }
}