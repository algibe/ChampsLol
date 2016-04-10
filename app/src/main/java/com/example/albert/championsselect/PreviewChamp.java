package com.example.albert.championsselect;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;


import com.example.albert.lolchamp.LOLChamp;

public class PreviewChamp extends AppCompatActivity implements LOLChamp.OnAddChampClickedListener{

    LOLChamp lolChamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_champ);

        Bundle bundle = getIntent().getExtras();

        String nameChamp = bundle.getString(SelectionChamp.nameChamp);
        int ataque = bundle.getInt(SelectionChamp.keyValueAtaque);
        int defensa = bundle.getInt(SelectionChamp.keyValueDefensa);
        int habilidad = bundle.getInt(SelectionChamp.keyValueHabilidad);
        int dificultad = bundle.getInt(SelectionChamp.keyValueDificultad);

        lolChamp = (LOLChamp)findViewById(R.id.imageChamp);
        setTitle(nameChamp);

        String name = "ic_"+nameChamp.toLowerCase();
        int id = getResources().getIdentifier(name, "mipmap", getPackageName());
        Drawable drawable = getResources().getDrawable(id);

        lolChamp.setImg(drawable);
        lolChamp.setNameChamp(nameChamp);

        //lolChamp.setAtaque(ataque);
        //lolChamp.setDefensa(defensa);
        //lolChamp.setDificultad(dificultad);
        //lolChamp.setHabilidad(habilidad);

        lolChamp.setOnAddChampClickedListener(this);
    }

    @Override
    public void onAddChampClicked(LOLChamp source, String idUser, int ataque, int defensa, int habilidad, int dificultad, String name) {

        /*Toast.makeText(this,"Id user : " + idUser +
                "\nName : " + name +
                "\nAtaque : " + ataque +
                "\nDefensa : " + defensa +
                "\nHabilidad : " + habilidad +
                "\nDificultad : " + dificultad ,Toast.LENGTH_LONG).show();*/

        Champion champion = new Champion();

        champion.setImg(name);
        champion.setIdUser(idUser);
        champion.setName(name);
        champion.setAtaque(ataque);
        champion.setDefensa(defensa);
        champion.setHabilidad(habilidad);
        champion.setDificultad(dificultad);

        LOLQuerys lolQuerys = new LOLQuerys();
        lolQuerys.insert(champion,SelectionChamp.lolDatabase);

        finish();

    }


}
