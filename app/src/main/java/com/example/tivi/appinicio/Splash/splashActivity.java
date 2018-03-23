package com.example.tivi.appinicio.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tivi.appinicio.MainActivity;
import com.example.tivi.appinicio.R;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startMainActivity();
    }

    private void startMainActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intento);
                finish();
            }
        },4000);
    }
}
