package fi.metropolia.herbreferenceguide.Veggie;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fi.metropolia.herbreferenceguide.R;

public class VeggieRecyclerViewAdapter extends RecyclerView.Adapter<VeggieRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Veggies> Veggies;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.veggie_recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.veggie_layout.setText(Veggies.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return Veggies.size();
    }

    public VeggieRecyclerViewAdapter(ArrayList<Veggies> veggies){
        Veggies = veggies;
        //Notify whenever the data set change so that it can refresh the recycler view
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView veggie_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            veggie_layout = itemView.findViewById(R.id.veggie_layout);
        }

    }

}
