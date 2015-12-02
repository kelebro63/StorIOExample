package me.bitfrom.storioexample.config;

import javax.inject.Singleton;

import dagger.Component;
import me.bitfrom.storioexample.StorioApplication;
import me.bitfrom.storioexample.ui.BaseActivity;

/**
 * Created by Constantine on 02.12.2015.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(StorioApplication storioApplication);

    void inject(BaseActivity activity);

}
