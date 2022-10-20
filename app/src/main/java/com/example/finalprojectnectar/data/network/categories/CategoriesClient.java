package com.example.finalprojectnectar.data.network.categories;

import com.example.finalprojectnectar.data.model.Product;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoriesClient {
    public static final String BASE_URL = "https://fakestoreapi.com/products/";
    private final ICategories mICategories;
    private static CategoriesClient INSTANCE;

    private CategoriesClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient()).build();
        mICategories = retrofit.create(ICategories.class);
    }

    //Create Instance
    public static CategoriesClient getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CategoriesClient();
        return INSTANCE;
    }

    //Get All Products
    public Call<ArrayList<String>> getCategories() {
        return mICategories.getCategories();
    }

    public Call<ArrayList<Product>> getProductsByCategory(String category) {
        return mICategories.getProductsByCategory(category);
    }

}
