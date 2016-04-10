package com.example.albert.championsselect;

/**
 * Created by PCCom on 09/04/2016.
 */
public class Champion {

    public static final String TABLE_NAME = "Champ";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IMG = "img";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID_USER = "iduser";
    public static final String COLUMN_ATAQUE = "ataque";
    public static final String COLUMN_DEFENSA = "defensa";
    public static final String COLUMN_HABILIDAD = "habilidad";
    public static final String COLUMN_DIFICULTAD = "dificultad";

    private String img;
    private String name;
    private String idUser;
    private int ataque;
    private int defensa;
    private int habilidad;
    private int dificultad;

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getIdUser() {
        return idUser;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public int getDificultad() {
        return dificultad;
    }


    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
}
