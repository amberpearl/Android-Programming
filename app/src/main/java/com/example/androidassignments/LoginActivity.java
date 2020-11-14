package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.content.Intent;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    SharedPreferences mySharedPreferences;
    Button LoginButton;
    public String EmailString;
    private EditText EmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.i("ACTIVITY_NAME", "in onCreate()");
        EmailAddress = findViewById(R.id.login_email);
        mySharedPreferences = getSharedPreferences("StoreEmail", Context.MODE_PRIVATE); //used to save key value data
        EmailAddress.setText(mySharedPreferences.getString("UserEmail", "email@domain.com"));
        LoginButton = findViewById(R.id.login_button);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putString("UserEmail", EmailAddress.getText().toString());
                editor.commit();

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }

    protected void onStart() {
        super.onStart();
        Log.i("ACTIVITY_NAME", "in onStart()");
    }
    protected void onPause() {
        super.onPause();
        Log.i("ACTIVITY_NAME", "in onPause()");
    }

    protected void onStop() {
        super.onStop();
        Log.i("ACTIVITY_NAME", "in onStop()");
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.i("ACTIVITY_NAME", "in onDestroy()");
    }
}