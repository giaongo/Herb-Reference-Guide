package fi.metropolia.herbreferenceguide.Veggie;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.nio.channels.InterruptedByTimeoutException;
import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.ItemDisplayActivity;
import fi.metropolia.herbreferenceguide.MainActivity;
import fi.metropolia.herbreferenceguide.R;

public class VeggiesActivity extends MainActivity {
    private RecyclerView myVeggieRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veggie_list);

        myVeggieRecyclerView = findViewById(R.id.veggie);

        ArrayList<Veggies> Veggies = new ArrayList<>();
        Veggies.add(new Veggies("Bell Pepper"));
        Veggies.add(new Veggies("Cabbage"));
        Veggies.add(new Veggies("Carrot"));
        Veggies.add(new Veggies("Lettuce"));


        myVeggieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VeggieRecyclerViewAdapter veggieRecyclerViewAdapter = new VeggieRecyclerViewAdapter(Veggies);
        myVeggieRecyclerView.setAdapter(veggieRecyclerViewAdapter);



    }

}
