package fi.metropolia.herbreferenceguide.Plant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.database.Plant;
import fi.metropolia.herbreferenceguide.note.NoteActivity;

public class ItemDisplayActivity extends AppCompatActivity {
    private Plant plantItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_display);
        loadPlantData();

        FloatingActionButton fab = findViewById(R.id.add_icon);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(ItemDisplayActivity.this, NoteActivity.class);
            startActivity(intent);
        });
    }

    private void loadPlantData() {
        plantItem = getIntent().getParcelableExtra(PlantActivity.PLANT_ITEM);
        ((TextView) findViewById(R.id.txtPlantTitle))
                .setText(getString(R.string.plantItemTitle, plantItem.getPlantName()));

        ((TextView) findViewById(R.id.benefitsDescriptions))
                .setText(getString(R.string.benefitsDescription, plantItem.getPlantHealthBenefit()));

        ((TextView) findViewById(R.id.foodDescription))
                .setText(getString(R.string.foodDescription, plantItem.getPlantFoodSuggestion()));

        ((TextView) findViewById(R.id.nutritionDescription))
                .setText(getString(R.string.nutritionDescription, plantItem.getPlantNutrition()));
        loadImage();
    }

    private void loadImage() {
        ImageView plantImg = findViewById(R.id.imagePlant);
        AssetManager assetManager = this.getAssets();
        InputStream is = null;
        try {
            is = assetManager.open(plantItem.getPlantImgSrc());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bitmap = BitmapFactory.decodeStream(is);
        plantImg.setImageBitmap(bitmap);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return false;
    }
}