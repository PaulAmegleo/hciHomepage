// SplashActivity.java
package com.example.homepagetest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 5000; // Splash screen duration in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delayed execution to simulate a splash screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start your main activity after the splash screen
                startActivity(new Intent(SplashActivity.this, HomepageActivity.class));
                finish(); // Close splash activity to prevent user from going back
            }
        }, SPLASH_DURATION);
    }
}
