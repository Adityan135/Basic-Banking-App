package com.example.aditya.basicbankingapp;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {
   public void gotonext(View view){
       Intent intent=new Intent(MainActivity.this,HomepageActivity.class);
       startActivity(intent);
   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ImageView imageView1=findViewById(R.id.banklogo);
        final ImageView imageView2=findViewById(R.id.background);
        final Button button=findViewById(R.id.viewbutton);
        final TextView textView1=findViewById(R.id.title);
        final TextView textView=findViewById(R.id.designby);
        textView1.setY(-1000);
        textView.setY(-1000);
        imageView1.setX(-1000);
        imageView1.setVisibility(View.VISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        imageView1.animate().translationXBy(1000).setDuration(2000).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                imageView1.animate().translationXBy(1300).setDuration(3000);
                textView1.animate().translationYBy(500).setDuration(3000);
                textView.animate().translationYBy(5000).setDuration(3000).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        imageView1.setVisibility(View.GONE);
                        textView1.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        imageView2.setVisibility(View.VISIBLE);
                        button.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }

        });
        textView1.animate().translationYBy(1000).setDuration(2000);
        textView.animate().translationYBy(1000).setDuration(2000);



    }
}
