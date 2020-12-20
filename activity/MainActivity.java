package com.example.fa.jogo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.fa.jogo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(
            final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            }


public void apertou(View view){
    Intent i = new Intent(MainActivity.this, FacilActivity.class);
    startActivity(i);

}

public void apertou_m(View view){
        Intent m = new Intent(MainActivity.this, MedioActivity.class);
        startActivity(m);
}

public  void apertou_d(View view){
        Intent d = new Intent(MainActivity.this, DificilActivity.class);
             startActivity(d);
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){

        switch (item.getItemId()) {
            case R.id.ranking:
                startActivity(new Intent(getApplicationContext(), RankingActivity.class));
                break;
            case R.id.sugestoes:
                startActivity(new Intent(getApplicationContext(), SobreActivity.class));
                break;
            case R.id.sobre:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    //Meetodo para desativar o botão voltar do celular
    @Override
    public void onBackPressed() {

    }

}
