package com.example.aditya.basicbankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HomepageActivity extends AppCompatActivity {
  ArrayList<String> names;
  ArrayAdapter arrayAdapter;
  SQLiteDatabase nameslists;

  public void gotoTransaction(View view){
      Intent intent=new Intent(HomepageActivity.this,TransactionsActivity.class);
      startActivity(intent);
  }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void insertToDatabase(){
        Cursor c=nameslists.rawQuery("SELECT count(*) FROM users",null);
        c.moveToFirst();
        int count=c.getInt(0);
        if(count==0){
            nameslists.execSQL("DELETE FROM users WHERE name='Aditya'");
            nameslists.execSQL("INSERT INTO users(name,bankname,accountno,ifsc,balance) VALUES ('Aditya','Superbank','123456789','SU123465',5000)");
            nameslists.execSQL("INSERT INTO users(name,bankname,accountno,ifsc,balance) VALUES ('Devika','Nicebank','9672418652','NI122365',60523)");
            nameslists.execSQL("INSERT INTO users(name,bankname,accountno,ifsc,balance) VALUES ('Srinivasan','Goldenbank','72832182335','GO223465',7890)");
            nameslists.execSQL("INSERT INTO users(name,bankname,accountno,ifsc,balance) VALUES ('Dinesh','Goodbank','235366789','GD125465',6500)");
            nameslists.execSQL("INSERT INTO users(name,bankname,accountno,ifsc,balance) VALUES ('Pavan','Savebank','6789156789','SA133455',500000)");
            nameslists.execSQL("INSERT INTO users(name,bankname,accountno,ifsc,balance) VALUES ('Ram','Onebank','46676789','ON123465',5125670)");
            nameslists.execSQL("INSERT INTO users(name,bankname,accountno,ifsc,balance) VALUES ('Arjun','Bestbank','097552349','BE123465',47900)");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        ListView listView=findViewById(R.id.Names);
        names=new ArrayList<>();
        /*names.add("Aditya");
        names.add("Devika");
        names.add("Srinivasan");
        names.add("Dinesh");
        names.add("Pavan");
        names.add("Arjun");
        names.add("Ram");*/
        nameslists=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        nameslists.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR(20),bankname VARCHAR(20),accountno VARCHAR(20),ifsc VARCHAR(20),balance INTEGER)");
         insertToDatabase();
        Cursor c=nameslists.rawQuery("SELECT * FROM users",null);
        int eindex=c.getColumnIndex("name");
        int yindex=c.getColumnIndex("bankname");
        int iindex=c.getColumnIndex("accountno");
        int xindex=c.getColumnIndex("ifsc");
        int tindex=c.getColumnIndex("balance");
        c.moveToFirst();
        while(!c.isAfterLast()){
            //Log.i("name",c.getString(eindex));
            String a=c.getString(eindex);
            //Log.i("name",a);

           names.add(a);
            c.moveToNext();
        }
        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,names);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(HomepageActivity.this,userpage.class);
                intent.putExtra("username",names.get(position));
                startActivity(intent);
            }
        });

    }
}
