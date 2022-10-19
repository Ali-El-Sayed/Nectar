package com.example.finalprojectnectar.data.network.product;

import androidx.lifecycle.LiveData;

import com.example.finalprojectnectar.data.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductClient {
    public static final String BASE_URL = "https://fakestoreapi.com/";
    private final IProduct mIProduct;
    private static ProductClient INSTANCE;

    private ProductClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        mIProduct = retrofit.create(IProduct.class);
    }

    //Create Instance
    public static ProductClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ProductClient();
        return INSTANCE;
    }

    //Get All Products
    public Call<ArrayList<Product>> getProducts() {
        return mIProduct.getProducts();
    }
}
