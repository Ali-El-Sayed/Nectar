package com.example.finalprojectnectar.screens.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.database.CartDatabase;
import com.example.finalprojectnectar.data.database.CartDbModel;
import com.example.finalprojectnectar.data.database.FavDb;
import com.example.finalprojectnectar.data.database.FavouriteDb;
import com.squareup.picasso.Picasso;

public class ProductDetails extends AppCompatActivity {
    private TextView txtName, txtDescription, txtPrice, txtCount;
    private ImageView imgProduct, btnExpand, btnAddToFav;
    private RatingBar mRatingBar;
    Button btnAddToCart;
    private int count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        txtPrice = findViewById(R.id.txtPrice);
        txtCount = findViewById(R.id.txtCount);
        imgProduct = findViewById(R.id.imgProduct);
        mRatingBar = findViewById(R.id.rate);
        btnAddToFav = findViewById(R.id.btnAddToFav);
        btnExpand = findViewById(R.id.btnExpand);
        btnAddToCart = findViewById(R.id.btnAddToCart);

        //Intent Extras
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String description = bundle.getString("description");
        String img = bundle.getString("img");
        count = bundle.getInt("count");
        double price = bundle.getDouble("price");
        double rate = bundle.getDouble("rate");

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        CartDatabase.getInstance(ProductDetails.this).Dao().insert(
                                new CartDbModel(null, name, price, count, img)
                        );
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProductDetails.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        btnExpand.setOnClickListener(new View.OnClickListener() {
            boolean expanded = false;

            @Override
            public void onClick(View v) {
                if (!expanded) {
                    txtDescription.setMaxLines(10);
                    expanded = true;
                    btnExpand.setImageResource(R.drawable.ic_arrow_up);
                } else {
                    txtDescription.setMaxLines(2);
                    expanded = false;
                    btnExpand.setImageResource(R.drawable.ic_arrow_down);
                }
            }
        });
        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FavouriteDb.getInstance(ProductDetails.this).Dao()
                                .insert(new FavDb(null, name, (int) price, count, img));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProductDetails.this, "Added To Favourite", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).start();
            }
        });


        txtName.setText(name);
        txtDescription.setText(description);
        txtPrice.setText("$" + price);
        txtCount.setText("" + count);
        mRatingBar.setRating((float) rate);
        Picasso.get().load(img).into(imgProduct);


    }

    public void onBackPress(View view) {
        onBackPressed();
    }

    public void onDecrease(View view) {
        int current = Integer.parseInt(txtCount.getText().toString());
        if (current != 0)
            txtCount.setText(--current + "");

    }

    public void onIncrease(View view) {
        int current = Integer.parseInt(txtCount.getText().toString());
        if (current < count)
            txtCount.setText(++current + "");
    }
}