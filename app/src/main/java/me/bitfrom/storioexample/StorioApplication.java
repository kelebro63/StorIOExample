package me.bitfrom.storioexample;

import android.app.Application;
import android.content.Context;

import me.bitfrom.storioexample.config.AppComponent;
import me.bitfrom.storioexample.config.AppModule;
import me.bitfrom.storioexample.config.DaggerAppComponent;

/**
 * Created by Constantine on 02.12.2015.
 */
public class StorioApplication extends Application {

    private static Context context;

    private AppComponent appComponent;

    public static AppComponent appComponent() {
        return ((StorioApplication) getContext().getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        setContext(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        StorioApplication.context = context;
    }
}
