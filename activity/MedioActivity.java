package com.example.fa.jogo.activity;

import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fa.jogo.R;
import com.example.fa.jogo.classe.Geral;

import java.util.Random;

public class MedioActivity extends AppCompatActivity {

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
            "Complete: Nas parabolas de Jesus ele fala sobre o trigo e o?", "Qual apóstolo esteve em um naúfragio?", "Qual homem na Bíblia foi sepultado sem a cabeça?",
            "Qual profeta foi consolado por Gabriel?","A parabola das bodas está em que livro?", "Onde está escrito os dez mandamentos?",
            "Quem foi o sucessor de moisés?", "Quem era o pai da profetiza Ana?", "Ester era o que de mordecai?", "Qual rei foi repreendido pelo profeta Natã?",
            "A carta esrita de Claúdio a Felix se encontra em que livro?", "Quem era Hadassa?", "Qual o profeta que é referênciado como a voz que clama no deserto?",
            "Qual a profisão do apostolo Levi?", "Onde está o cantigo de Moisés?", "O Salmo 84 fala de quê?", "O que é oferta de manjares?",
            "Onde fala que Deus é amor?", "Onde está na Bíblia a penitência de Efraim?", "A rainha de sabá visitou qual rei de Israel?",
            "O que significa TEQUEL?", "Onde está a frase: Perseverai na oração..?", "O que significa PERES?", "A fé que vence o mundo está em que livro?",
            "Quem foi Gaio?", "Complete: Se alguém disser: Amo a Deus, e odeia a seu..?", "Quem foi o colega de prisão de Paulo?",
            "Complete: A mulher sábia edifica a sua..?", "Eliú era amigo de quem?", "Complete: Ninguém põem vinho novo em..?"
    };

    private int[] drawables = {
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,  R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao

    };
    private String[] livros = {"a", "Atos 27:31", "Mateus 14:11", "Daniel 10:11-12", "Mateus 22", "Êxodo 20", "Números 27:18", "Lucas 2:36",
            "Ester 2:7", "2samuel 12:7", "Atos 23:26-30", "Ester 2:7", "Maros 1:3", "Marcos 2:14", "Êxodo 15", "Salmo 84", "Ezequiel 46:15",
            "1João 4:7", "Isaias 28", "1Reis 10", "Daniel 5:27", "Colossenses 4:2", "Daniel 5:28", "1João 5", "3João 1", "1João 4:20",
            "Atos 16:19-26", "Provérbios 14;1", "Jó 35:1", "Marcos 2:22"

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
        alerta.setText("Parabéns você concluiu o modo Intermediário!");
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
    CountDownTimer countDownTimer = new CountDownTimer(21000, 1000) {

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

        if (resultado == 0 || resultado == 6 || resultado == 12 || resultado == 18 || resultado == 24 ||
                resultado == 30 || resultado == 36 || resultado == 42 || resultado == 48 || resultado == 54) {

            if (resultado == 0) {
                botao1.setText("azeite");
                botao2.setText("joio");
                botao3.setText("pão");
            }if (resultado == 6){
                botao1.setText("Calebe");
                botao2.setText("Josué");// 2 botão
                botao3.setText("Arão");
            }
            if (resultado == 12){
                botao1.setText("Zacarias");
                botao2.setText("João Batista");
                botao3.setText("José");
            }
            if (resultado == 18){
                botao1.setText("Ezequiel");
                botao2.setText("Isaias");
                botao3.setText("Daniel");
            }
            if (resultado == 24){
                botao1.setText("Escriba");
                botao2.setText("Presbítero");
                botao3.setText("Sacedorte");
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


        } else if (resultado == 1 || resultado == 7 || resultado == 13 || resultado == 19 || resultado == 25 || resultado == 31 ||
                resultado == 37 || resultado == 43 || resultado == 49 || resultado == 55) {

            if (resultado == 1) {
                botao1.setText("João");
                botao2.setText("Marcos");
                botao3.setText("Paulo");
            }
            if (resultado == 7){
                botao1.setText("Zedequias");
                botao2.setText("Jonã"); // 3 botao
                botao3.setText("Fanuel");
            }
            if (resultado == 13){
                botao1.setText("Pescador");
                botao2.setText("Padeiro");
                botao3.setText("Cobrador");
            }
            if (resultado == 19){
                botao1.setText("Nabucodonosor");
                botao2.setText("Davi");
                botao3.setText("salomão");
            }
            if (resultado == 25){
                botao1.setText("Próximo");
                botao2.setText("Filho");
                botao3.setText("Irmão");
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


        } else if (resultado == 2 || resultado == 8 || resultado == 14 || resultado == 20 || resultado == 26 || resultado == 32 ||
                resultado == 38 || resultado == 44 || resultado == 50 || resultado == 56) {
            if (resultado == 2){
                botao1.setText("José");
                botao2.setText("Paulo");
                botao3.setText("João Batista");
            }
            if (resultado == 8) {
                botao1.setText("Sobrinha");
                botao2.setText("Irmã"); // 3 botao
                botao3.setText("prima");
            }
            if (resultado == 14){
                botao1.setText("Malaquias");
                botao2.setText("Números");
                botao3.setText("Êxodo");
            }
            if (resultado == 20){
                botao1.setText("Dividido");
                botao2.setText("Você morrerá");
                botao3.setText("Pesado foste");
            }
            if (resultado == 26){
                botao1.setText("Barnabé");
                botao2.setText("Timóteo");
                botao3.setText("Silas");
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


        } else if (resultado == 3 || resultado == 9 || resultado == 15 || resultado == 21 || resultado == 27 || resultado == 33 ||
                resultado == 39 || resultado == 45 || resultado == 51 || resultado == 57) {
            if (resultado == 3) {
                botao1.setText("Daniel");
                botao2.setText("Paulo");
                botao3.setText("Jacó");
            }
            if (resultado == 9){
                botao1.setText("Davi");
                botao2.setText("Saul"); // 1 botao
                botao3.setText("Salomão");
            }
            if (resultado == 15){
                botao1.setText("Saudades");
                botao2.setText("Dízimos");
                botao3.setText("Perdão");
            }
            if (resultado == 21){
                botao1.setText("Colossenses");
                botao2.setText("Atos");
                botao3.setText("Filemon");
            }
            if (resultado == 27){
                botao1.setText("Casa");
                botao2.setText("Fé");
                botao3.setText("Família");
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


        }else if (resultado == 4 || resultado == 10 || resultado == 16 || resultado == 22 || resultado == 28 || resultado == 34 ||
                resultado == 40 || resultado == 46 || resultado == 52 || resultado == 58) {

            if (resultado == 4) {
                botao1.setText("Apocalipse");
                botao2.setText("Mateus");
                botao3.setText("Ezequiel");
            }
            if (resultado == 10){
                botao1.setText("Marcos");
                botao2.setText("Atos"); // 2 botao
                botao3.setText("Apocalipse");
            }
            if (resultado == 16){
                botao1.setText("Oferta pascal");
                botao2.setText("Oferta diária");
                botao3.setText("Oferta anual");
            }
            if (resultado == 22){
                botao1.setText("Uma cidade");
                botao2.setText("Dividido foi");
                botao3.setText("Afastado foi");
            }
            if (resultado == 28){
                botao1.setText("Jacó");
                botao2.setText("Jó");
                botao3.setText("Abraão");
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


        }else if (resultado == 5 || resultado == 11 || resultado == 17 || resultado == 23 || resultado == 29 || resultado == 35 ||
                resultado == 41 || resultado == 47 || resultado == 53 || resultado == 59) {
            if (resultado == 5) {
                botao1.setText("Êxodo");
                botao2.setText("Levitico");
                botao3.setText("Números");
            }
            if (resultado == 11){
                botao1.setText("Ester");
                botao2.setText("Débora"); // 1 botao
                botao3.setText("Miriã");
            }
            if (resultado == 17){
                botao1.setText("1João");
                botao2.setText("2João");
                botao3.setText("3João");
            }
            if (resultado == 23){
                botao1.setText("1João");
                botao2.setText("2Pedro");
                botao3.setText("Hebreus");
            }
            if (resultado == 29){
                botao1.setText("Odres velhos");
                botao2.setText("Odres novos");
                botao3.setText("jarro velho");
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

