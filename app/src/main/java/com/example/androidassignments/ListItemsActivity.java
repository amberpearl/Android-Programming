package com.example.androidassignments;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Switch;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;


public class ListItemsActivity extends AppCompatActivity {
    protected static final String ACTIVITY_NAME = "ListItemsActivity";
    ImageButton imageButtons;
    Switch switchButtonCheck;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private CharSequence text;
    private int duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Log.i("ACTIVITY_NAME", "in onCreate()");
        imageButtons = findViewById(R.id.imageButton);

        imageButtons.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent ClickPicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                try {
                    startActivityForResult(ClickPicture, REQUEST_IMAGE_CAPTURE);
                } catch (ActivityNotFoundException x) {
                    Toast toast = Toast.makeText(getApplicationContext(), R.string.camera_error, Toast.LENGTH_SHORT);
                    toast.show();
                }
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

    protected void onStop(){
        super.onStop();
        Log.i("ACTIVITY_NAME", "in onStop()");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.i("ACTIVITY_NAME", "in onDestroy()");
    }

    public void setOnCheckedChanged(View view) {
    switchButtonCheck = findViewById(R.id.switchButton);
    int duration = 0;
    if (switchButtonCheck.isChecked()) {
            CharSequence text = "Switch is On";// "Switch is Off"
            duration = Toast.LENGTH_SHORT; //= Toast.LENGTH_LONG if Off

        }else if(!switchButtonCheck.isChecked()){
            CharSequence text = "Switch is Off";// "Switch is Off"
            duration = Toast.LENGTH_LONG; //= Toast.LENGTH_LONG if Off
        }

        Toast toast = Toast.makeText(this , text, duration); //this is the ListActivity
        toast.show(); //display your message box

    }

    public void OnCheckChanged(View view) { //entire code was provided by the professor
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(R.string.dialog_message) //Add a dialog message to strings.xml

                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent resultIntent = new Intent(  );
                        resultIntent.putExtra("Response", "Here is my response");
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageButtons.setImageBitmap(imageBitmap);
        }
    }

}
