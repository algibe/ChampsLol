package com.example.albert.championsselect;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.albert.lolchamp.LOLChamp;

public class PreviewChamp extends AppCompatActivity {

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

        Toast.makeText(this, "Ataque : " + ataque +
                "\nDefensa : " + defensa +
                "\nHabilidad : " + habilidad +
                "\nDificultad : " + dificultad, Toast.LENGTH_LONG).show();

        lolChamp = (LOLChamp)findViewById(R.id.imageChamp);
        setTitle(nameChamp);

        String name = "ic_"+nameChamp.toLowerCase();
        int id = getResources().getIdentifier(name, "mipmap", getPackageName());
        Drawable drawable = getResources().getDrawable(id);

        lolChamp.setImg(drawable);
        lolChamp.setNameChamp(nameChamp + " - ");

        lolChamp.setAtaque(ataque);
        lolChamp.setDefensa(defensa);
        lolChamp.setDificultad(dificultad);
        lolChamp.setHabilidad(habilidad);
    }
}
