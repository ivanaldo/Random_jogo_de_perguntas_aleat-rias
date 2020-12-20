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

public class DificilActivity extends AppCompatActivity {

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
            "Quantos anos viveu Noé?", "Quantos anos viveu Adão?", "Quem foi o homem mais velho mencionado na Bíblia?",
            "Quem foi o fundador de Babel?", "Quem foi o bisavô de Davi?", "Quantos anos morreu Isaque?", "quantos anos viveu Abraão?",
            "Quantos anos  viveu Jacó?", "Como se chamava Hebrom no tempo dos patriarcas?", "Quantos anos reinou Davi em Jerusalém?",
            "Minha maior vergonha se deu em três epísodios, quem sou eu?", "Fui perseguidor e depois perseguido, fui assassino e depois acabei assassinado, quem sou eu?",
            "Qual era a profissão de Raquel?", "Quem era o marido da profetiza Débora?",  "Por negligenciar a repreensão de meus filhos meus pecados jamais serão perdoados, quem sou eu?",
            "Quem segundo a bíblia foi teletransportado?", "Quem ficou invisível por um tempo?", "Quem foi reconhecido por criar maquinas de armas para o exercito de Israel?",
            "Ao meu pedido o sol retrocedeu 10 graus?", "Qual a única mulher na Bíblia que tem sua real idade revelada?", "Qual a única mulher que entrou na lista dos pais da fé?",
            "Quem foi a prima de Jesus?", "Quantos morreram na torre de siloé?", "Quantos foram os sacerdotes que Doegue matou por ordem de Saul?",
            "Quem segundo Jesus foi morto entre o altar e o templo?", "Quem era o sumo sacerdote no tempo do profeta Ageu?", "Como se Chamava o pai do profeta Joel?",
            "Qual desses reis foi do tempo do profeta Oséias?", "Qual desses profetas pregou somente contra Edom?", "Sofonias foi profeta no tempo de qual rei?"

};

    private int[] drawables = {
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao, R.drawable.padrao,
            R.drawable.padrao, R.drawable.padrao

    };
    private String[] livros = {"Gênesis 9:29", "Gênesis 5:5", "Gênesis 5:27", "Gênesis 10:8-10", "Rute 4:21-22", "Gênesis 35:28", "Gênesis 25:7",
            "Gênesis 47:28", "Gênesis 23:2", "1Reis 2:11", "Mateus 26:75", "Atos 8:3; 2Timóteo 4:6-8", "Gênesis 29:9", "Juízes 4:4", "1Samuel 3:12-14",
            "Atos 8:39-40", "Atos 12:7-10", "2Crônicas 26:14-15", "2Reis 20:9-12", "Gênesis 23:1", "Hebreus 11:11", "Lucas 1:30-36", "Lucas 13:4",
            "1Samuel 22:18", "Lucas 11:51", "Ageu 1:1", "Joel 1:1", "Oséias 1:1", "Obadias", "Sofonias 1:1"

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
        alerta.setText("Parabéns você concluiu o modo Avançado!");
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
    CountDownTimer countDownTimer = new CountDownTimer(11000, 1000) {

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
                botao1.setText("960");
                botao2.setText("950");
                botao3.setText("930");
            }if (resultado == 6){
                botao1.setText("180");
                botao2.setText("175");// 2 botão
                botao3.setText("150");
            }
            if (resultado == 12){
                botao1.setText("Profetiza");
                botao2.setText("Pastora");
                botao3.setText("Parteira");
            }
            if (resultado == 18){
                botao1.setText("Josué");
                botao2.setText("Ezequias");
                botao3.setText("Moisés");
            }
            if (resultado == 24){
                botao1.setText("Abiatar");
                botao2.setText("Zacarias");
                botao3.setText("Aimeleque");
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
                botao1.setText("935");
                botao2.setText("932");
                botao3.setText("930");
            }
            if (resultado == 7){
                botao1.setText("145");
                botao2.setText("157"); // 3 botao
                botao3.setText("147");
            }
            if (resultado == 13){
                botao1.setText("Baraque");
                botao2.setText("Abinoão");
                botao3.setText("Lapidote");
            }
            if (resultado == 19){
                botao1.setText("Rebeca");
                botao2.setText("Raquel");
                botao3.setText("Sara");
            }
            if (resultado == 25){
                botao1.setText("Abiatar");
                botao2.setText("Abimeleque");
                botao3.setText("Josué");
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
                botao1.setText("Lameque");
                botao2.setText("Adão");
                botao3.setText("Matusalém");
            }
            if (resultado == 8) {
                botao1.setText("Orebe");
                botao2.setText("Sinai"); // 3 botao
                botao3.setText("Quiriate-Arba");
            }
            if (resultado == 14){
                botao1.setText("Davi");
                botao2.setText("Levi");
                botao3.setText("Eli");
            }
            if (resultado == 20){
                botao1.setText("Ana");
                botao2.setText("Débora");
                botao3.setText("Sara");
            }
            if (resultado == 26){
                botao1.setText("Lemuel");
                botao2.setText("Label");
                botao3.setText("Petuel");
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
                botao1.setText("Ninrode");
                botao2.setText("Ninroque");
                botao3.setText("Ninrote");
            }
            if (resultado == 9){
                botao1.setText("33");
                botao2.setText("40"); // 1 botao
                botao3.setText("37");
            }
            if (resultado == 15){
                botao1.setText("Filipe");
                botao2.setText("Paulo");
                botao3.setText("Pedro");
            }
            if (resultado == 21){
                botao1.setText("Isabel");
                botao2.setText("Maria");
                botao3.setText("Marta");
            }
            if (resultado == 27){
                botao1.setText("Ezequias");
                botao2.setText("Manassés");
                botao3.setText("Jeoaquim");
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
                botao1.setText("Jessé");
                botao2.setText("Boaz");
                botao3.setText("Obede");
            }
            if (resultado == 10){
                botao1.setText("Paulo");
                botao2.setText("Pedro"); // 2 botao
                botao3.setText("Judas");
            }
            if (resultado == 16){
                botao1.setText("Jaão");
                botao2.setText("Pedro");
                botao3.setText("Filipe");
            }
            if (resultado == 22){
                botao1.setText("16");
                botao2.setText("18");
                botao3.setText("22");
            }
            if (resultado == 28){
                botao1.setText("Ageu");
                botao2.setText("Obadias");
                botao3.setText("Habacuque");
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
                botao1.setText("180");
                botao2.setText("150");
                botao3.setText("145");
            }
            if (resultado == 11){
                botao1.setText("Paulo");
                botao2.setText("Judas"); // 1 botao
                botao3.setText("Pílatos");
            }
            if (resultado == 17){
                botao1.setText("Uzias");
                botao2.setText("Davi");
                botao3.setText("Salomão");
            }
            if (resultado == 23){
                botao1.setText("85");
                botao2.setText("45");
                botao3.setText("65");
            }
            if (resultado == 29){
                botao1.setText("Josias");
                botao2.setText("Jotão");
                botao3.setText("Urias");
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

