package com.example.finalprojectnectar.reprository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.data.network.product.ProductClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {


    private final MutableLiveData<ArrayList<Product>> products = new MutableLiveData<>();

    public void getProductsFromServer() {
        ProductClient.getInstance().getProducts().enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body());
                    Log.d("HomeScreenData", "onResponse: " + response.body().get(0).getTitle());
                }
                Log.d("HomeScreenData", "onResponse: " + response.body().get(0).getTitle());

            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.d("HomeScreenData", "onResponse: " + t.getMessage());

            }
        });
    }

    public MutableLiveData<ArrayList<Product>> getProducts() {
        return products;
    }
}
