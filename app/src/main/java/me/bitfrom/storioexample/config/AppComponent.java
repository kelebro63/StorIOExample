package me.bitfrom.storioexample.config;

import javax.inject.Singleton;

import dagger.Component;
import me.bitfrom.storioexample.StorioApplication;
import me.bitfrom.storioexample.database.DbModule;
import me.bitfrom.storioexample.database.provider.QuotesProvider;
import me.bitfrom.storioexample.ui.BaseActivity;

@Singleton
@Component(modules = {AppModule.class, DbModule.class})
public interface AppComponent {

    void inject(StorioApplication storioApplication);

    void inject(BaseActivity activity);

    void inject(QuotesProvider quotesProvider);
}
