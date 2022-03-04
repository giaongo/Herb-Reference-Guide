package fi.metropolia.herbreferenceguide.plant;

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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import fi.metropolia.herbreferenceguide.*;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;
import fi.metropolia.herbreferenceguide.database.Plant;

/**
 * This class defines RecyclerView Adapter that creates a view holder and binds data to view holder
 *
 * @author Tai Nguyen
 * @version 1.0
 * @since 2022-02-21
 * @see <a href="https://www.youtube.com/watch?v=18VcnYN5_LM&t=1s&ab_channel=Stevdza-San">
 * Recycler View: Android Studio tutorial part 1/2/3</a>
 */
public class PlantRecyclerViewAdapter extends RecyclerView.Adapter<PlantRecyclerViewAdapter.ViewHolder> {
    private final ArrayList<Plant> plantList;
    Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    /**
     * Adapter Constructor to initialize instance of this recyclerview
     * @param plantList ArrayList
     * @param context Context
     * @param recyclerViewInterface RecyclerViewInterface
     */
    public PlantRecyclerViewAdapter(ArrayList<Plant> plantList, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.plantList = plantList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
        notifyDataSetChanged();
    }

    /**
     * Creates new view
     * @param parent ViewGroup
     * @param viewType int
     * @return ViewHolder new view
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plant_recycler_layout, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    /**
     * Set content of new recycler view by fetching data(texts and photos) from database
     * @param holder ViewHolder
     * @param position int
     * @see <a href="https://developer.android.com/reference/android/graphics/Bitmap">
     *      * Android Studio: Bitmap</a>
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Resources res = context.getResources();
        String plantName = plantList.get(position).getPlantName();
        holder.veggie_layout.setText(res.getString(R.string.item_name, plantName));
        try{
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(plantList.get(position).getPlantImgSrc());
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            holder.plant_thumbnail.setImageBitmap(bitmap);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Gets size of the dataset
     * @return int size of dataset
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @Override
    public int getItemCount() {
        return plantList.size();
    }


    /**
     * Defines ViewHolder class that gives reference to the view
     * Code reference:
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>.
     * Event listener: <a href="https://www.youtube.com/watch?v=7GPUpvcU1FE&ab_channel=PracticalCoding">
     * RecyclerView Item Click | Best Practice Way</a>
     */
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
