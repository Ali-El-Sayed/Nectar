package com.example.finalprojectnectar.screens.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectnectar.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ExploreCustomAdapter extends RecyclerView.Adapter<ExploreCustomAdapter.DataHolder> {

    ArrayList<String> categories = new ArrayList<>();
    HashMap<String, Integer> images = new HashMap<>();
    IOnCategoryClick mIOnCategoryClick;

    public ExploreCustomAdapter(IOnCategoryClick IOnCategoryClick) {
        mIOnCategoryClick = IOnCategoryClick;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        images.put("electronics", R.drawable.electronics);
        images.put("jewelery", R.drawable.jewelery);
        images.put("men's clothing", R.drawable.men_clothing);
        images.put("women's clothing", R.drawable.women_clothing);
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.explore_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        String title = categories.get(position);
        holder.txtCategories.setText(title.toUpperCase());
        holder.imgCategories.setImageResource(images.get(title));
        holder.imgCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIOnCategoryClick.onCategoryClick(title);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }


    public static class DataHolder extends RecyclerView.ViewHolder {
        ImageView imgCategories;
        TextView txtCategories;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            imgCategories = itemView.findViewById(R.id.imgCategory);
            txtCategories = itemView.findViewById(R.id.txtCategoryTitle);
        }
    }
}
