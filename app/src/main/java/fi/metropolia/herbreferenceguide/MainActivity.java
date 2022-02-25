package fi.metropolia.herbreferenceguide;

import static fi.metropolia.herbreferenceguide.R.id.mcHerbs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import fi.metropolia.herbreferenceguide.Plant.PlantActivity;
import fi.metropolia.herbreferenceguide.camera.CameraActivity;
import fi.metropolia.herbreferenceguide.note.NoteActivity;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    String[] typeChosen = {"herb", "fruit",  "veggie"};
    public static final String TYPE = "plantType";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initCardClick();
    }

    private void initCardClick() {
        CardClick cardClick = new CardClick();
        (findViewById(mcHerbs)).setOnClickListener(cardClick);
        (findViewById(R.id.mcFruits)).setOnClickListener(cardClick);
        (findViewById(R.id.mcVegetables)).setOnClickListener(cardClick);
        (findViewById(R.id.mcNotes)).setOnClickListener(cardClick);
    }

    private class CardClick  implements View.OnClickListener  {
        @Override
        public void onClick(View view) {
            if(view.getId() != R.id.mcNotes) {
                intent = new Intent(MainActivity.this, PlantActivity.class);
                if(view.getId() == mcHerbs) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.camera) {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}