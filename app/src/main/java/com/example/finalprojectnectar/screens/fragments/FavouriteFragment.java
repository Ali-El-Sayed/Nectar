package com.example.finalprojectnectar.screens.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.database.CartDatabase;
import com.example.finalprojectnectar.data.database.CartDbModel;
import com.example.finalprojectnectar.data.database.FavDb;
import com.example.finalprojectnectar.data.database.FavouriteDb;
import com.example.finalprojectnectar.data.viewmodel.FavViewModel;
import com.example.finalprojectnectar.screens.adapter.FavouriteCustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {
    List<FavDb> favList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        FavouriteCustomAdapter favouriteCustomAdapter = new FavouriteCustomAdapter();
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        Button btnAddToCart = view.findViewById(R.id.btnAddToCart);
        FavViewModel favViewModel = new ViewModelProvider(getActivity()).get(FavViewModel.class);
        favViewModel.getFavProductsFromDb(getActivity());

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (FavDb f : favList) {
                            CartDatabase.getInstance(getActivity()).Dao()
                                    .insert(new CartDbModel(null, f.getTitle(), f.getPrice(), f.getCount(), f.getImage()));
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Added To Cart", Toast.LENGTH_SHORT).show();
                            }
                        });
                        FavouriteDb.getInstance(getActivity()).Dao().deleteAllProducts();

                    }
                }).start();
            }
        });
        favouriteCustomAdapter.setProducts(new ArrayList<>());
        RecyclerView recyclerView = view.findViewById(R.id.favList);
        progressBar.setVisibility(View.VISIBLE);
//     
        favViewModel.getProducts().observe(getActivity(), new Observer<List<FavDb>>() {
            @Override
            public void onChanged(List<FavDb> favDbs) {
                favList = favDbs;
                favouriteCustomAdapter.setProducts(favDbs);
                recyclerView.setAdapter(favouriteCustomAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                progressBar.setVisibility(View.GONE);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }


}