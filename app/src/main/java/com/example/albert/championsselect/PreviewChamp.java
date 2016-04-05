package com.example.albert.championsselect;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.example.albert.lolchamp.LOLChamp;

public class PreviewChamp extends AppCompatActivity {

    LOLChamp lolChamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_champ);

        Bundle bundle = getIntent().getExtras();

        String nameChamp = bundle.getString(SelectionChamp.nameChamp);

        lolChamp = (LOLChamp)findViewById(R.id.imageChamp);
        setTitle(nameChamp);

        String name = "ic_"+nameChamp.toLowerCase();
        int id = getResources().getIdentifier(name, "mipmap", getPackageName());
        Drawable drawable = getResources().getDrawable(id);

        lolChamp.setImg(drawable);
        lolChamp.setNameChamp(nameChamp + " - ");
    }
}
