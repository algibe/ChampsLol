package com.example.albert.championsselect;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

public class LOLQuerys {

    public boolean insert(Champion champion, LOLDatabase lolDatabase) {

        if(lolDatabase.getWritableDatabase() != null){
            //Getting writable permissions to database
            SQLiteDatabase sqLiteDatabase = lolDatabase.getWritableDatabase();

            //Getting data
            ContentValues contentValues = new ContentValues();

            contentValues.put(Champion.COLUMN_IMG, champion.getImg());
            contentValues.put(Champion.COLUMN_NAME, champion.getName());
            contentValues.put(Champion.COLUMN_ID_USER, champion.getIdUser());
            contentValues.put(Champion.COLUMN_ATAQUE, champion.getAtaque());
            contentValues.put(Champion.COLUMN_DEFENSA, champion.getDefensa());
            contentValues.put(Champion.COLUMN_HABILIDAD, champion.getHabilidad());
            contentValues.put(Champion.COLUMN_DIFICULTAD, champion.getDificultad());

            //Insert data
            sqLiteDatabase.insert(Champion.TABLE_NAME, null, contentValues);

            //Close database
            sqLiteDatabase.close();

            return true;
        }

        return false;
    }

    public boolean delete(LOLDatabase lolDatabase) {

        if(lolDatabase.getWritableDatabase() != null){

            String DELETE_FROM_ACTIVITY = "DELETE FROM " + Champion.TABLE_NAME;
            SQLiteDatabase sqLiteDatabase = lolDatabase.getWritableDatabase();
            sqLiteDatabase.execSQL(DELETE_FROM_ACTIVITY);

            return true;
        }
        return false;

    }

    public ArrayList<Champion> select(LOLDatabase lolDatabase) {

        SQLiteDatabase sqLiteDatabase = lolDatabase.getReadableDatabase();
        String selectAllActivity = "SELECT * FROM " + Champion.TABLE_NAME;
        ArrayList<Champion> championsArrayList = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(selectAllActivity, null);

        //Cursor to set all values to User
        if(cursor.moveToFirst()){
            do{
                Champion champion = new Champion();

                champion.setImg(cursor.getString(cursor.getColumnIndex(Champion.COLUMN_IMG)));
                champion.setName(cursor.getString(cursor.getColumnIndex(Champion.COLUMN_NAME)));
                champion.setIdUser(cursor.getString(cursor.getColumnIndex(Champion.COLUMN_ID_USER)));
                champion.setAtaque(cursor.getInt(cursor.getColumnIndex(Champion.COLUMN_ATAQUE)));
                champion.setDefensa(cursor.getInt(cursor.getColumnIndex(Champion.COLUMN_DEFENSA)));
                champion.setHabilidad(cursor.getInt(cursor.getColumnIndex(Champion.COLUMN_HABILIDAD)));
                champion.setDificultad(cursor.getInt(cursor.getColumnIndex(Champion.COLUMN_DIFICULTAD)));

                championsArrayList.add(champion);
            } while (cursor.moveToNext());
        }

        //Closing the cursor and database
        cursor.close();
        sqLiteDatabase.close();

        return championsArrayList;

    }

}
