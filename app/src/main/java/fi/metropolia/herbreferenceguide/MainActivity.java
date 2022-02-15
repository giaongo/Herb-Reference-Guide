package fi.metropolia.herbreferenceguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView fruitCardView = findViewById(R.id.mcFruits);
        fruitCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FruitsActivity.class);
            view.getContext().startActivity(intent);
        });


        CardView herbCardView = findViewById(R.id.mcHerbs);
        herbCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, HerbsActivity.class);
            view.getContext().startActivity(intent);
        });

        CardView veggieCardView = findViewById(R.id.mcVegetables);
        veggieCardView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, VeggiesActivity.class);
            view.getContext().startActivity(intent);
        });

    }
}