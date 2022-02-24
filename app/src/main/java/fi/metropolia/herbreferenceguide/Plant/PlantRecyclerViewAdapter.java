package fi.metropolia.herbreferenceguide.Plant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.Plant;

public class PlantRecyclerViewAdapter extends RecyclerView.Adapter<PlantRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<Plant> plantList;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;
    protected Plant plantItem;

    public PlantRecyclerViewAdapter(ArrayList<Plant> plantList, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.plantList = plantList;
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
        String plantName = plantList.get(position).getPlantName();
        holder.veggie_layout.setText(res.getString(R.string.item_name, plantName));
        //String plantImgSrc = plant.get(position);
        try{
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(plantList.get(position).getPlantImgSrc());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.plant_thumbnail.setImageBitmap(bitmap);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        return plantList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView veggie_layout;
        private final ImageView plant_thumbnail;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            veggie_layout = itemView.findViewById(R.id.veggie_layout);
            plant_thumbnail = itemView.findViewById(R.id.plant_thumbnail);

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
