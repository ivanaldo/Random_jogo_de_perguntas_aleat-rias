package com.example.fa.jogo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fa.jogo.R;
import com.example.fa.jogo.classe.Geral;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class FacilActivity extends AppCompatActivity {

    private Button botao1;
    private Button botao2;
    private Button botao3;
    private ImageView imagem;
    private TextView texto;
    private TextView livro;
    private int resultado = 0;
    private TextView ponto;
    private Geral soma;
    private TextView cronometro;
    private TextView alerta;
    private int pontos = 0;
    private Boolean para = false;
    private int fim = 0;
    private String[] lista = new String[60];

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String[] frases = {
            "Quantos anos Noé passou pra fazer a Arca?", "Qual foi o rei mais sábio de Israel?", "Quem foi o homem mais forte do mundo?",
            "Qual foi a primeira praga do Egito?", "Quem foi o pai de Isaque?", "Quem foi o pai de Jacó?", "Qual foi o primeiro filho de Adão?",
            "O primeiro milagre de Jesus foi de?", "Quantos foram o apóstolos de Jesus?", "Qual foi o primeiro nome de Eva?", "Qual é o nome do pai de João batista?",
            "Quantos dias durou o dilúvio?", "Quem é o pai do rei Salomão?", "Qual o nome da mulher de Abraão?","Quem escreveu os 10 mandamentos",
            "Qual é o segundo filho de Adão e Eva?", "Qual o terceiro filho de Adão e Eva?", "Qual discípulo traiu Jesus?", "Quem é o dono da mula que falou?",
            "Quem sonhou com uma escada até o céu?", "Qual o nome do gigante que Davi venceu?", "Quem construiu a arca?", "Quem foi lançado na cova dos leões?",
            "Quem Deus mandou para libertar Israel do Egito?", "Quem subiu na árvore para vê Jesus passar?", "Qual profeta foi engolido por um peixe?",
            "Qual o primeiro homem do  mundo?", "Que peixe engoliu Jonas?", "Qual o homem que perdeu tudo e depois Deus lhe deu tudo de volta em dobro?",
            "Onde Jesus nasceu?"
    };

    private int[] drawables = {
            R.drawable.arca, R.drawable.rei, R.drawable.sansao, R.drawable.pragas, R.drawable.abraao, R.drawable.isaque, R.drawable.caim,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao,R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao

    };
    private String[] livros = {"Gênesis 5:32; 7:6", "2Crônicas 1:11-12", "Juízes 14:5-6, 15:14-15", "Gênesis 7:14-25", "Gênesis 21:1-4",
            "Gênesis 25:19-26", "Gênesis 4:1", "João 2:1-12", "Mateus 10:1-4", "Gênesis 2:23", "Lucas 1:57-63", "Gênesis 7:17", "2Crônicas 1:1",
            "Gênesis 12:5", "Êxodo 31:18", "Gênesis 4:2", "Gênesis 5:25", "Mateus 26:47-56", "Números 22:21-35", "Gênesis 28: 10-15", "1Samuel 17:1-54",
            "Gênesis 6:9-22", "Daniel 6:16", "Êxodo 3:1-10", "Lucas 19:1-10", "Jonas 1:17", "Gênesis 2:4-19", "Jonas 1:17", "Jó 1:13-22; 42:10",
            "Lucas 2:1-7"

    };

    //Instância da classe Jogador
    Geral geral = new Geral();

    //Meetodo para desativar o botão voltar do celular
    @Override
    public void onBackPressed() {
        finish();
    }

    public void aleatorio() {
        para = false;
        Random numeroRandomico = new Random();
        final int valor = numeroRandomico.nextInt(frases.length);

        for (int i = 0; i < frases.length && para == false ; i++){

            for (int j = 0; j < lista.length; j++) {

                if (lista[j] == Integer.toString(valor)){
                    aleatorio();
                    para = true;
                    break;
                }
                }

            if (lista[i] == null && para == false ) {
                lista[i] = Integer.toString(valor);
                imagem.setImageResource(drawables[valor]);
                texto.setText(frases[valor]);
                resultado = valor;
                livro.setText(" ");
                alerta.setText(" ");
                jogo();
                break;

            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facil);

        //Inicializa componentes
        ponto = findViewById(R.id.somaPontos);
        botao1 = findViewById(R.id.botaoF1);
        botao2 = findViewById(R.id.botaoF2);
        botao3 = findViewById(R.id.botaoF3);
        texto = findViewById(R.id.text);
        livro = findViewById(R.id.livros);
        cronometro = findViewById(R.id.tempo);
        alerta = findViewById(R.id.mensagem);
        imagem = (ImageView) findViewById(R.id.imageView);

        //Chama metodo Random
        aleatorio();
    }

    public void menssagemErro(){

            alerta.setText("Sua resposta está errada");
            alerta.setTextColor(getResources().getColor(R.color.vermelho));
            //Cancelara contagem do cronometro
            countDownTimer.cancel();

            //metodo para aguardar 2 segundos antes de chamar metodo Random
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (fim != 30) {
                        aleatorio();
                    }else{
                        finalizou();
                        texto.setText(" ");
                    }
                }
            }, 2000);
    }

    public void menssagemAcerto(){

            alerta.setText("Parabéns você acertou!");
            alerta.setTextColor(getResources().getColor(R.color.verde));
            //Cancela a contagem do cronometro
            countDownTimer.cancel();

            //metodo para aguardar 2 segundos antes de chamar metodo Random
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (fim != 30) {
                        aleatorio();
                    }else {
                        finalizou();
                        texto.setText(" ");
                    }
                }
            }, 2000);

        }
    public void tempo(){
            alerta.setText("Seu tempo acabou!");
            alerta.setTextColor(getResources().getColor(R.color.vermelho));

            //bloqueia botões
            botao1.setEnabled(false);
            botao2.setEnabled(false);
            botao3.setEnabled(false);

            //metodo para aguardar 2 segundos antes de chamar metodo Random
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (fim != 30) {
                        aleatorio();
                    }else {
                        finalizou();
                        texto.setText(" ");
                    }
                }
            }, 2000);

    }

        public void finalizou(){
        alerta.setText("Parabéns você concluiu o modo fácil!");
        livro.setText(" ");
        alerta.setTextColor(getResources().getColor(R.color.colorAccent));
        imagem.setImageResource(R.drawable.parabens);
        botao1.setVisibility(View.GONE);
        botao2.setVisibility(View.GONE);
        botao3.setVisibility(View.GONE);


            //metodo para aguardar 2 segundos antes de chamar metodo Random
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 3000);
    }

    //Cronometro
    CountDownTimer countDownTimer = new CountDownTimer(31000, 1000) {

        public void onTick(long millisUntilFinished) {
            cronometro.setText(" " + millisUntilFinished / 1000);
        }

        public void onFinish() {
            tempo();
        }
    };

    public void jogo() {

        // contador para finalizar a activity
        fim += 1;

            //libera botões
            botao1.setEnabled(true);
            botao2.setEnabled(true);
            botao3.setEnabled(true);

            //inicia cronometro
            countDownTimer.start();

            if (resultado == 0 || resultado == 6 || resultado == 12 || resultado == 18 || resultado == 24) {

                if (resultado == 0) {
                    botao1.setText("200");
                    botao2.setText("100");
                    botao3.setText("250");
                }if (resultado == 6){
                    botao1.setText("Abel");
                    botao2.setText("Caim");// 2 botão
                    botao3.setText("Sete");
                }
                if (resultado == 12){
                    botao1.setText("Saul");
                    botao2.setText("Davi");
                    botao3.setText("Roboão");
                }
                if (resultado == 18){
                    botao1.setText("Lameuqe");
                    botao2.setText("Balaão");
                    botao3.setText("Sansão");
                }
                if (resultado == 24){
                    botao1.setText("Zacarias");
                    botao2.setText("Zaquel");
                    botao3.setText("Ezequiel");
                }
                botao1.setBackgroundResource(R.color.colorPrimary);
                botao1.setBackgroundResource(R.drawable.botao_arredondado);
                botao2.setBackgroundResource(R.color.colorPrimary);
                botao2.setBackgroundResource(R.drawable.botao_arredondado);
                botao3.setBackgroundResource(R.color.colorPrimary);
                botao3.setBackgroundResource(R.drawable.botao_arredondado);

                botao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao1.setBackgroundResource(R.color.vermelho);
                        botao1.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao2.setBackgroundResource(R.color.verde);
                        botao2.setBackgroundResource(R.drawable.botao_arredondado_verde);
                        pontos += 10;
                        geral.setPonto(pontos);
                        ponto.setText(" " + geral.getPonto());
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemAcerto();
                    }
                });
                botao3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao3.setBackgroundResource(R.color.vermelho);
                        botao3.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });


            } else if (resultado == 1 || resultado == 7 || resultado == 13 || resultado == 19 || resultado == 25) {

                if (resultado == 1) {
                    botao1.setText("Davi");
                    botao2.setText("Josafá");
                    botao3.setText("Salomão");
                }
                if (resultado == 7){
                    botao1.setText("Multiplicação");
                    botao2.setText("cura"); // 3 botao
                    botao3.setText("Transformação");
                }
                if (resultado == 13){
                    botao1.setText("Saria");
                    botao2.setText("Sará");
                    botao3.setText("Sara");
                }
                if (resultado == 19){
                    botao1.setText("Esaú");
                    botao2.setText("Isaque");
                    botao3.setText("Jacó");
                }
                if (resultado == 25){
                    botao1.setText("Jonatas");
                    botao2.setText("Jó");
                    botao3.setText("Jonas");
                }
                botao1.setBackgroundResource(R.color.colorPrimary);
                botao1.setBackgroundResource(R.drawable.botao_arredondado);
                botao2.setBackgroundResource(R.color.colorPrimary);
                botao2.setBackgroundResource(R.drawable.botao_arredondado);
                botao3.setBackgroundResource(R.color.colorPrimary);
                botao3.setBackgroundResource(R.drawable.botao_arredondado);

                botao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao1.setBackgroundResource(R.color.vermelho);
                        botao1.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao2.setBackgroundResource(R.color.vermelho);
                        botao2.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao3.setBackgroundResource(R.color.verde);
                        botao3.setBackgroundResource(R.drawable.botao_arredondado_verde);
                        pontos += 10;
                        geral.setPonto(pontos);
                        ponto.setText(" " + geral.getPonto());
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemAcerto();
                    }
                });


            } else if (resultado == 2 || resultado == 8 || resultado == 14 || resultado == 20 || resultado == 26) {
                if (resultado == 2){
                botao1.setText("Golias");
                botao2.setText("Abimeleque");
                botao3.setText("Sansão");
            }
            if (resultado == 8) {
                botao1.setText("10");
                botao2.setText("11"); // 3 botao
                botao3.setText("12");
            }
            if (resultado == 14){
                botao1.setText("Moisés");
                botao2.setText("Arão"); // 3 botao
                botao3.setText("Deus");
            }
            if (resultado == 20){
                botao1.setText("Goliam");
                botao2.setText("Golião");
                botao3.setText("Golias");
            }
            if (resultado == 26){
                botao1.setText("Noé");
                botao2.setText("Enoque");
                botao3.setText("Adão");
            }
                botao1.setBackgroundResource(R.color.colorPrimary);
                botao1.setBackgroundResource(R.drawable.botao_arredondado);
                botao2.setBackgroundResource(R.color.colorPrimary);
                botao2.setBackgroundResource(R.drawable.botao_arredondado);
                botao3.setBackgroundResource(R.color.colorPrimary);
                botao3.setBackgroundResource(R.drawable.botao_arredondado);

                botao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao1.setBackgroundResource(R.color.vermelho);
                        botao1.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao2.setBackgroundResource(R.color.vermelho);
                        botao2.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao3.setBackgroundResource(R.color.verde);
                        botao3.setBackgroundResource(R.drawable.botao_arredondado_verde);
                        pontos += 10;
                        geral.setPonto(pontos);
                        ponto.setText(" " + geral.getPonto());
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemAcerto();
                    }
                });


            } else if (resultado == 3 || resultado == 9 || resultado == 15 || resultado == 21 || resultado == 27) {
                if (resultado == 3) {
                    botao1.setText("Sangue");
                    botao2.setText("Piolho");
                    botao3.setText("Trevas");
                }
                if (resultado == 9){
                    botao1.setText("Varoa");
                    botao2.setText("Ana"); // 1 botao
                    botao3.setText("Eva");
                }
                if (resultado == 15){
                    botao1.setText("Abel");
                    botao2.setText("Caim");
                    botao3.setText("Sete");
                }
                if (resultado == 21){
                    botao1.setText("Noé");
                    botao2.setText("Naum");
                    botao3.setText("Neemias");
                }
                if (resultado == 27){
                    botao1.setText("Grande peixe");
                    botao2.setText("Baleia");
                    botao3.setText("Orca");
                }
                botao1.setBackgroundResource(R.color.colorPrimary);
                botao1.setBackgroundResource(R.drawable.botao_arredondado);
                botao2.setBackgroundResource(R.color.colorPrimary);
                botao2.setBackgroundResource(R.drawable.botao_arredondado);
                botao3.setBackgroundResource(R.color.colorPrimary);
                botao3.setBackgroundResource(R.drawable.botao_arredondado);

                botao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao1.setBackgroundResource(R.color.verde);
                        botao1.setBackgroundResource(R.drawable.botao_arredondado_verde);
                        pontos += 10;
                        geral.setPonto(pontos);
                        ponto.setText(" " + geral.getPonto());
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemAcerto();
                    }
                });
                botao2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao2.setBackgroundResource(R.color.vermelho);
                        botao2.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao3.setBackgroundResource(R.color.vermelho);
                        botao3.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });


            }else if (resultado == 4 || resultado == 10 || resultado == 16 || resultado == 22 || resultado == 28) {

                if (resultado == 4) {
                    botao1.setText("José");
                    botao2.setText("Abraão");
                    botao3.setText("Jacó");
                }
                if (resultado == 10){
                    botao1.setText("Ananias");
                    botao2.setText("Zacarias"); // 2 botao
                    botao3.setText("Malaquias");
                }
                if (resultado == 16){
                    botao1.setText("Abel");
                    botao2.setText("Sete");
                    botao3.setText("Caim");
                }
                if (resultado == 22){
                    botao1.setText("Davi");
                    botao2.setText("Daniel");
                    botao3.setText("Dario");
                }
                if (resultado == 28){
                    botao1.setText("Jacó");
                    botao2.setText("Jó");
                    botao3.setText("Jairo");
                }

                botao1.setBackgroundResource(R.color.colorPrimary);
                botao1.setBackgroundResource(R.drawable.botao_arredondado);
                botao2.setBackgroundResource(R.color.colorPrimary);
                botao2.setBackgroundResource(R.drawable.botao_arredondado);
                botao3.setBackgroundResource(R.color.colorPrimary);
                botao3.setBackgroundResource(R.drawable.botao_arredondado);

                botao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao1.setBackgroundResource(R.color.vermelho);
                        botao1.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao2.setBackgroundResource(R.color.verde);
                        botao2.setBackgroundResource(R.drawable.botao_arredondado_verde);
                        pontos += 10;
                        geral.setPonto(pontos);
                        ponto.setText(" " + geral.getPonto());
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemAcerto();
                    }
                });
                botao3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao3.setBackgroundResource(R.color.vermelho);
                        botao3.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });


            }else if (resultado == 5 || resultado == 11 || resultado == 17 || resultado == 23 || resultado == 29) {
                if (resultado == 5) {
                    botao1.setText("Isaque");
                    botao2.setText("Abraão");
                    botao3.setText("Ismael");
                }
                if (resultado == 11){
                    botao1.setText("40");
                    botao2.setText("120"); // 1 botao
                    botao3.setText("150");
                }
                if (resultado == 17){
                    botao1.setText("Judas");
                    botao2.setText("Lucas");
                    botao3.setText("Tiago");
                }
                if (resultado == 23){
                    botao1.setText("Moisés");
                    botao2.setText("José");
                    botao3.setText("Josué");
                }
                if (resultado == 29){
                    botao1.setText("Belém");
                    botao2.setText("Nazaré");
                    botao3.setText("Egito");
                }
                botao1.setBackgroundResource(R.color.colorPrimary);
                botao1.setBackgroundResource(R.drawable.botao_arredondado);
                botao2.setBackgroundResource(R.color.colorPrimary);
                botao2.setBackgroundResource(R.drawable.botao_arredondado);
                botao3.setBackgroundResource(R.color.colorPrimary);
                botao3.setBackgroundResource(R.drawable.botao_arredondado);

                botao1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao1.setBackgroundResource(R.color.verde);
                        botao1.setBackgroundResource(R.drawable.botao_arredondado_verde);
                        pontos += 10;
                        geral.setPonto(pontos);
                        ponto.setText(" " + geral.getPonto());
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemAcerto();
                    }
                });
                botao2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao2.setBackgroundResource(R.color.vermelho);
                        botao2.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
                botao3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        botao3.setBackgroundResource(R.color.vermelho);
                        botao3.setBackgroundResource(R.drawable.botao_arredondado_vermelho);
                        botao1.setEnabled(false);
                        botao2.setEnabled(false);
                        botao3.setEnabled(false);
                        livro.setText(livros[resultado]);
                        menssagemErro();
                    }
                });
            }
         }
    }

