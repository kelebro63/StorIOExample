package me.bitfrom.storioexample.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.bitfrom.storioexample.database.entities.Quote;
import me.bitfrom.storioexample.database.entities.QuoteStorIOSQLiteDeleteResolver;
import me.bitfrom.storioexample.database.entities.QuoteStorIOSQLiteGetResolver;
import me.bitfrom.storioexample.database.entities.QuoteStorIOSQLitePutResolver;

@Module
public class DbModule {

    @Provides
    @NonNull
    @Singleton
    public StorIOSQLite provideStorIOSQLite(@NonNull SQLiteOpenHelper sqLiteOpenHelper) {
        return DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(sqLiteOpenHelper)
                .addTypeMapping(Quote.class, SQLiteTypeMapping.<Quote>builder()
                        .putResolver(new QuoteStorIOSQLitePutResolver())
                        .getResolver(new QuoteStorIOSQLiteGetResolver())
                        .deleteResolver(new QuoteStorIOSQLiteDeleteResolver()).build())
                .build();
    }

    @Provides
    @NonNull
    @Singleton
    public SQLiteOpenHelper providesSQLiteOpenHelper(@NonNull Context context) {
        return new DbOpenHelper(context);
    }
}
