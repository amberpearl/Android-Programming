package com.example.androidassignments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import android.database.Cursor;


public class ChatWindow extends AppCompatActivity {
    Button SendButton;
    EditText textInput;
    ListView listView;
    ChatAdapter messageAdapter;
    ArrayList<String> Message_Array;
    private SQLiteDatabase Database;
    private ChatDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        SendButton = findViewById(R.id.send_button);
        textInput = findViewById(R.id.edit_button);
        listView = findViewById(R.id.list_view);

        databaseHelper = new ChatDatabaseHelper(this);
        Database = databaseHelper.getWritableDatabase();

        String[] columns = {ChatDatabaseHelper.KEY_MESSAGE};
        Cursor cursor = Database.query(ChatDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){

            Message_Array.add(cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            Log.i("ChatWindow.java","SQL MESSAGE: " + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE)));
            cursor.moveToNext();

        }

        Log.i("ChatWindow.java", "Cursor’s  column count =" + cursor.getColumnCount());

        for(int i=0;i<cursor.getColumnCount();i++){

            Log.i("ChatWindow.java",cursor.getColumnName(i));

        }

        cursor.close();

        Message_Array = new ArrayList<>();
        messageAdapter = new ChatAdapter(this);
        listView.setAdapter(messageAdapter);

        SendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Message_Array.add(textInput.getText().toString());
                ContentValues contentValues = new ContentValues();
                contentValues.put(ChatDatabaseHelper.KEY_MESSAGE,textInput.getText().toString());
                Database.insert(ChatDatabaseHelper.TABLE_NAME,null,contentValues);
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/getView()
                textInput.setText("");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Database.close();
    }

    private class ChatAdapter extends ArrayAdapter<String>{

        public ChatAdapter(@NonNull Context context) {
            super(context, 0);
        }

        public int getCount(){

            return Message_Array.size();
        }

        public String getItem(int position){
            return Message_Array.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();

            View result;

            if(position%2==0){
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            }
            else{
                result = inflater.inflate(R.layout.chat_row_outgoing,null);
            }

            TextView message = result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;

        }

    }
}