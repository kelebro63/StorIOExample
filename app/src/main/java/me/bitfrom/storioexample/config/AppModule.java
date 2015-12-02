package me.bitfrom.storioexample.config;

import android.app.Application;
import android.content.Context;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.bitfrom.storioexample.BuildConfig;
import me.bitfrom.storioexample.network.RequestKeeper;
import me.bitfrom.storioexample.network.UmoriliAPI;
import me.bitfrom.storioexample.network.UmoriliLoadService;
import me.bitfrom.storioexample.util.ApplicationPreferences;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context providesContext() {
        return application;
    }

    @Provides
    @Singleton
    public ApplicationPreferences providesApplicationPreferences(Context context) {
        return new ApplicationPreferences(context);
    }

    @Provides
    @Singleton
    public UmoriliAPI providesRestService() {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .disableHtmlEscaping()
                .create();

        OkHttpClient client = new OkHttpClient();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RequestKeeper.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        return retrofit.create(UmoriliAPI.class);
    }

    @Provides
    @Singleton
    public UmoriliLoadService providesLoadService(UmoriliAPI umoriliAPI) {
        return new UmoriliLoadService(umoriliAPI);
    }
}
