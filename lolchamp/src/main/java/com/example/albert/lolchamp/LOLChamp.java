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

        //Modificar colores de los seekbar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            seekBarbAtaque.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#A33D34")));
            seekBarDefensa.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#506B09")));
            seekBarHabilidad.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#4A7BA6")));
            seekBarDificultad.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#79639C")));
        }

        //Load attributes
        TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.LOLChamp, defStyle, 0);

        //Insertar la imagen en la imageView
        Drawable img = a.getDrawable(R.styleable.LOLChamp_imgChamp);
        if (img != null) { setImg(img);}

        //Nombre del campeon seleccionado
        String nameChamp = a.getString(R.styleable.LOLChamp_nameChamp);
        if (nameChamp != null) { setNameChamp(nameChamp);}

        //Nombre del usuario
        String playerName = a.getString(R.styleable.LOLChamp_setNameUser);
        if(playerName != null){ setUserName(playerName);}

        //Modificar o insertar el placeholder del edittext
        String hintText = a.getString(R.styleable.LOLChamp_setHint);
        if(hintText != null){ setHintText(hintText);}


        //Valor del seekbar de ataque
        int ataque = a.getInt(R.styleable.LOLChamp_valueAtaque,0);
        if (ataque >= 0) { setAtaque(ataque);}

        //Valor del seekbar de defensa
        int defensa = a.getInt(R.styleable.LOLChamp_valueDefensa,0);
        if (defensa >= 0) { setDefensa(defensa);}

        //Valor del seekbar de habilidad
        int habilidad = a.getInt(R.styleable.LOLChamp_valueHabilidad,0);
        if (habilidad >= 0) { setHabilidad(habilidad);}

        //Valor del seekbar de dificultad
        int dificultad = a.getInt(R.styleable.LOLChamp_valueDificultad,0);
        if (dificultad >= 0) { setDificultad(dificultad);}


        //Oculta o mostrar el boton de añadir un nuevo campeon
        boolean visible = a.getBoolean(R.styleable.LOLChamp_buttonVisible,true);
        setVisible(visible);

        //Deshabilitar o habilitar el edittext
        boolean isEditable = a.getBoolean(R.styleable.LOLChamp_isEditable,true);
        setEditable(isEditable);


        //Hacer que el seekbar de ataque no sea modificable
        boolean editAtaque = a.getBoolean(R.styleable.LOLChamp_editAtaque,true);
        editAtaque(editAtaque);

        //Hacer que el seekbar de defensa no sea modificable
        boolean editDefensa = a.getBoolean(R.styleable.LOLChamp_editDefensa,true);
        editDefensa(editDefensa);

        //Hacer que el seekbar de habilidad no sea modificable
        boolean editHabilidad = a.getBoolean(R.styleable.LOLChamp_editHabilidad,true);
        editHabilidad(editHabilidad);

        //Hacer que el seekbar de dificultad no sea modificable
        boolean editDificultad = a.getBoolean(R.styleable.LOLChamp_editDificultad,true);
        editDificultad(editDificultad);

        a.recycle();

        //Añadir al boton de añadir un campeon un evento
        add.setOnClickListener(this);

    }

    //Metodos que gestionan el evento del boton addChamp
    public interface  OnAddChampClickedListener {
        public void onAddChampClicked(LOLChamp source,
                                      int imgChamp,
                                      String idUser,
                                      int ataque,
                                      int defensa,
                                      int habilidad,
                                      int dificultad,
                                      String name);
    }

    public void setOnAddChampClickedListener(OnAddChampClickedListener listener ){
        mCallback = listener;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.addChamp){
            if(mCallback != null){
                mCallback.onAddChampClicked(this,
                        imgChamp.getId(),
                        idUser.getText().toString(),
                        seekBarbAtaque.getProgress(),
                        seekBarDefensa.getProgress(),
                        seekBarHabilidad.getProgress(),
                        seekBarDificultad.getProgress(),
                        nameChamp.getText().toString());
            }
        }
    }

    //Insertar la imagen en la imageView
    public void setImg(Drawable champ){
        imgChamp.setImageDrawable(champ);
        invalidate();
        requestLayout();
    }

    //Nombre del campeona
    public void setNameChamp(String name){
        nameChamp.setText(name);
        invalidate();
        requestLayout();
    }

    //Nombre de usuario
    public void setUserName(String userName){
        idUser.setText(userName);
        invalidate();
        requestLayout();
    }

    //Modificar o insertar el placeholder del edittext
    public void setHintText(String hintText){
        idUser.setHint(hintText);
        invalidate();
        requestLayout();
    }


    //Valor del seekbar de ataque
    public void setAtaque(int ataque){
        seekBarbAtaque.setProgress(ataque);
        invalidate();
        requestLayout();
    }

    //Valor del seekbar de habilidad
    public void setHabilidad(int habilidad){
        seekBarHabilidad.setProgress(habilidad);
        invalidate();
        requestLayout();
    }

    //Valor del seekbar de defensa
    public void setDefensa(int defensa){
        seekBarDefensa.setProgress(defensa);
        invalidate();
        requestLayout();
    }

    //Valor del seekbar de dificultad
    public void setDificultad(int dificultad){
        seekBarDificultad.setProgress(dificultad);
        invalidate();
        requestLayout();
    }


    //Oculta o mostrar el boton de añadir un nuevo campeon
    public void setVisible(boolean visible){
        if(!visible){
            add.setVisibility(GONE);
        } else {
            add.setVisibility(VISIBLE);
        }
        invalidate();
        requestLayout();
    }

    //Deshabilitar o habilitar el edittext
    public void setEditable(boolean editable){
        idUser.setEnabled(editable);
        invalidate();
        requestLayout();
    }


    //Hacer que el seekbar de ataque se pueda modificar o no
    public void editAtaque(boolean ataque){
        if(!ataque){
            seekBarbAtaque.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                int originalProgress;

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    originalProgress = seekBar.getProgress();
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                    if (fromUser) {
                        seekBar.setProgress(originalProgress);
                    }
                }
            });
        }
        invalidate();
        requestLayout();
    }

    //Hacer que el seekbar de defensa se pueda modificar o no
    public void editDefensa(boolean defensa){
        if(!defensa){
            seekBarDefensa.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                int originalProgress;

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    originalProgress = seekBar.getProgress();
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                    if (fromUser) {
                        seekBar.setProgress(originalProgress);
                    }
                }
            });
        }
        invalidate();
        requestLayout();
    }

    //Hacer que el seekbar de habilidad se pueda modificar o no
    public void editHabilidad(boolean habilidad){
        if(!habilidad){
            seekBarHabilidad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                int originalProgress;

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    originalProgress = seekBar.getProgress();
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                    if (fromUser) {
                        seekBar.setProgress(originalProgress);
                    }
                }
            });
        }
        invalidate();
        requestLayout();
    }

    //Hacer que el seekbar de dificultad se pueda modificar o no
    public void editDificultad(boolean dificultad){
        if(!dificultad){
            seekBarDificultad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                int originalProgress;

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    originalProgress = seekBar.getProgress();
                }

                @Override
                public void onProgressChanged(SeekBar seekBar, int arg1, boolean fromUser) {
                    if (fromUser) {
                        seekBar.setProgress(originalProgress);
                    }
                }
            });
        }
        invalidate();
        requestLayout();
    }

}
