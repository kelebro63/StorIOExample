package me.bitfrom.storioexample.database.provider.meta;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.delete.DeleteResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.DefaultGetResolver;
import com.pushtorefresh.storio.contentresolver.operations.get.GetResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.contentresolver.operations.put.PutResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.InsertQuery;
import com.pushtorefresh.storio.contentresolver.queries.UpdateQuery;

import me.bitfrom.storioexample.config.ConstantsManager;
import me.bitfrom.storioexample.database.QuotesContract;
import me.bitfrom.storioexample.database.entities.Quote;

public class QuoteMeta {

    @NonNull
    public static final Uri CONTENT_URI = Uri.parse("content://" + ConstantsManager.AUTHORITY +
                                                    "/quotes");

    @NonNull
    public static final PutResolver<Quote> PUT_RESOLVER = new DefaultPutResolver<Quote>() {
        @NonNull
        @Override
        protected InsertQuery mapToInsertQuery(@NonNull Quote object) {
            return InsertQuery.builder()
                    .uri(CONTENT_URI)
                    .build();
        }

        @NonNull
        @Override
        protected UpdateQuery mapToUpdateQuery(@NonNull Quote object) {
            return UpdateQuery.builder()
                    .uri(CONTENT_URI)
                    .where(QuotesContract._ID + " = ?")
                    .whereArgs(object.getId())
                    .build();
        }

        @NonNull
        @Override
        protected ContentValues mapToContentValues(@NonNull Quote object) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(QuotesContract._ID, object.getId());
            contentValues.put(QuotesContract.CONTENT, object.getContent());

            return contentValues;
        }
    };

    @NonNull
    public static final GetResolver<Quote> GET_RESOLVER = new DefaultGetResolver<Quote>() {
        @NonNull
        @Override
        public Quote mapFromCursor(@NonNull Cursor cursor) {
            return Quote.newQuote(
                    cursor.getLong(cursor.getColumnIndexOrThrow(QuotesContract._ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(QuotesContract.CONTENT))
            );
        }
    };

    @NonNull
    public static final DeleteResolver<Quote> DELETE_RESOLVER = new DefaultDeleteResolver<Quote>() {
        @NonNull
        @Override
        protected DeleteQuery mapToDeleteQuery(@NonNull Quote object) {
            return DeleteQuery.builder()
                    .uri(CONTENT_URI)
                    .where(QuotesContract._ID + " = ?")
                    .whereArgs(object.getId())
                    .build();
        }
    };
}
