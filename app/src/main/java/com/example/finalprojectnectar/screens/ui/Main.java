package com.example.finalprojectnectar.screens.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.screens.fragments.AccountFragment;
import com.example.finalprojectnectar.screens.fragments.ExploreFragment;
import com.example.finalprojectnectar.screens.fragments.FavouriteFragment;
import com.example.finalprojectnectar.screens.fragments.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Main extends AppCompatActivity {
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBottomNavigationView = findViewById(R.id.bottomNavBar);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.shopMenu:
                        replaceFragment(new ShopFragment());
                        break;

                    case R.id.exploreMenu:
                        replaceFragment(new ExploreFragment());

                        break;

                    case R.id.cartMenu:

                        Toast.makeText(Main.this, "Cart", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.favMenu:
                        replaceFragment(new FavouriteFragment());
                        break;

                    case R.id.accMenu:
                        replaceFragment(new AccountFragment());
                        break;
                }
                return true;
            }
        });
        replaceFragment(new ShopFragment());
    }


    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainContent, fragment);
        fragmentTransaction.commit();
    }
}