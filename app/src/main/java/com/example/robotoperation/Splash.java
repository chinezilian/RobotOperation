package com.example.robotoperation;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;

public class Splash extends Activity {
    private static int SPLASH_TIME_OUT = 10000;
    public static EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        text = (EditText) findViewById(R.id.text);
       // new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
          /*  @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent secondActivity = new Intent(Splash.this, MainActivity.class);
                startActivity(secondActivity);

                // Fecha esta activity
                finish();
            }
        }, SPLASH_TIME_OUT);*/

    }
    public void startFirstActivity(View view) {
        Intent firstActivity = new Intent(this, MainActivity.class);
        startActivity(firstActivity);
    }
}
