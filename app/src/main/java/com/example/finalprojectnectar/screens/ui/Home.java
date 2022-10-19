package com.example.finalprojectnectar.screens.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.model.Product;
import com.example.finalprojectnectar.data.viewmodel.ProductViewModel;
import com.example.finalprojectnectar.screens.adapter.HomeCustomAdapter;
import com.example.finalprojectnectar.screens.adapter.IOnProductClick;

import java.util.ArrayList;

public class Home extends AppCompatActivity implements IOnProductClick {

    private HomeCustomAdapter mHomeCustomAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Product> result = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SearchView searchView = findViewById(R.id.searchView);
        mRecyclerView = findViewById(R.id.productList);
        mHomeCustomAdapter = new HomeCustomAdapter(this);

        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductsFromRepo();
        productViewModel.getProducts().observe(this, new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> products) {
                mHomeCustomAdapter.setProducts(products);
                result = products;

            }
        });
        mRecyclerView.setAdapter(mHomeCustomAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            final ArrayList<Product> newResult = new ArrayList<>();

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                for (Product p : result) {
                    if (p.getTitle().toLowerCase().contains(newText.toLowerCase()))
                        newResult.add(p);

                    HomeCustomAdapter homeCustomAdapter = new HomeCustomAdapter(Home.this);
                    homeCustomAdapter.setProducts(newResult);
                    mRecyclerView.setAdapter(homeCustomAdapter);
                }
                return true;

            }

        });
    }

    @Override
    public void onProductClick(Product product) {
        Toast.makeText(this, product.getTitle(), Toast.LENGTH_SHORT).show();
    }
}