package com.example.coderamg.Splasch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.example.coderamg.Activity.MainActivity;
import com.example.coderamg.R;

public class SplachScreen extends AppCompatActivity {
LottieAnimationView lottieAnimationView;
    SharedPreferences onBoardingScreen;
    private static int SPLASH_TIMER=5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach_screen);

        lottieAnimationView =findViewById(R.id.lottie);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBoardingScreen =getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime =onBoardingScreen.getBoolean("firstTime",true);


                if (isFirstTime){

                    SharedPreferences.Editor editor=onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    Intent intent=new Intent(getApplicationContext(), Slider.class);
                    startActivity(intent);
                    finish();
                }else {

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();

                }


            }
        },SPLASH_TIMER);
       // lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);

    }
}