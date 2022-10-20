package com.example.finalprojectnectar.data.network.categories;

import com.example.finalprojectnectar.data.model.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ICategories {

    @GET("categories")
    Call<ArrayList<String>> getCategories();


//    https://fakestoreapi.com/products//jewelery

    @GET("category/")
    Call<ArrayList<Product>> getProductsByCategory(@Query(value = "category") String category);


}
