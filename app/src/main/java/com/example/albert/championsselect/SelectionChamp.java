package com.example.albert.championsselect;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectionChamp extends AppCompatActivity {

    public static final String nameChamp = "champName";
    public static final String keyValueAtaque = "ataque";
    public static final String keyValueDefensa = "defensa";
    public static final String keyValueHabilidad = "habilidad";
    public static final String keyValueDificultad = "dificultad";

    private Spinner champName;
    private String selectedChamp;

    private SeekBar seekBarbAtaque;
    private SeekBar seekBarDefensa;
    private SeekBar seekBarHabilidad;
    private SeekBar seekBarDificultad;

    public static LOLDatabase lolDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_champ);

        lolDatabase = new LOLDatabase(this);

        champName = (Spinner)findViewById(R.id.champName);

        seekBarbAtaque = (SeekBar)findViewById(R.id.seekBarAtaque);
        seekBarDefensa = (SeekBar)findViewById(R.id.seekBarDefensa);
        seekBarHabilidad = (SeekBar)findViewById(R.id.seekBarHabilidad);
        seekBarDificultad = (SeekBar)findViewById(R.id.seekBarDificultad);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            seekBarbAtaque.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#A33D34")));
            seekBarDefensa.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#506B09")));
            seekBarHabilidad.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#4A7BA6")));
            seekBarDificultad.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#79639C")));
        }

        //seekBarDificultad.getProgressDrawable().setColorFilter(Color., android.graphics.PorterDuff.Mode.SRC_IN);


        final String[] datos = new String[]{"Leona","Thresh","Blitz","Braum","Morgana","Tahm"};

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, datos);


        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        champName.setAdapter(adaptador);

        champName.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                        selectedChamp =  "" + parent.getItemAtPosition(position);
                    }

                    public void onNothingSelected(AdapterView<?> parent) {
                        //lblMensaje.setText("");
                    }
                });
    }

    public void selectChamp(View view) {

        int valueAtaque = seekBarbAtaque.getProgress();
        int valueDefensa = seekBarDefensa.getProgress();
        int valueHabilidad = seekBarHabilidad.getProgress();
        int valueDificultad = seekBarDificultad.getProgress();

        /*Toast.makeText(this,"Ataque : " + valueAtaque +
                "\nDefensa : " +valueDefensa+
                "\nHabilidad : " +valueHabilidad+
                "\nDificultad : " +valueDificultad,Toast.LENGTH_LONG).show();*/
        Intent intent = new Intent(this,PreviewChamp.class);

        Bundle bundle = new Bundle();
        bundle.putString(nameChamp, selectedChamp);
        bundle.putInt(keyValueAtaque, valueAtaque);
        bundle.putInt(keyValueDefensa, valueDefensa);
        bundle.putInt(keyValueHabilidad, valueHabilidad);
        bundle.putInt(keyValueDificultad,valueDificultad);
        intent.putExtras(bundle);

        startActivity(intent);
        //Toast.makeText(this,selectedChamp,Toast.LENGTH_LONG).show();
    }
}
