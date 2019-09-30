package com.example.whatsapp.model;

public class Conversa {
    private String titulo;
    private String previa;
    private String hora;
    private int qntMsgs;

    public Conversa(String titulo, String previa, String hora, int qntMsgs) {
        this.titulo = titulo;
        this.previa = previa;
        this.hora = hora;
        this.qntMsgs = qntMsgs;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrevia() {
        return previa;
    }

    public void setPrevia(String previa) {
        this.previa = previa;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getQntMsgs() {
        return qntMsgs;
    }

    public void setQntMsgs(int qntMsgs) {
        this.qntMsgs = qntMsgs;
    }
}
