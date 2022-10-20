package com.example.finalprojectnectar.screens.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalprojectnectar.R;
import com.example.finalprojectnectar.data.local.SaveData;
import com.example.finalprojectnectar.data.model.UserResponseModel;
import com.example.finalprojectnectar.data.network.user.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    TextView txtSignUp;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        txtSignUp = findViewById(R.id.txtSignUp);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        mProgressBar = findViewById(R.id.progressBar);

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogIn.this, SignUp.class));
                finish();
            }
        });

        if (new SaveData(LogIn.this).getUserStatus()) {
            startActivity(new Intent(LogIn.this, Main.class));
            finish();
        }

    }

    public void onLogging(View view) {
        if (txtEmail.getText().toString().isEmpty() || txtPassword.getText().toString().isEmpty()) {
            mProgressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Enter You Info", Toast.LENGTH_SHORT).show();
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            UserClient.getInstance().signIn(txtEmail.getText().toString(), txtPassword.getText().toString()).enqueue(new Callback<UserResponseModel>() {
                @Override
                public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                    mProgressBar.setVisibility(View.GONE);
                    if (response.isSuccessful()) {
                        if (response.body().getResponse().equals("تأكد من البريد او الرقم السرى"))
                            Toast.makeText(LogIn.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                        else {
                            Toast.makeText(LogIn.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                            new SaveData(LogIn.this).updateUserData(true);
                            startActivity(new Intent(LogIn.this, Main.class));
                            finish();
                        }
                    } else
                        Toast.makeText(LogIn.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<UserResponseModel> call, Throwable t) {
                    mProgressBar.setVisibility(View.GONE);
                    Toast.makeText(LogIn.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}