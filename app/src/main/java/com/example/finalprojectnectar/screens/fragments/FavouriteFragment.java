package com.example.finalprojectnectar.screens.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.database.FavDb;
import com.example.finalprojectnectar.data.database.FavouriteDb;
import com.example.finalprojectnectar.screens.adapter.FavouriteCustomAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteFragment extends Fragment {


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
        favouriteCustomAdapter.setProducts(new ArrayList<>());
        RecyclerView recyclerView = view.findViewById(R.id.favList);
        progressBar.setVisibility(View.VISIBLE)
        ;
        FavouriteDb.getInstance(getActivity()).Dao().getAllProducts().observe(getActivity(), new Observer<List<FavDb>>() {
            @Override
            public void onChanged(List<FavDb> favDbs) {
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