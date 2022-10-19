package com.example.finalprojectnectar.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.reprository.Repository;

import java.util.ArrayList;

public class CategoriesViewModel extends ViewModel {
    private final Repository mRepository = new Repository();

    public void getCategoriesFromRepo() {
        mRepository.getCategoriesFromServer();
    }

    public LiveData<ArrayList<String>> getCategories() {
        return mRepository.getCategories();
    }
}
