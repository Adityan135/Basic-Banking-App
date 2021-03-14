package com.example.aditya.basicbankingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FinalActivity extends AppCompatActivity {
    TextView title;
    TextView message;
    ImageView imageView;

    public void gottofirst(View view){
        Intent intent=new Intent(FinalActivity.this,HomepageActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Toast.makeText(FinalActivity.this,"Press Done button to go to home page",Toast.LENGTH_SHORT).show();
        }
       return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        title=findViewById(R.id.Title);
        message=findViewById(R.id.Message);
        imageView=findViewById(R.id.ImageView);
        Intent intent=getIntent();
       // String name1=intent.getStringExtra("Name1");
        String name2=intent.getStringExtra("Name2");
        String msg=intent.getStringExtra("Message");
       // Log.i("Name1",name1);
        //Log.i("Name2",name2);
        if(msg.equals("Success")){
            title.setText(msg);
            imageView.setImageResource(R.drawable.greentick);
            message.setText("Your Transaction to "+name2+" is Successful");
        }
        else{
            title.setText(msg);
            imageView.setImageResource(R.drawable.wrong);
            message.setText("Your Transaction to "+name2+" is Failed");
        }

    }


}
