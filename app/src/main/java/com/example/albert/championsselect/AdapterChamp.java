package com.example.albert.championsselect;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.albert.lolchamp.LOLChamp;

import java.util.List;


public class AdapterChamp extends RecyclerView.Adapter<AdapterChamp.AnimeViewHolder> {

    private List<Champion> items;

    public static class AnimeViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public LOLChamp lolChamp;


        public AnimeViewHolder(View v) {
            super(v);
            lolChamp = (LOLChamp) v.findViewById(R.id.layoutChamp);
        }
    }

    public AdapterChamp(List<Champion> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public AnimeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_champ, viewGroup, false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AnimeViewHolder viewHolder, int i) {

        viewHolder.lolChamp.setNameChamp(items.get(i).getName());
        viewHolder.lolChamp.setUserName(items.get(i).getName());

        viewHolder.lolChamp.setAtaque(items.get(i).getAtaque());
        viewHolder.lolChamp.setDefensa(items.get(i).getDefensa());
        viewHolder.lolChamp.setHabilidad(items.get(i).getHabilidad());
        viewHolder.lolChamp.setDificultad(items.get(i).getDificultad());

        String name = "ic_" + items.get(i).getName().toLowerCase();
        int id = ListaChampion.context.getResources().getIdentifier(name, "mipmap", ListaChampion.context.getPackageName());
        Drawable drawable = ListaChampion.context.getResources().getDrawable(id);

        viewHolder.lolChamp.setImg(drawable);

    }
}
