package fi.metropolia.herbreferenceguide.Fruit;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.MainActivity;
import fi.metropolia.herbreferenceguide.R;


public class FruitsActivity extends MainActivity {
    private RecyclerView myFruitRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruit_list);

        myFruitRecyclerView = findViewById(R.id.fruit);

        ArrayList<Fruits> Fruits = new ArrayList<>();
        Fruits.add(new Fruits("Mango"));
        Fruits.add(new Fruits("Banana"));
        Fruits.add(new Fruits("Strawberry"));
        Fruits.add(new Fruits("Pineapple"));


        myFruitRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FruitRecyclerViewAdapter fruitRecyclerViewAdapter = new FruitRecyclerViewAdapter(Fruits);
        myFruitRecyclerView.setAdapter(fruitRecyclerViewAdapter);

    }

}