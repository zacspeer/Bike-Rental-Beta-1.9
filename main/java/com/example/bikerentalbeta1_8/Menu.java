package com.example.bikerentalbeta1_8;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class Menu extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        getSupportActionBar().hide();
        mAuth= FirebaseAuth.getInstance();
        CircularProgressButton cpbLogout = findViewById(R.id.cirLogout);
        cpbLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Menu.this.finish();
                startActivity(new Intent(Menu.this,LoginActivity.class));
            }
        });
        CircularProgressButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.startAnimation();
                Menu.this.finish();
                back.revertAnimation();
            }
        });
        Button rented = findViewById(R.id.rentedRides);
        rented.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,RentedRides.class));
            }
        });
        Button registeraveh = findViewById(R.id.regVehicle);
        registeraveh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,Registeravehicle.class));
            }
        });
        Button privacy = findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,PrivacyPolicy.class));
            }
        });
        Button aboutus = findViewById(R.id.aboutus);
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this,AboutUs.class));
            }
        });
    }
}
