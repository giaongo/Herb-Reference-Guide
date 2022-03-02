package fi.metropolia.herbreferenceguide.camera;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.*;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;

/**
 * Defines RecyclerView adapter that simply creates ViewHolder and binds data to that view holder
 * @author Giao Ngo
 * @version 1.0
 * @since 2022-03-01
 */
public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private final ArrayList<CameraImage> imageLists;
    private final Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    /**
     * Adapter constructor to initialise the instance of ImageAdapter
     * @param imageLists ArrayList
     * @param context Context
     * @param recyclerViewInterface RecyclerViewInterface
     */
    public ImageAdapter(ArrayList<CameraImage> imageLists, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.imageLists = imageLists;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    /**
     * Create new view
     * @param parent ViewGroup
     * @param viewType int
     * @return ViewHolder
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     *  Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_recycler_layout, parent, false);
        return new ViewHolder(view, recyclerViewInterface);
    }

    /**
     * Sets the contents of a view
     * @param holder ViewHolder
     * @param position the element position from the dataset
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        holder.capturedImage.setImageBitmap(imageLists.get(position).getBitmap());
    }

    /**
     * Gets size of the dataset
     * @return int size of dataset
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>
     */
    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    /**
     * Defines ViewHolder class that gives a reference to the views
     * Code reference:
     * @see <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview">
     * Android Studio: Create dynamic lists with RecyclerView</a>.
     * Event listener: <a href="https://www.youtube.com/watch?v=7GPUpvcU1FE&ab_channel=PracticalCoding">
     * RecyclerView Item Click | Best Practice Way</a>
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView capturedImage;
        private final FloatingActionButton checkedBtn;
        private boolean isCheckedBtn = false;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            this.capturedImage = itemView.findViewById(R.id.capturedImage);
            checkedBtn = itemView.findViewById(R.id.btnChecked);
            checkedBtn.setOnClickListener(view -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    //Toggle the floating action btn icon based on image click
                    if (!isCheckedBtn) {
                        checkedBtn.setImageResource(R.drawable.ic_check);
                        isCheckedBtn = true;
                        recyclerViewInterface.onItemClick(position);
                    } else {
                        checkedBtn.setImageResource(0);
                        isCheckedBtn = false;
                    }
                }
            });
        }
    }
}


