package me.bitfrom.storioexample.database;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * Table metadata
 */
public class QuotesContract implements BaseColumns {

    @NonNull
    public static final String TABLE = "quotes";

    @NonNull
    public static final String CONTENT = "content";

}
