package com.example.fa.jogo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fa.jogo.R;
import com.example.fa.jogo.classe.Geral;


public class JogadorActivity extends AppCompatActivity {

    private Button botaoAcesso;
    private EditText nomes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogador);

        botaoAcesso = findViewById(R.id.botaoMain);
        nomes = findViewById(R.id.textNome);

        botaoAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nomes.getText().length() == 0){//como o tamanho é zero é nulla a resposta

                    Toast.makeText(getApplicationContext(),"Preenche seu nome para liberar o jogo!", Toast.LENGTH_LONG).show();

                }else if (nomes.getText().toString().trim().equals("")){

                    Toast.makeText(getApplicationContext(),"Coloque no mínimo uma letra!", Toast.LENGTH_LONG).show();

                }else{

                    Intent a = new Intent(JogadorActivity.this, MainActivity.class);
                    startActivity(a);

                    String nome = nomes.getText().toString();

                    Geral jogador = new Geral();
                    jogador.setNome(nome);

                }
            }
        });
    }
}
