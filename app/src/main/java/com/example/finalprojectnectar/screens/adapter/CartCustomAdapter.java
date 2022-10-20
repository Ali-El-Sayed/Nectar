package com.example.finalprojectnectar.screens.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.database.CartDatabase;
import com.example.finalprojectnectar.data.database.CartDbModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartCustomAdapter extends RecyclerView.Adapter<CartCustomAdapter.DataHolder> {
    List<CartDbModel> cartProducts = new ArrayList<>();

    @NonNull
    @Override
    public DataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DataHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DataHolder holder, int position) {
        holder.txtCount.setText("" + cartProducts.get(position).getCount());
        holder.txtPrice.setText("$" + cartProducts.get(position).getPrice());
        holder.txtTitle.setText(cartProducts.get(position).getTitle());
        holder.originalCount = cartProducts.get(position).getCount();
        Picasso.get().load(cartProducts.get(position).getImage()).into(holder.imgProduct);
        holder.btnRemoveFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CartDatabase.getInstance(v.getContext()).Dao().deleteById(cartProducts.get(position).getId());
                    }
                }).start();
            }
        });
        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.onDecrease(v);
            }
        });
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.onIncrease(v);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public void setCartProducts(List<CartDbModel> cartProducts) {
        this.cartProducts = cartProducts;
    }


    public static class DataHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtPrice, txtCount;
        ImageButton btnIncrease, btnDecrease;
        ImageView imgProduct;
        int originalCount;
        ImageView btnRemoveFromCart;

        public DataHolder(@NonNull View itemView) {
            super(itemView);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            txtTitle = itemView.findViewById(R.id.txtProductTitle);
            txtPrice = itemView.findViewById(R.id.txtProductPrice);
            txtCount = itemView.findViewById(R.id.txtCount);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            btnRemoveFromCart = itemView.findViewById(R.id.btnRemoveCartElement);
        }

        public void onDecrease(View view) {
            int current = Integer.parseInt(txtCount.getText().toString());
            if (current != 0)
                txtCount.setText(--current + "");

        }

        public void onIncrease(View view) {
            int current = Integer.parseInt(txtCount.getText().toString());
            if (current < originalCount)
                txtCount.setText(++current + "");
        }


    }
}
