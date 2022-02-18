package fi.metropolia.herbreferenceguide.Plant;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.ItemDisplayActivity;
import fi.metropolia.herbreferenceguide.MainActivity;
import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.AppDatabase;
import fi.metropolia.herbreferenceguide.database.Plant;

public class PlantActivity extends MainActivity implements RecyclerViewInterface {
    ArrayList<Plant> plantList;
    protected static final String PLANT_ID = "Plant Id";
    protected static final String PLANT_NAME = "Plant Name";
    protected static final String PLANT_TYPE = "Plant Type";
    protected static final String PLANT_NUTRITION = "Plant Nutrition";
    protected static final String PLANT_HEALTHBENEFIT = "Plant Health Benefit";
    protected static final String PLANT_FOODSUGGESTION = "Plant Food Suggestion";
    protected static final String PLANT_IMGSRC = "Plant Image Source";

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
        plantList  = (ArrayList<Plant>) database.plantDao().getPlantByType(plantType);
    }

    private void initRecyclerView() {
        RecyclerView myVeggieRecyclerView = findViewById(R.id.veggie);
        myVeggieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        PlantRecyclerViewAdapter plantRecyclerViewAdapter = new PlantRecyclerViewAdapter(plantList,this);
        myVeggieRecyclerView.setAdapter(plantRecyclerViewAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(PlantActivity.this, ItemDisplayActivity.class);
        int plantId = plantList.get(position).getPlantId();
        String plantName = plantList.get(position).getPlantName();
        String plantType = plantList.get(position).getPlantType();
        String plantNutrition = plantList.get(position).getPlantNutrition();
        String plantHealthBenefit = plantList.get(position).getPlantHealthBenefit();
        String plantFoodSuggestion = plantList.get(position).getPlantFoodSuggestion();
        String plantImgSrc = plantList.get(position).getPlantImgSrc();

        intent.putExtra(PLANT_ID,plantId);
        intent.putExtra(PLANT_NAME, plantName);
        intent.putExtra(PLANT_TYPE, plantType);
        intent.putExtra(PLANT_HEALTHBENEFIT,plantHealthBenefit);
        intent.putExtra(PLANT_NUTRITION, plantNutrition);
        intent.putExtra(PLANT_FOODSUGGESTION, plantFoodSuggestion);
        intent.putExtra(PLANT_IMGSRC, plantImgSrc);

        startActivity(intent);

    }

}
