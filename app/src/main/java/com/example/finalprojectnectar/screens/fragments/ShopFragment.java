package com.example.finalprojectnectar.screens.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.data.viewmodel.ProductViewModel;
import com.example.finalprojectnectar.screens.adapter.HomeCustomAdapter;
import com.example.finalprojectnectar.screens.adapter.IOnProductClick;
import com.example.finalprojectnectar.screens.ui.ProductDetails;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements IOnProductClick {
    private ProductViewModel mProductViewModel;
    private HomeCustomAdapter mHomeCustomAdapter;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private SearchView mSearchView;
    private ArrayList<Product> result = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        // Inflate the layout for this fragment
        mSearchView = view.findViewById(R.id.searchView);
        mRecyclerView = view.findViewById(R.id.productList);
        mProgressBar = view.findViewById(R.id.progressBar);
        mHomeCustomAdapter = new HomeCustomAdapter(this);

        mProductViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        mProductViewModel.getProductsFromRepo();
        mProductViewModel.getProducts().observe(getActivity(), new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                mHomeCustomAdapter.setProducts(products);
                mProgressBar.setVisibility(View.GONE);
                result = products;

            }
        });
        mRecyclerView.setAdapter(mHomeCustomAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            ArrayList<Product> newResult = new ArrayList<>();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newResult.clear();
                for (Product p : result) {
                    if (p.getTitle().toLowerCase().trim().contains(newText.toLowerCase().trim()))
                        newResult.add(p);
                    mHomeCustomAdapter.setProducts(newResult);
                    mRecyclerView.setAdapter(mHomeCustomAdapter);
//                    Log.d("SearchView", "onQueryTextChange: " + newResult.get(0).getTitle());
                }
                return true;
            }

        });
        return view;
    }

    @Override
    public void onProductClick(Product product) {
        Intent bundle = new Intent(getActivity(), ProductDetails.class);
        bundle.putExtra("name", product.getTitle());
        bundle.putExtra("description", product.getDescription());
        bundle.putExtra("img", product.getImage());
        bundle.putExtra("count", product.getRating().getCount());
        bundle.putExtra("price", product.getPrice());
        bundle.putExtra("rate", product.getRating().getRate());
        startActivity(bundle);
    }
}