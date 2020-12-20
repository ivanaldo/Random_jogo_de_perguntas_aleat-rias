package com.example.fa.jogo.classe;

import com.example.fa.jogo.helper.ConfiguracaoFirebase;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.storage.StorageReference;

import java.io.Serializable;
import java.util.List;

public class Geral implements Serializable {

    private String idAnuncio;
    private String nome;
    private int ponto;


    /*public Geral() {
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_anuncios");
        setIdAnuncio( anuncioRef.push().getKey() );

    }

    public void salvar(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_anuncios");

        anuncioRef.child(idUsuario)
                .child(getIdAnuncio())
                .setValue(this);

        salvarAnuncioPublico();

    }

    public void salvarAnuncioPublico(){

        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("anuncios");

        anuncioRef.child( getNome() )
                .child(String.valueOf(getPonto()))
                .child( getIdAnuncio() )
                .setValue(this);

    }

    public void remover(){

        String idUsuario = ConfiguracaoFirebase.getIdUsuario();
        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("meus_anuncios")
                .child( idUsuario )
                .child( getIdAnuncio() );

        anuncioRef.removeValue();
        removerAnuncioPublico();

    }

    public void removerAnuncioPublico() {

        DatabaseReference anuncioRef = ConfiguracaoFirebase.getFirebase()
                .child("anuncios")
                .child(getNome())
                .child(String.valueOf(getPonto()))
                .child(getIdAnuncio());

        anuncioRef.removeValue();

        for (int i = 0; i < 7; i++) {
            StorageReference storageReference = ConfiguracaoFirebase.getFirebaseStorage()
                    .child("imagens")
                    .child("anuncios")
                    .child(getIdAnuncio())
                    .child("imagem" + i);
            storageReference.delete();
        }

    }*/

    public String getIdAnuncio() {
        return idAnuncio;
    }

    public void setIdAnuncio(String idAnuncio) {
        this.idAnuncio = idAnuncio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

}


