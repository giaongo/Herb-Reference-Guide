package fi.metropolia.herbreferenceguide.Plant;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.MainActivity;
import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Plant;

public class PlantActivity extends MainActivity {
    ArrayList<Plant> plant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plant_list);
        loadPlantData();
        initRecyclerView();
    }

    private void loadPlantData() {
        Intent intent = getIntent();
        String plantType = intent.getStringExtra(TYPE);
        setTitle(plantType);
        AppDatabase database = AppDatabase.getINSTANCE(PlantActivity.this);
        plant  = (ArrayList<Plant>) database.plantDao().getPlantByType(plantType);
    }

    private void initRecyclerView() {
        RecyclerView myVeggieRecyclerView = findViewById(R.id.veggie);
        myVeggieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlantRecyclerViewAdapter plantRecyclerViewAdapter = new PlantRecyclerViewAdapter(plant,this);
        myVeggieRecyclerView.setAdapter(plantRecyclerViewAdapter);
    }
}
