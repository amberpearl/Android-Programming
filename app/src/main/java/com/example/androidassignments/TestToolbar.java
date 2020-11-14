package com.example.androidassignments;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class TestToolbar extends AppCompatActivity {

    String ToastMessage;
    EditText SetMessage;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ToastMessage = "Choice 1 is selected";

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Good choice!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.toolbar_menu, m);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem m) {


        switch (m.getItemId()) {

            case R.id.Choice1:
                Log.d("Toolbar", "Choice 1 selected");
                Snackbar.make(getWindow().getDecorView().findViewById(R.id.toolbar), ToastMessage, Snackbar.LENGTH_LONG).show();
                break;

            case R.id.Choice2:
                Log.d("Toolbar", "Choice 2 selected");
                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                builder.setTitle("Testing Choice 2");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();// User clicked OK button
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // User cancelled the dialog
                    }
                });
                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();

                break;

            case R.id.Choice3:

                Log.d("Toolbar", "Choice 3 selected");

                AlertDialog.Builder customBuilder = new AlertDialog.Builder(TestToolbar.this);
                customBuilder.setTitle("Type Message");
                LayoutInflater inflater = this.getLayoutInflater();
                v = inflater.inflate(R.layout.dialog_test_toolbar, null);

                customBuilder.setView(v)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SetMessage = v.findViewById(R.id.type_message); //do you want to go back
                                ToastMessage = SetMessage.getText().toString();
                            }
                        })

                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // User cancelled the dialog
                            }
                        });

                // Create the AlertDialog
                AlertDialog customDialog = customBuilder.create();
                customDialog.show();

                break;

            default:
                Log.d("ToolBar", "Extra Selected");
                Toast toast = Toast.makeText(this, "Version 1.0 by Amber D'Silva", Toast.LENGTH_LONG);
                toast.show();
        }

        return false;
    }

}