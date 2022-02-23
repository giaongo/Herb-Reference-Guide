package fi.metropolia.herbreferenceguide.Plant;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.MainActivity;
import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Plant;

public class PlantActivity extends AppCompatActivity implements RecyclerViewInterface {
    private ArrayList<String> plantListName;
    private AppDatabase database;
    public static final String PLANT_ITEM = "plant item";
    private Plant plantImg;
    private ImageView plant_thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list);
        database = AppDatabase.getINSTANCE(PlantActivity.this);
        loadPlantData();
        initRecyclerView();
    }

    private void loadPlantData() {
        Intent intent = getIntent();
        String plantType = intent.getStringExtra(MainActivity.TYPE);
        setTitle(plantType.toUpperCase());
        plantListName = (ArrayList<String>) database.plantDao().getPlantByType(plantType);
    }

    private void initRecyclerView() {
        RecyclerView myVeggieRecyclerView = findViewById(R.id.veggie);
        myVeggieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlantRecyclerViewAdapter plantRecyclerViewAdapter = new PlantRecyclerViewAdapter(
                plantListName,
                this,
                this);
        myVeggieRecyclerView.setAdapter(plantRecyclerViewAdapter);
    }

    @Override
    public void onItemClick(int position) {
        String plantName = plantListName.get(position);
        Plant plantItem = database.plantDao().getPlantByName(plantName);
        Intent intent = new Intent(PlantActivity.this, ItemDisplayActivity.class);
        intent.putExtra(PLANT_ITEM, plantItem);
        startActivity(intent);
    }
}
