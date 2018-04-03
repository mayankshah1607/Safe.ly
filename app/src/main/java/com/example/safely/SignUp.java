package com.example.safely;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.safely.NetworkAPIs.AuthAPI;
import com.example.safely.NetworkModels.BasicModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    EditText username, email, phone, pass, cnfpass, age;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        cnfpass = findViewById(R.id.cnfpass);
        age = findViewById(R.id.age);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(SignUp.this);
                progressDialog.setMessage("Signing up...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.base_url_auth)).addConverterFactory(GsonConverterFactory.create()).build();
                AuthAPI authAPI = retrofit.create(AuthAPI.class);

                Call<BasicModel> register = authAPI.register(username.getText().toString(), email.getText().toString(), phone.getText().toString(), pass.getText().toString(), age.getText().toString());
                register.enqueue(new Callback<BasicModel>() {
                    @Override
                    public void onResponse(Call<BasicModel> call, Response<BasicModel> response) {
                        progressDialog.dismiss();
                        Boolean success = response.body().getSuccess();
                        String message = response.body().getMessage();
                        Toast.makeText(SignUp.this, message, Toast.LENGTH_LONG).show();
                        if (!success)
                            return;
                        startActivity(new Intent(SignUp.this, SignIn.class));
                    }

                    @Override
                    public void onFailure(Call<BasicModel> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, "Check your network connection", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
