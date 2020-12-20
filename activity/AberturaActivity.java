package com.example.fa.jogo.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.fa.jogo.R;

public class AberturaActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {

    }

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abertura);

        new Handler().postDelayed(new Runnable() {
            /*
             * Exibindo splash com um timer.
             */
            @Override
            public void run() {
                // Esse método será executado sempre que o timer acabar
                // E inicia a activity principal
                Intent i = new Intent(AberturaActivity.this, JogadorActivity.class);
                startActivity(i);

                // Fecha esta activity
                finish();
                //Meetodo para desativar o botão voltar do celular

            }
        }, SPLASH_TIME_OUT);
    }
}

