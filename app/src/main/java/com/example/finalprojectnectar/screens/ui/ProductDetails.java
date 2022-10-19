package com.example.finalprojectnectar.screens.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.finalprojectnectar.R;
import com.squareup.picasso.Picasso;

public class ProductDetails extends AppCompatActivity {
    private TextView txtName, txtDescription, txtPrice, txtCount;
    private ImageView imgProduct, btnExpand;
    private RatingBar mRatingBar;
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
        btnExpand = findViewById(R.id.btnExpand);
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


        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String description = bundle.getString("description");
        String img = bundle.getString("img");
        count = bundle.getInt("count");
        double price = bundle.getDouble("price");
        double rate = bundle.getDouble("rate");

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