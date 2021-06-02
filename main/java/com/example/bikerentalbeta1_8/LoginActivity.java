package com.example.bikerentalbeta1_8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class LoginActivity extends AppCompatActivity {
    public boolean state;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        FirebaseUser user = mAuth.getCurrentUser();
         if (user != null) {
             Intent main = new Intent(LoginActivity.this, MainActivity.class);
             startActivity(main);
             LoginActivity.this.finish();
         } else {
             signin();
         }

    }

    public void signin(){
        setContentView(R.layout.activity_login);
        ViewFlipper viewFlipper = (ViewFlipper)findViewById(R.id.vf);
        viewFlipper.setDisplayedChild(0);
        CircularProgressButton cpbRegister = findViewById(R.id.cirLoginButtonReg);
        CircularProgressButton cpbSignin = findViewById(R.id.cirLogout);
        EditText sname = findViewById(R.id.semail);
        EditText spass = findViewById(R.id.spass);
        TextView name = findViewById(R.id.email);
        TextView pass = findViewById(R.id.pass);
        cpbSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = sname.getText().toString();
                String upass = spass.getText().toString();
                if(uname.isEmpty() || upass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please provide valid Email and Password", Toast.LENGTH_LONG).show();
                }
                else if(uname.isEmpty()&&upass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Fields are Empty",Toast.LENGTH_LONG).show();
                }
                else {
                    cpbSignin.startAnimation();
                    sign(uname, upass,cpbSignin);
                }
            }
        });
        cpbRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpbRegister.startAnimation();
                String uname = String.valueOf(name.getText());
                String upass = String.valueOf(pass.getText());
                if(uname!= null && upass!=null) {
                    createAccount(uname, upass,viewFlipper,cpbRegister);
                }
                else
                    Toast.makeText(getApplicationContext(),"Please Enter a valid Credentials",Toast.LENGTH_LONG).show();
            }
        });
        TextView signup =findViewById(R.id.dSignUp);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(1);
                sname.clearComposingText();
                spass.clearComposingText();
            }
        });
        TextView signin = findViewById(R.id.aSignUp);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewFlipper.setDisplayedChild(0);
                name.clearComposingText();
                pass.clearComposingText();
            }
        });
        TextView fpass = findViewById(R.id.forgotpassword);
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotPassword(viewFlipper);
            }
        });

    }

    public void createAccount(String email, String password, ViewFlipper vf,CircularProgressButton cpb) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"Success, Please Sign in",Toast.LENGTH_LONG).show();
                            cpb.revertAnimation();
                            vf.setDisplayedChild(0);
                        } else {
                            Toast.makeText(getApplicationContext(),"Account already exists",Toast.LENGTH_LONG).show();
                            cpb.revertAnimation();
                            vf.setDisplayedChild(0);
                        }
                    }
                });
    }
    public void sign(String email, String pass,CircularProgressButton cpb){
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    cpb.revertAnimation();
                } else {
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    cpb.revertAnimation();
                    startActivity(intent);
                }
            }
        });
    }
    public void forgotPassword(ViewFlipper vf){
        vf.setDisplayedChild(2);
        EditText femail = findViewById(R.id.forgotemail);
        femail.clearComposingText();
        TextView loginhere = findViewById(R.id.fSignin);
        CircularProgressButton cpbsendlink = findViewById(R.id.cirSendLink);
        cpbsendlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpbsendlink.startAnimation();
                String email = femail.getText().toString();
                if(!email.isEmpty()) {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Check you email for Link",Toast.LENGTH_LONG).show();
                                vf.setDisplayedChild(0);
                                cpbsendlink.revertAnimation();
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "Account does not exist.", Toast.LENGTH_LONG).show();
                                cpbsendlink.revertAnimation();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(), "Enter a valid email", Toast.LENGTH_LONG).show();
                    cpbsendlink.revertAnimation();
                }
            }
        });
        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vf.setDisplayedChild(0);
            }
        });
    }

}
