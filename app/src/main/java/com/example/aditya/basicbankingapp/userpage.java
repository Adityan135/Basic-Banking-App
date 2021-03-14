package com.example.aditya.basicbankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class userpage extends AppCompatActivity {
    SQLiteDatabase database;
    TextView username;
    TextView account;
    TextView bankname;
    TextView ifsc;
    TextView balance;
    public void gotonext(View view){
        Intent intent=new Intent(userpage.this,AmountEnterActivity.class);
        intent.putExtra("Name",username.getText().toString());
        intent.putExtra("Amount",balance.getText().toString());
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);
        username=findViewById(R.id.Name);
        account=findViewById(R.id.Account);
        bankname=findViewById(R.id.Bankname);
        ifsc=findViewById(R.id.Ifsc);
        balance=findViewById(R.id.Balance);
        database=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        Intent intent=getIntent();
        String name=intent.getStringExtra("username");
        Log.i("name",name);
        Cursor c=database.rawQuery("SELECT * FROM users WHERE name='"+name+"'",null);
        int nindex=c.getColumnIndex("name");
        int bindex=c.getColumnIndex("bankname");
        int aindex=c.getColumnIndex("accountno");
        int iindex=c.getColumnIndex("ifsc");
        int blindex=c.getColumnIndex("balance");
        c.moveToFirst();
        while(!c.isAfterLast()){

            username.setText(c.getString(nindex));
            bankname.setText(c.getString(bindex));
            account.setText(c.getString(aindex));
            ifsc.setText(c.getString(iindex));
            balance.setText(c.getString(blindex));
            c.moveToNext();
        }
    }
}
