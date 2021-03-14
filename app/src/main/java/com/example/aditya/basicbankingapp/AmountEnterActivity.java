package com.example.aditya.basicbankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AmountEnterActivity extends AppCompatActivity {
    String name;
    String amt;
    EditText editText;
    public void onButtonclick(View view){
        int amtint=Integer.parseInt(amt);
        if(editText.getText().toString().isEmpty()){
            Toast.makeText(this,"Enter an Amount",Toast.LENGTH_SHORT).show();
        }
        else {
            String check = editText.getText().toString();
            int flag = 0;
            for (int i = 0; i < check.length(); i++) {
                if (check.charAt(i) < 48 || check.charAt(i) > 57) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                Toast.makeText(this, "Amount should contain only Integers", Toast.LENGTH_SHORT).show();
            } else {
                int editval = Integer.parseInt(editText.getText().toString());
                if(editval==0){
                    Toast.makeText(this,"Enter an amount greater than ₹ 0",Toast.LENGTH_SHORT).show();
                }
                else if (editval > amtint) {
                    Toast.makeText(this, "Enter an amount less than ₹ " + amt, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(AmountEnterActivity.this, TransactTo.class);
                    intent.putExtra("Name", name);
                    intent.putExtra("Amount", amt);
                    intent.putExtra("EnteredAmount", editText.getText().toString());
                    startActivity(intent);
                }
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_enter);
        editText=findViewById(R.id.amount);
        Intent intent=getIntent();
        name=intent.getStringExtra("Name");
        amt=intent.getStringExtra("Amount");

    }
}
