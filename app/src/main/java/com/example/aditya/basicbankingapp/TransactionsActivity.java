package com.example.aditya.basicbankingapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {
SQLiteDatabase transactions;
public boolean check(){
    Cursor c=transactions.rawQuery("SELECT count(*) FROM transactions",null);
    c.moveToFirst();
    int count=c.getInt(0);
    if(count==0){
      return false;
    }
    return true;
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        ListView listView=findViewById(R.id.transactions);
        // Transaction first=new Transaction("Aditya","Devika","Success","100");
        // Transaction second=new Transaction("Aditya","Devika","Success","100");
        ArrayList<Transaction> transactionlist=new ArrayList<>();
        // transactionlist.add(first);
        //transactionlist.add(second);
        transactions=this.openOrCreateDatabase("Users",MODE_PRIVATE,null);
        transactions.execSQL("CREATE TABLE IF NOT EXISTS transactions(name1 VARCHAR(20),name2 VARCHAR(20),status VARCHAR(20),amount VARCHAR(20))");
        if(check()){
            Cursor c=transactions.rawQuery("SELECT * FROM transactions",null);
            int n1index=c.getColumnIndex("name1");
            int n2index=c.getColumnIndex("name2");
            int sindex=c.getColumnIndex("status");
            int aindex=c.getColumnIndex("amount");
            c.moveToLast();
            while(!c.isBeforeFirst()){

                String a=c.getString(n1index);
                String b=c.getString(n2index);
                String x=c.getString(sindex);
                String y="₹ "+c.getString(aindex);
                Transaction transaction=new Transaction(a,b,x,y);
                transactionlist.add(transaction);
                c.moveToPrevious();
            }



        TransactionListAdapter adapter=new  TransactionListAdapter(this,R.layout.adapter_list_view,transactionlist);
        listView.setAdapter(adapter);}

    }
}
