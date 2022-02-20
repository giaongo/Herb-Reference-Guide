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
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;

public class PlantRecyclerViewAdapter extends RecyclerView.Adapter<PlantRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> plant;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public PlantRecyclerViewAdapter(ArrayList<String> veggies, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.plant = veggies;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plant_recycler_layout, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Resources res = context.getResources();
        String plantName = plant.get(position);
        holder.veggie_layout.setText(res.getString(R.string.item_name, plantName));
    }

    @Override
    public int getItemCount() {
        return plant.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView veggie_layout;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            veggie_layout = itemView.findViewById(R.id.veggie_layout);
            itemView.setOnClickListener(view -> {
                if (recyclerViewInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(position);
                    }
                }
            });
        }
    }

}
