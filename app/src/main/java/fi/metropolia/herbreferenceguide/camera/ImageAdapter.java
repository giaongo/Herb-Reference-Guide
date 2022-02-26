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

import fi.metropolia.herbreferenceguide.R;
import fi.metropolia.herbreferenceguide.RecyclerViewInterface;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<CameraImage> imageLists;
    private Context context;
    private final RecyclerViewInterface recyclerViewInterface;

    public ImageAdapter(ArrayList<CameraImage> imageLists, Context context, RecyclerViewInterface recyclerViewInterface) {
        this.imageLists = imageLists;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_recycler_layout, parent, false);
        return new ViewHolder(view, recyclerViewInterface, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        holder.capturedImage.setImageBitmap(imageLists.get(position).getBitmap());
    }

    @Override
    public int getItemCount() {
        return imageLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView capturedImage;
        private FloatingActionButton checkedBtn;
        private boolean isCheckedBtn = false;

        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface, Context context) {
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


