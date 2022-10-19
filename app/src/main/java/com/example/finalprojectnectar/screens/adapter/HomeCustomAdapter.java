package com.example.finalprojectnectar.screens.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeCustomAdapter extends RecyclerView.Adapter<HomeCustomAdapter.DataHolder> {


    private ArrayList<Product> products = new ArrayList<>();
    private IOnProductClick mIOnClick;

    public HomeCustomAdapter(IOnProductClick IOnClick) {
        this.mIOnClick = IOnClick;
    }

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_card, parent, false));
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        holder.txtProductTitle.setText(products.get(position).getTitle());
        holder.txtProductPrice.setText("$" + products.get(position).getPrice());
        Picasso.get().load(products.get(position).getImage()).into(holder.imgProduct);
        holder.imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIOnClick.onProductClick(products.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public static class DataHolder extends RecyclerView.ViewHolder {
        private final ImageView imgProduct;
        private final TextView txtProductTitle;
        private final TextView txtProductPrice;
        private final ImageButton btnAddToCart;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            txtProductTitle = itemView.findViewById(R.id.txtProductTitle);
            txtProductPrice = itemView.findViewById(R.id.txtProductPrice);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
