package com.example.falepormim;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FalasDAO {
    private static final String DATABASE_NAME = "SpeechDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_SPEECH = "speech";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TEXT = "text";

    private static DatabaseHelper DBHelper;
    private SQLiteDatabase database;

    public FalasDAO(Context context) {
        DatabaseHelper dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL("CREATE TABLE " + TABLE_SPEECH + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TEXT + " TEXT);");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_SPEECH);
            onCreate(db);
        }
    }

    public FalasDAO open() throws SQLException {
        database = DBHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        database.close();
    }

    public long inserirFala(String text) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_TEXT, text);
        return database.insert(TABLE_SPEECH, null, initialValues);
    }

    public Cursor obterTodasAsFalas() {
        return database.query(TABLE_SPEECH, new String[]{COLUMN_ID, COLUMN_TEXT}, null, null, null, null, null);
    }
}