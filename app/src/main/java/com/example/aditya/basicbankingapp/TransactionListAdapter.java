package com.example.aditya.basicbankingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aditya on 13-03-2021.
 */

public class TransactionListAdapter extends ArrayAdapter<Transaction> {
    private Context mcontext;
    private int mresource;
    public TransactionListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Transaction> objects) {
        super(context, resource, objects);
        mcontext=context;
        mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String name1=getItem(position).getName1();
        String name2=getItem(position).getName2();
        String status=getItem(position).getStatus();
        String amount=getItem(position).getAmount();
        Transaction transaction=new Transaction(name1,name2,status,amount);
        LayoutInflater layoutInflater=LayoutInflater.from(mcontext);
        convertView=layoutInflater.inflate(mresource,parent,false);

        TextView NAME1=convertView.findViewById(R.id.Name1);
        TextView NAME2=convertView.findViewById(R.id.Name2);
        TextView STATUS=convertView.findViewById(R.id.Status);
        TextView AMOUNT=convertView.findViewById(R.id.Amount);
        ImageView PIC=convertView.findViewById(R.id.Arrow);
        NAME1.setText(name1);
        NAME2.setText(name2);
        STATUS.setText(status);
        AMOUNT.setText(amount);
        return convertView;
    }
}

