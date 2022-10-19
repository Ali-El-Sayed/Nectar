package com.example.finalprojectnectar.data.viewmodel;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.data.network.product.ProductClient;
import com.example.finalprojectnectar.reprository.Repository;
import com.example.finalprojectnectar.screens.ui.Home;

import java.util.ArrayList;

public class ProductViewModel extends ViewModel {

    private final Repository mRepository = new Repository();

    public void getProductsFromRepo() {
        mRepository.getProductsFromServer();
    }

    public LiveData<ArrayList<Product>> getProducts() {
        return mRepository.getProducts();
    }
}
