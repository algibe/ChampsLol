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



        /*seekBarbAtaque.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
        });*/

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
        if (n != null) { setNameChamp(n);}

        String playerName = a.getString(R.styleable.LOLChamp_setNameUser);
        if(playerName != null){ setUserName(playerName);}


        int ataque = a.getInt(R.styleable.LOLChamp_valueAtaque,0);
        if (ataque >= 0) { setAtaque(ataque);}

        int defensa = a.getInt(R.styleable.LOLChamp_valueDefensa,0);
        if (defensa >= 0) { setDefensa(defensa);}

        int habilidad = a.getInt(R.styleable.LOLChamp_valueHabilidad,0);
        if (habilidad >= 0) { setHabilidad(habilidad);}

        int dificultad = a.getInt(R.styleable.LOLChamp_valueDificultad,0);
        if (dificultad >= 0) { setDificultad(dificultad);}


        boolean visible = a.getBoolean(R.styleable.LOLChamp_buttonVisible,true);
        setVisible(visible);

        boolean isEditable = a.getBoolean(R.styleable.LOLChamp_isEditable,true);
        setEditable(isEditable);


        boolean editAtaque = a.getBoolean(R.styleable.LOLChamp_editAtaque,true);
        editAtaque(editAtaque);

        boolean editDefensa = a.getBoolean(R.styleable.LOLChamp_editDefensa,true);
        editDefensa(editAtaque);

        boolean editHabilidad = a.getBoolean(R.styleable.LOLChamp_editHabilidad,true);
        editHabilidad(editHabilidad);

        boolean editDificultad = a.getBoolean(R.styleable.LOLChamp_editDificultad,true);
        editDificultad(editDificultad);



        a.recycle();

        add.setOnClickListener(this);

    }

    public interface  OnAddChampClickedListener {
        public void onAddChampClicked(LOLChamp source,
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
                        idUser.getText().toString(),
                        seekBarbAtaque.getProgress(),
                        seekBarDefensa.getProgress(),
                        seekBarHabilidad.getProgress(),
                        seekBarDificultad.getProgress(),
                        nameChamp.getText().toString());
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

    public void setUserName(String userName){
        idUser.setText(userName);
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


    public void setVisible(boolean visible){
        if(!visible){
            add.setVisibility(GONE);
        } else {
            add.setVisibility(VISIBLE);
        }
        invalidate();
        requestLayout();
    }

    public void setEditable(boolean editable){
        idUser.setEnabled(editable);
        invalidate();
        requestLayout();
    }


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
