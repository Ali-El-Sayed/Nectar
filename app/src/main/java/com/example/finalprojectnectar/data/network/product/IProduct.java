package com.example.finalprojectnectar.data.network.product;

import androidx.lifecycle.LiveData;

import com.example.finalprojectnectar.data.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IProduct {


    @GET("Products")
    Call<ArrayList<Product>> getProducts();



}
