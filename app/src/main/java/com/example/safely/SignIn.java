package com.example.safely;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safely.NetworkAPIs.AuthAPI;
import com.example.safely.NetworkModels.LoginModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity {
    Button SignUp;
    Button loginBtn;
    EditText email, password;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        SignUp = findViewById(R.id.buttonsignup);
        loginBtn = findViewById(R.id.buttonlogin);
        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        SignUp.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent newIntent = new Intent(SignIn.this,SignUp.class);
                        startActivity(newIntent);
                    }
                }
        );

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                progressDialog.setMessage("Logging in...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.base_url_auth)).addConverterFactory(GsonConverterFactory.create()).build();
                AuthAPI authAPI = retrofit.create(AuthAPI.class);

                Call<LoginModel> login = authAPI.login(email.getText().toString(), password.getText().toString());
                login.enqueue(new Callback<LoginModel>() {
                    @Override
                    public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                        progressDialog.dismiss();
                        Boolean success = response.body().getSuccess();
                        String message = response.body().getMessage();
                        Toast.makeText(SignIn.this, message, Toast.LENGTH_LONG).show();
                        if (!success)
                            return;
                        String token = response.body().getToken();
                        SharedPreferences sharedPreferences = getSharedPreferences("loginCache", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", token);
                        editor.commit();

                        startActivity(new Intent(SignIn.this, MapsActivity.class));

                    }

                    @Override
                    public void onFailure(Call<LoginModel> call, Throwable t) {
                        progressDialog.dismiss();
                        t.printStackTrace();
                        Toast.makeText(SignIn.this, "Check your network connection", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
