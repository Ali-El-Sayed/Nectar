package com.example.finalprojectnectar.screens.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.data.viewmodel.CategoriesViewModel;
import com.example.finalprojectnectar.data.viewmodel.ProductViewModel;
import com.example.finalprojectnectar.screens.adapter.ExploreCustomAdapter;
import com.example.finalprojectnectar.screens.adapter.IOnCategoryClick;

import java.util.ArrayList;


public class ExploreFragment extends Fragment implements IOnCategoryClick {
    private ProgressBar mProgressBar;
    private RecyclerView recyclerView;
    private final ExploreCustomAdapter exploreCustomAdapter = new ExploreCustomAdapter(this);
    private ArrayList<String> result = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        mProgressBar = view.findViewById(R.id.progressBar);
        SearchView searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.categoryList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        CategoriesViewModel categoriesViewModel = new ViewModelProvider(this).get(CategoriesViewModel.class);
        categoriesViewModel.getCategoriesFromRepo();
        categoriesViewModel.getCategories().observe(getActivity(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> categories) {
                result = categories;
                exploreCustomAdapter.setCategories(categories);
                recyclerView.setAdapter(exploreCustomAdapter);
                mProgressBar.setVisibility(View.GONE);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            ArrayList<String> newResult = new ArrayList<>();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newResult.clear();
                for (String p : result) {
                    if (p.toLowerCase().trim().contains(newText.toLowerCase().trim()))
                        newResult.add(p);
                    exploreCustomAdapter.setCategories(newResult);
                    recyclerView.setAdapter(exploreCustomAdapter);
//                    Log.d("SearchView", "onQueryTextChange: " + newResult.get(0));
                }
                return true;
            }

        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onCategoryClick(String name) {
        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
    }
}