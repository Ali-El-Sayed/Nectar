package com.example.finalprojectnectar.data.viewmodel;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectnectar.data.database.CartDbModel;
import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.reprository.Repository;

import java.util.List;

public class CartViewModel extends ViewModel {
    private final Repository mRepository = new Repository();

    public void getCartProductsFromDatabase(FragmentActivity context) {
        mRepository.getCartProductsFromDb(context);
    }


    public LiveData<List<CartDbModel>> getProducts() {
        return mRepository.getCartProducts();
    }

}
