package fi.metropolia.herbreferenceguide;


import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HerbsActivity extends MainActivity {
    private RecyclerView myHerbRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herb_list);

        myHerbRecyclerView = findViewById(R.id.herb);

        ArrayList<Herbs> Herbs = new ArrayList<>();
        Herbs.add(new Herbs("Basil"));
        Herbs.add(new Herbs("Rosemary"));
        Herbs.add(new Herbs("Oregano"));
        Herbs.add(new Herbs("Mint"));


        myHerbRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        HerbRecyclerViewAdapter herbRecyclerViewAdapter = new HerbRecyclerViewAdapter(Herbs);
        myHerbRecyclerView.setAdapter(herbRecyclerViewAdapter);

    }

}