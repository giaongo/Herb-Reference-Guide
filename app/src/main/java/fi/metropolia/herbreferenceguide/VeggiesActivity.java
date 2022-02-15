package fi.metropolia.herbreferenceguide;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VeggiesActivity extends MainActivity{
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
