package com.example.safely;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.safely.NetworkAPIs.UserAPI;
import com.example.safely.NetworkModels.FetchUserModel;
import com.example.safely.NetworkModels.LocationModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView estimatedVal, risk, homeAddress;
    FloatingActionButton status, search, add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        estimatedVal = findViewById(R.id.estimatedValue);
        risk = findViewById(R.id.riskOfDisaster);
        status = findViewById(R.id.statusFab);
        search = findViewById(R.id.searchFab);
        add = findViewById(R.id.addLocFab);
        homeAddress = findViewById(R.id.homeAddress);


        estimatedVal.setText("$ 130,000");
        risk.setText("Medium");
        risk.setTextColor(Color.parseColor("#00AA00"));
        homeAddress.setText("VIT Food Court");

        YoYo.with(Techniques.FadeInUp).duration(1500).repeat(0).playOn(status);
        YoYo.with(Techniques.FadeInUp).duration(1500).repeat(0).playOn(search);
        YoYo.with(Techniques.FadeInUp).duration(1500).repeat(0).playOn(add);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        SharedPreferences sharedPreferences = getSharedPreferences("loginCache", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");

        final ProgressDialog progressDialog = new ProgressDialog(MapsActivity.this);
        progressDialog.setMessage("Diving into the app...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.dismiss();


        LatLng sydney = new LatLng(12.970391, 79.159042);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Home"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 16.0f));
/*
        Retrofit retrofit = new Retrofit.Builder().baseUrl(getResources().getString(R.string.base_url_user)).addConverterFactory(GsonConverterFactory.create()).build();
        UserAPI userAPI = retrofit.create(UserAPI.class);

        Call<FetchUserModel> fetchDetails = userAPI.fetchDetails(token);
        fetchDetails.enqueue(new Callback<FetchUserModel>() {
            @Override
            public void onResponse(Call<FetchUserModel> call, Response<FetchUserModel> response) {
                progressDialog.dismiss();
                String message = response.body().getMessage();
                Toast.makeText(MapsActivity.this, message, Toast.LENGTH_LONG).show();
                if (!response.body().getSuccess())
                    return;
                List<LocationModel> locations = response.body().getUser().getLocations();
                LocationModel home = locations.get(locations.size());
                Geocoder geocoder = new Geocoder(MapsActivity.this, Locale.getDefault());
                try {
                    Address address = geocoder.getFromLocation(Double.parseDouble(home.getGeolocation().split("")[0]), Double.parseDouble(home.getGeolocation().split("")[1]), 1).get(0);
                    homeAddress.setText(address.getAddressLine(0));

                    // Add a marker in Sydney and move the camera
                } catch (IOException e) {
                    e.printStackTrace();
                }
                estimatedVal.setText("$ " + home.getCost().toString());
                risk.setText(home.getRisk());

            }

            @Override
            public void onFailure(Call<FetchUserModel> call, Throwable t) {
                progressDialog.dismiss();
                t.printStackTrace();;
                Toast.makeText(MapsActivity.this, "Check network connection", Toast.LENGTH_LONG).show();
            }
        });*/

    }
}
