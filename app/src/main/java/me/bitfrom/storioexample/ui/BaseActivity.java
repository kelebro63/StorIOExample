package me.bitfrom.storioexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import me.bitfrom.storioexample.StorioApplication;
import me.bitfrom.storioexample.network.UmoriliLoadService;
import me.bitfrom.storioexample.util.ApplicationPreferences;

/**
 * Created by Constantine on 02.12.2015.
 **/
abstract public class BaseActivity extends AppCompatActivity {

    @Inject
    protected Context context;

    @Inject
    protected ApplicationPreferences applicationPreferences;

    @Inject
    protected UmoriliLoadService loadService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());

        StorioApplication.appComponent().inject(this);
    }

    abstract protected int getContentView();
}
