package com.example.albert.championsselect;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListaChampion extends AppCompatActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_champion);

        LOLDatabase lolDatabase = new LOLDatabase(this);
        LOLQuerys lolQuerys = new LOLQuerys();

        context = this.getApplicationContext();

        recycler = (RecyclerView)findViewById(R.id.listaChamp);
        recycler.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recycler.setLayoutManager(lManager);

        adapter = new AdapterChamp(lolQuerys.select(lolDatabase));
        recycler.setAdapter(adapter);


    }
}
