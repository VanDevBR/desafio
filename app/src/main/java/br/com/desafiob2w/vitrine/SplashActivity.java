package br.com.desafiob2w.vitrine;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    private Handler SplashDelayHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        SplashDelayHandler = new Handler();
        SplashDelayHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                goToHome();
            }

        }, 2000);

    }

    protected void goToHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
