package fi.metropolia.herbreferenceguide.Herb;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.R;

public class HerbRecyclerViewAdapter extends RecyclerView.Adapter<HerbRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Herbs> Herbs;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.herb_recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.herb_layout.setText(Herbs.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Herbs.size();
    }



    public HerbRecyclerViewAdapter(ArrayList<Herbs> herbs) {
        Herbs = herbs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView herb_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            herb_layout = itemView.findViewById(R.id.herb_layout);
        }
    }

}
