package fi.metropolia.herbreferenceguide.Plant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.database.Plant;

public class PlantRecyclerViewAdapter extends RecyclerView.Adapter<PlantRecyclerViewAdapter.ViewHolder>{

    private final ArrayList<fi.metropolia.herbreferenceguide.database.Plant> plant;
    Context context;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plant_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Resources res =  context.getResources();
        String  plantName = plant.get(position).getPlantName();
        holder.veggie_layout.setText(res.getString(R.string.item_name,plantName));
    }

    @Override
    public int getItemCount() {
        return plant.size();
    }

    public PlantRecyclerViewAdapter(ArrayList<Plant> veggies, Context context){
        this.plant = veggies;
        this.context = context;
        //Notify whenever the data set change so that it can refresh the recycler view
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView veggie_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            veggie_layout = itemView.findViewById(R.id.veggie_layout);
        }

    }

}
