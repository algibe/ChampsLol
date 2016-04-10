package com.example.albert.championsselect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LOLDatabase extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "lol.db";
    private SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

    public LOLDatabase(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        getWritableDatabase();
        //onUpgrade(sqLiteDatabase,3,3);
    }

    private final String CREATE_TABLE_CHAMP = "CREATE TABLE " + Champion.TABLE_NAME + " (" +
            Champion.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Champion.COLUMN_IMG + " TEXT," +
            Champion.COLUMN_NAME + " TEXT," +
            Champion.COLUMN_ID_USER + " TEXT," +
            Champion.COLUMN_ATAQUE + " INTEGER," +
            Champion.COLUMN_DEFENSA + " INTEGER," +
            Champion.COLUMN_HABILIDAD + " INTEGER," +
            Champion.COLUMN_DIFICULTAD + " INTEGER);";

    private final String DROP_TABLE_CHAMP = " DROP TABLE IF EXIST " + Champion.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CHAMP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_CHAMP);
        onCreate(db);

    }
}
