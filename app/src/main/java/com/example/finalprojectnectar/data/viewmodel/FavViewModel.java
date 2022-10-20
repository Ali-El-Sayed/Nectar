package com.example.finalprojectnectar.data.viewmodel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectnectar.data.database.FavDb;
import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.reprository.Repository;

import java.util.List;

public class FavViewModel extends ViewModel {

    private final Repository mRepository = new Repository();

    public void getFavProductsFromDb(FragmentActivity context) {
        mRepository.getFavProductFromDb(context);
    }

    public LiveData<List<FavDb>> getProducts() {
        return mRepository.getFavProducts();
    }
}
