package com.example.finalprojectnectar.screens.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.database.CartDatabase;
import com.example.finalprojectnectar.data.database.CartDbModel;
import com.example.finalprojectnectar.data.viewmodel.CartViewModel;
import com.example.finalprojectnectar.data.viewmodel.ProductViewModel;
import com.example.finalprojectnectar.screens.adapter.CartCustomAdapter;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cart_fragment, container, false);
        CartCustomAdapter cartCustomAdapter = new CartCustomAdapter();
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        View btnAddToCart = view.findViewById(R.id.btnCheckOut);
        TextView txtTotal = view.findViewById(R.id.txtTotal);
        CartViewModel cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);


        cartCustomAdapter.setCartProducts(new ArrayList<>());
        RecyclerView recyclerView = view.findViewById(R.id.favList);
        progressBar.setVisibility(View.VISIBLE);
        cartViewModel.getCartProductsFromDatabase(getActivity());
        cartViewModel.getProducts().observe(getActivity(), new Observer<List<CartDbModel>>() {
            @Override
            public void onChanged(List<CartDbModel> cartDbModels) {
                double total = 0;
                for (CartDbModel model : cartDbModels)
                    total += model.getPrice();
                txtTotal.setText("$" + total);

                cartCustomAdapter.setCartProducts(cartDbModels);
                recyclerView.setAdapter(cartCustomAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                progressBar.setVisibility(View.GONE);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}