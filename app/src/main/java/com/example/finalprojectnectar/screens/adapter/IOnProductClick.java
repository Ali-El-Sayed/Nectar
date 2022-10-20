package com.example.finalprojectnectar.screens.adapter;

import android.view.View;

import com.example.finalprojectnectar.data.model.Product;

public interface IOnProductClick {
    void onProductClick(Product product);

    void onAddToCartClick(Product product);
}
