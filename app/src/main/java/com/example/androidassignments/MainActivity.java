package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "MainActivity";
    Button LoginButtonList;
    Button StartChatButton;
    Button ToolbarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ACTIVITY_NAME", "in onCreate()");

        LoginButtonList = findViewById(R.id.trybutton);
        StartChatButton = findViewById(R.id.start_button);
        ToolbarButton = findViewById(R.id.toolbar_button);

        LoginButtonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListItemsActivity.class);
                startActivityForResult(intent,10);
            }
        });


        StartChatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked Start Chat");
                Intent intent = new Intent(MainActivity.this, ChatWindow.class);
                startActivity(intent);
            }
        });

        ToolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "User clicked Toolbar Button");
                Intent intent = new Intent(MainActivity.this, TestToolbar.class);
                startActivity(intent);
            }
        });
    }


    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
            super.onActivityResult(requestCode, responseCode, data);
                if (requestCode == 10) {
                Log.i(ACTIVITY_NAME, "Returned to MainActivity.onActivityResult");
             }
                if (responseCode == Activity.RESULT_OK) {
                String messagePassed = data.getStringExtra("Response");
                Toast toast = Toast.makeText(this, "ListItemsActivity passed: " + messagePassed, Toast.LENGTH_LONG);
                toast.show();
        }
    }



    protected void onResume() {
        super.onResume();
        Log.i("ACTIVITY_NAME", "in onResume()");
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