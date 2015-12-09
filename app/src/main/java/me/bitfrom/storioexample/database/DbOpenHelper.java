package me.bitfrom.storioexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.bitfrom.storioexample.config.ConstantsManager;
import me.bitfrom.storioexample.database.tables.QuotesTable;


public class DbOpenHelper extends SQLiteOpenHelper {

    public DbOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, ConstantsManager.DB_NAME, factory, ConstantsManager.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(QuotesTable.getCreateTableQuery());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
