package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.annotation.Annotation;

public class splashScreen extends AppCompatActivity {
    ImageView image;
    TextView text;
    Handler handler;
    public static int s = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        image = findViewById(R.id.splashImage);
        text = findViewById(R.id.ToDotxt);

        Animation animation = (Animation) AnimationUtils.loadAnimation(this, R.anim.myanim);

        image.startAnimation(animation);
        text.startAnimation(animation);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, s);
    }
}
