package com.example.aditya.basicbankingapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TransactTo extends AppCompatActivity {
String name;
String amt;
String enteredamt;
ArrayList<String> users;
SQLiteDatabase sqLiteDatabase;
ArrayAdapter arrayAdapter;
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transact_to);
        Intent intent=getIntent();
        name=intent.getStringExtra("Name");
        amt=intent.getStringExtra("Amount");
        enteredamt=intent.getStringExtra("EnteredAmount");
        users=new ArrayList<>();
        listView=findViewById(R.id.Users);
        sqLiteDatabase=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS transactions(name1 VARCHAR(20),name2 VARCHAR(20),status VARCHAR(20),amount VARCHAR(20))");
        Cursor c=sqLiteDatabase.rawQuery("SELECT * FROM users WHERE name NOT LIKE '"+name+"'",null);
        int nindex=c.getColumnIndex("name");
        c.moveToFirst();
        while(!c.isAfterLast()) {
            //Log.i("name", c.getString(nindex));
            users.add(c.getString(nindex));
            c.moveToNext();
        }
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,users);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(TransactTo.this)
                        .setTitle("Are Your Sure?")
                        .setMessage("Do you want to transfer amount to "+users.get(position)+"?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sqLiteDatabase.execSQL("INSERT INTO transactions(name1,name2,status,amount) VALUES('"+name+"','"+users.get(position)+"','SUCCESS','"+enteredamt+"')");
                                sqLiteDatabase.execSQL("UPDATE users SET balance="+String.valueOf(Integer.parseInt(amt)-Integer.parseInt(enteredamt))+" WHERE name='"+name+"'");
                                sqLiteDatabase.execSQL("UPDATE users SET balance="+String.valueOf(Integer.parseInt(amt)+Integer.parseInt(enteredamt))+" WHERE name='"+users.get(position)+"'");
                                Intent intent1=new Intent(TransactTo.this,FinalActivity.class);
                                //intent1.putExtra("Name1",name);
                                intent1.putExtra("Name2",users.get(position));
                                intent1.putExtra("Message","Success");
                                startActivity(intent1);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 Intent intent1=new Intent(TransactTo.this,FinalActivity.class);
                                sqLiteDatabase.execSQL("INSERT INTO transactions(name1,name2,status,amount) VALUES('"+name+"','"+users.get(position)+"','FAILURE','"+enteredamt+"')");
                                 intent1.putExtra("Name2",users.get(position));
                                 intent1.putExtra("Message","Failure");
                                 startActivity(intent1);
                            }
                        }).show();
            }
        });
        //Log.i("Name",name);
        //Log.i("Amt",enteredamt);
    }
}
