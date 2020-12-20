package com.example.fa.jogo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fa.jogo.R;
import com.example.fa.jogo.classe.Geral;


import java.util.List;


public class AdapterAnuncios extends RecyclerView.Adapter<AdapterAnuncios.MyViewHolder> {

    private List<Geral> jogador;
    private Context pontos;


    public AdapterAnuncios(List<Geral> jogador, Context pontos) {
        this.jogador = jogador;
        this.pontos = pontos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_anuncio, parent, false);
        return new MyViewHolder( item );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Geral joga = jogador.get(position);
        holder.nome.setText( joga.getNome() );
        holder.pontos.setText( joga.getPonto() );

    }

    @Override
    public int getItemCount() {
        return jogador.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView pontos;


        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textNome);
            pontos = itemView.findViewById(R.id.textPontos);

        }
    }

}
