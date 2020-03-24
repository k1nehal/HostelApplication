package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity {
    private ImageView logo_image;
    private static int splash_time=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo_image=findViewById(R.id.logo);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this,MainActivity.class);
//                Intent i=new Intent(SplashScreen.this,StudentDetails.class);
//                Intent i=new Intent(SplashScreen.this,WardenDashboard.class);
//                Intent i=new Intent(SplashScreen.this,DetailsOther.class);
//                Intent i=new Intent(SplashScreen.this,DetailsBasic.class);

                startActivity(i);
                finish();
            }
        },splash_time);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.logoanim);
        logo_image.startAnimation(animation);
    }
}
