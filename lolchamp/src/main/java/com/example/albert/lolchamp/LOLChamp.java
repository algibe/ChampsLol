package com.example.albert.lolchamp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class LOLChamp extends LinearLayout implements View.OnClickListener {

    private ImageView imgChamp;
    private TextView nameChamp;

    private SeekBar seekBarbAtaque;
    private SeekBar seekBarDefensa;
    private SeekBar seekBarHabilidad;
    private SeekBar seekBarDificultad;

    private Button add;

    private EditText idUser;

    private OnAddChampClickedListener mCallback = null;

    public LOLChamp(Context context) {
        super(context);
        init(null, 0);
    }

    public LOLChamp(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public LOLChamp(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        LayoutInflater inflater =
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.lolchamp,this);

        imgChamp = (ImageView)findViewById(R.id.champSelect);
        nameChamp = (TextView)findViewById(R.id.nameChamp);

        seekBarbAtaque = (SeekBar)findViewById(R.id.seekBarAtaque);
        seekBarDefensa = (SeekBar)findViewById(R.id.seekBarDefensa);
        seekBarHabilidad = (SeekBar)findViewById(R.id.seekBarHabilidad);
        seekBarDificultad = (SeekBar)findViewById(R.id.seekBarDificultad);

        add = (Button)findViewById(R.id.addChamp);

        idUser = (EditText)findViewById(R.id.idUser);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            seekBarbAtaque.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#A33D34")));
            seekBarDefensa.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#506B09")));
            seekBarHabilidad.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#4A7BA6")));
            seekBarDificultad.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#79639C")));
        }

        //Load attributes
        TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LOLChamp, defStyle, 0);

        Drawable s = a.getDrawable(R.styleable.LOLChamp_imgChamp);
        if (s != null) { setImg(s);}

        String n = a.getString(R.styleable.LOLChamp_nameChamp);
        if (s != null) { setImg(s);}

        int ataque = a.getInt(1, R.styleable.LOLChamp_valueAtaque);
        if (s != null) { setAtaque(ataque);}

        int defensa = a.getInt(1,R.styleable.LOLChamp_valueDefensa);
        if (s != null) { setDefensa(defensa);}

        int habilidad = a.getInt(1,R.styleable.LOLChamp_valueHabilidad);
        if (s != null) { setHabilidad(habilidad);}

        int dificultad = a.getInt(1,R.styleable.LOLChamp_valueDificultad);
        if (s != null) { setDefensa(dificultad);}

        a.recycle();

        add.setOnClickListener(this);

    }

    public interface  OnAddChampClickedListener {
        public void onAddChampClicked(LOLChamp source, String idUser,int ataque,int defensa,int habilidad,int dificultad);
    }

    public void setOnAddChampClickedListener(OnAddChampClickedListener listener ){
        mCallback = listener;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addChamp){
            if(mCallback != null){
                mCallback.onAddChampClicked(this,idUser.getText().toString(),seekBarbAtaque.getProgress(),seekBarDefensa.getProgress(),seekBarHabilidad.getProgress(),seekBarDificultad.getProgress());
            }
        }
    }

    public void setImg(Drawable champ){
        imgChamp.setImageDrawable(champ);
        invalidate();
        requestLayout();
    }

    public void setNameChamp(String name){
        nameChamp.setText(name);
        invalidate();
        requestLayout();
    }

    public void setAtaque(int ataque){
        seekBarbAtaque.setProgress(ataque);
        invalidate();
        requestLayout();
    }

    public void setHabilidad(int habilidad){
        seekBarHabilidad.setProgress(habilidad);
        invalidate();
        requestLayout();
    }

    public void setDefensa(int defensa){
        seekBarDefensa.setProgress(defensa);
        invalidate();
        requestLayout();
    }

    public void setDificultad(int dificultad){
        seekBarDificultad.setProgress(dificultad);
        invalidate();
        requestLayout();
    }



}
