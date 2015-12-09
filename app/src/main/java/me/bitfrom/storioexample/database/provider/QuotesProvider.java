package me.bitfrom.storioexample.database.provider;


import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import me.bitfrom.storioexample.StorioApplication;
import me.bitfrom.storioexample.config.ConstantsManager;
import me.bitfrom.storioexample.database.QuotesContract;

public class QuotesProvider extends ContentProvider{

    @Inject
    SQLiteOpenHelper sqLiteOpenHelper;

    private static final String PATH_QUOTES = "quotes";
    private static final int URI_MATCHER_CODE_QUOTES = 1;

    private static final UriMatcher URI_MATCHER = new UriMatcher(1);

    static {
        URI_MATCHER.addURI(ConstantsManager.AUTHORITY, PATH_QUOTES, URI_MATCHER_CODE_QUOTES);
    }

    @Override
    public boolean onCreate() {
        StorioApplication.appComponent().inject(this);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        switch (URI_MATCHER.match(uri)) {
            case URI_MATCHER_CODE_QUOTES:
                return sqLiteOpenHelper
                        .getReadableDatabase()
                        .query(QuotesContract.TABLE,
                                projection,
                                selection,
                                selectionArgs,
                                null,
                                null,
                                sortOrder);
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final long insertedId;

        switch (URI_MATCHER.match(uri)) {
            case URI_MATCHER_CODE_QUOTES:
                insertedId = sqLiteOpenHelper
                        .getWritableDatabase()
                        .insert(QuotesContract.TABLE,
                                null,
                                values);
                break;
            default:
                return null;
        }

        if (insertedId != -1) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return ContentUris.withAppendedId(uri, insertedId);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final int numberOfRowsDeleted;

        switch (URI_MATCHER.match(uri)) {
            case URI_MATCHER_CODE_QUOTES:
                numberOfRowsDeleted = sqLiteOpenHelper
                        .getWritableDatabase()
                        .delete(QuotesContract.TABLE,
                                selection,
                                selectionArgs);
                break;

            default:
                return 0;
        }

        if (numberOfRowsDeleted > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final int numberOfRowsUpdated;

        switch (URI_MATCHER.match(uri)) {
            case URI_MATCHER_CODE_QUOTES:
                numberOfRowsUpdated = sqLiteOpenHelper
                        .getWritableDatabase()
                        .update(QuotesContract.TABLE,
                                values,
                                selection,
                                selectionArgs);
                break;

            default:
                return 0;
        }

        if (numberOfRowsUpdated > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numberOfRowsUpdated;
    }
}
