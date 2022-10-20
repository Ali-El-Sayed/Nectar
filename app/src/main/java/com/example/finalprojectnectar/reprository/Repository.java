package com.example.finalprojectnectar.reprository;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.finalprojectnectar.data.database.CartDatabase;
import com.example.finalprojectnectar.data.database.CartDbModel;
import com.example.finalprojectnectar.data.database.FavDb;
import com.example.finalprojectnectar.data.database.FavouriteDb;
import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.data.network.categories.CategoriesClient;
import com.example.finalprojectnectar.data.network.product.ProductClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {


    private final MutableLiveData<ArrayList<Product>> products = new MutableLiveData<>();


    private final MutableLiveData<ArrayList<String>> categories = new MutableLiveData<>();

    private final MutableLiveData<List<CartDbModel>> cartProducts = new MutableLiveData<>();

    private final MutableLiveData<List<FavDb>> favProducts = new MutableLiveData<>();


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

    public void getCategoriesFromServer() {
        CategoriesClient.getInstance().getCategories().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categories.setValue(response.body());
                    Log.d("ExploreScreenData", "onResponse: " + response.body());
                }
                Log.d("ExploreScreenData", "onResponse: " + response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.d("ExploreScreenData", "onResponse: " + t.getMessage());

            }
        });
    }

    public void getCartProductsFromDb(FragmentActivity context) {
        CartDatabase.getInstance(context).Dao().getAllProducts().observe(context, new Observer<List<CartDbModel>>() {
            @Override
            public void onChanged(List<CartDbModel> cartDbModels) {
                cartProducts.setValue(cartDbModels);
            }
        });
    }

    public void getFavProductFromDb(FragmentActivity context) {
        FavouriteDb.getInstance(context).Dao().getAllProducts().observe(context, new Observer<List<FavDb>>() {
            @Override
            public void onChanged(List<FavDb> favDbs) {
                favProducts.setValue(favDbs);
            }
        });
    }

    public void getProductsByCategoryFromServer(String category) {
        //men's%20clothing
        CategoriesClient.getInstance().getProductsByCategory(category).enqueue(new Callback<ArrayList<Product>>() {
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

    public MutableLiveData<ArrayList<String>> getCategories() {
        return categories;
    }

    public MutableLiveData<ArrayList<Product>> getProductByCategory() {
        return products;
    }

    public MutableLiveData<List<CartDbModel>> getCartProducts() {
        return cartProducts;
    }

    public MutableLiveData<List<FavDb>> getFavProducts() {
        return favProducts;
    }

}
