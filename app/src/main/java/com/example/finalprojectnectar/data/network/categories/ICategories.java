package com.example.finalprojectnectar.data.network.categories;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ICategories {

    @GET("categories")
    Call<ArrayList<String>> getCategories();

}
