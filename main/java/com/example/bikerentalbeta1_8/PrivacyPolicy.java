package com.example.bikerentalbeta1_8;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class PrivacyPolicy extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.privacy_policy);
        CircularProgressButton back = findViewById(R.id.priback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrivacyPolicy.this.finish();
            }
        });
    }
}
