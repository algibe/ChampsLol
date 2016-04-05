package com.example.albert.championsselect;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.albert.lolchamp.LOLChamp;

public class MainActivity extends AppCompatActivity {

    LOLChamp lolChamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        lolChamp = (LOLChamp)findViewById(R.id.imageChamp);
        Drawable myIcon = getResources().getDrawable( R.mipmap.ic_thresh );
        lolChamp.setImg(myIcon);
    }
}
