package fi.metropolia.herbreferenceguide;

import android.annotation.SuppressLint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FruitRecyclerViewAdapter extends RecyclerView.Adapter<FruitRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Fruits> Fruits;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.fruit_layout.setText(Fruits.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Fruits.size();
    }

    public FruitRecyclerViewAdapter(ArrayList<Fruits> fruits) {
        Fruits = fruits;
        //Notify whenever the data set change so that it can refresh the recycler view
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView fruit_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fruit_layout = itemView.findViewById(R.id.fruit_layout);
        }

    }

}
