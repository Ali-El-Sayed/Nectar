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
import com.example.finalprojectnectar.data.model.UserResponseModel;
import com.example.finalprojectnectar.data.network.user.UserClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    TextView txtLogIn;
    Button btnSignUp;
    EditText mUsername, mEmail, mPassword;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        txtLogIn = findViewById(R.id.txtLogIn);
        mUsername = findViewById(R.id.etxUsername);
        mEmail = findViewById(R.id.etxEmail);
        mPassword = findViewById(R.id.etxPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        mProgressBar = findViewById(R.id.progressBar);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUsername.getText().toString().isEmpty() ||
                        mEmail.getText().toString().isEmpty() ||
                        mPassword.getText().toString().isEmpty())
                    Toast.makeText(SignUp.this, "Check your Information", Toast.LENGTH_SHORT).show();
                else {
                    mProgressBar.setVisibility(View.VISIBLE);
                    UserClient.getInstance().signUp(mUsername.getText().toString(),
                            mEmail.getText().toString(),
                            mPassword.getText().toString()).enqueue(new Callback<UserResponseModel>() {
                        @Override
                        public void onResponse(Call<UserResponseModel> call, Response<UserResponseModel> response) {
                            mProgressBar.setVisibility(View.GONE);
                            if (response.isSuccessful()) {
                                if (response.body().getResponse().equals("هذا البريد مسجل من قبل"))
                                    Toast.makeText(SignUp.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                                else {
                                    Toast.makeText(SignUp.this, response.body().getResponse(), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignUp.this, LogIn.class));
                                    finish();
                                }
                            } else
                                Toast.makeText(SignUp.this, "Try Again", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onFailure(Call<UserResponseModel> call, Throwable t) {
                            mProgressBar.setVisibility(View.GONE);
                            Toast.makeText(SignUp.this, "Try Again", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });

        txtLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, LogIn.class));
                finish();
            }
        });
    }
}