package it.find.com.call.presenter.data;

/**
 * Created by Bruno on 19-Jan-18.
 */

public class User {
    private String nome;
    private int faltas;
    private int atrasos;
    private boolean warn;

    public boolean isWarn() {
        return warn;
    }

    public String getNome() {
        return nome;
    }

    public int getFaltas() {
        return faltas;
    }

    public int getAtrasos() {
        return atrasos;
    }

    public User(String nome, int faltas, int atrasos, boolean warn) {
        this.nome = nome;
        this.faltas = faltas;
        this.atrasos = atrasos;
        this.warn = warn;
    }
}
