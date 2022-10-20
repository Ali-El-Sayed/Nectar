package com.example.finalprojectnectar.screens.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.database.FavDb;
import com.example.finalprojectnectar.data.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FavouriteCustomAdapter extends RecyclerView.Adapter<FavouriteCustomAdapter.DataHolder> {


    List<FavDb> mProducts = new ArrayList<>();

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        holder.txtPrice.setText("$"+mProducts.get(position).getPrice());
        holder.txtTitle.setText("$" + mProducts.get(position).getTitle());
        Log.d("ImgURI", "onBindViewHolder: "+mProducts.get(position).getImage());
        Picasso.get().load(mProducts.get(position).getImage()).into(holder.imgProduct);
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setProducts(List<FavDb> products) {
        mProducts = products;
        notifyDataSetChanged();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtPrice;
        ImageView imgProduct;

        public DataHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtProductTitle);
            txtPrice = itemView.findViewById(R.id.txtProductPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}

