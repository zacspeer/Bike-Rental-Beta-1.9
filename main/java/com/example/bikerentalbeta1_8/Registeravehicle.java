package com.example.bikerentalbeta1_8;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class Registeravehicle extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.register_a_ride);
        listeners();
    }

    private void listeners() {
        EditText name = findViewById(R.id.name);
        EditText vehno = findViewById(R.id.vehino);
        EditText rcno = findViewById(R.id.rcno);
        EditText dlno = findViewById(R.id.dlno);
        EditText address = findViewById(R.id.address);
        CircularProgressButton back = findViewById(R.id.regback);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Registeravehicle.this.finish();
            }
        });
        CircularProgressButton register = findViewById(R.id.registerveh);
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO: get the texts and upload to server for registration
                register.startAnimation();
                String Name = name.getText().toString();
                String Vehino = vehno.getText().toString();
                String Rcno = rcno.getText().toString();
                String Dlno = dlno.getText().toString();
                String Address = address.getText().toString();
                Toast.makeText(getApplicationContext(),"Server Error, ID: 1120",Toast.LENGTH_LONG).show();
                register.revertAnimation();
            }
        });
    }
}
