package com.example.bikerentalbeta1_8;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class RentedRides extends AppCompatActivity {
    String[] mobileArray = {"No vehicles yet, try your first today!☺"};
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.rented_rides);
        listeners();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_view, mobileArray);
        ListView listView = (ListView) findViewById(R.id.listv);
        listView.setAdapter(adapter);

    }
    public void listeners(){
        CircularProgressButton back  = findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RentedRides.this.finish();
            }
        });
    }
}
