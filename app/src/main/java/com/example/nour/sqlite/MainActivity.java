package com.example.nour.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.nour.sqlite.R.id.editText_id;

public class MainActivity extends AppCompatActivity {


        DB_Sqlit db =new DB_Sqlit(this);
        EditText name, email,ID;
        ListView lst;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            name =(EditText) findViewById(R.id.editText_name);
            email=(EditText)findViewById(R.id.editText_email);
            ID=(EditText)findViewById(R.id.editText_id);

            lst=(ListView)findViewById(R.id.list);
        }

        public void bt_add(  View view) {
            String Name=name.getText().toString();
            String Email=email.getText().toString();
            boolean result= db.insertData(Name,Email);
            if (result == true){
                Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                showData();
            }else {
                Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_SHORT).show();
            }
        }
        public void showData(){
            ArrayList<String> listData= db.getAllrecord();
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
            lst.setAdapter(arrayAdapter);
        }

        public void bt_Up(View view) {
            String Name=name.getText().toString();
            String Email=email.getText().toString();
            String id=ID.getText().toString();

            boolean result= db.updatedata(id,Name,Email);
            if (result == true){
                Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
                name.setText("");
                email.setText("");
                ID.setText("");
                showData();
            }else {
                Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_SHORT).show();
            }
        }

        public void bt_delete(View view) {
            String id=ID.getText().toString();
             int result = db.delete(id);
             if (result>0){
                 Toast.makeText(MainActivity.this,"OK",Toast.LENGTH_SHORT).show();
                 showData();
             }else {
                 Toast.makeText(MainActivity.this,"NO",Toast.LENGTH_SHORT).show();

             }
        }
    }
