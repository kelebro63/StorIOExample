package me.bitfrom.storioexample.database.tables;

import android.support.annotation.NonNull;

import me.bitfrom.storioexample.database.QuotesContract;

/**
 * Table where we gonna store all quotes
 */
public class QuotesTable {

    private QuotesTable() {
        throw new IllegalStateException("No instances please");
    }

    @NonNull
    public static String getCreateTableQuery() {
        return "CREATE TABLE " + QuotesContract.TABLE + "("
                + QuotesContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + QuotesContract.CONTENT + " TEXT NOT NULL " + ");";
    }
}
