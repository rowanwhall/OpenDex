package personal.rowan.sandbox.application;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import personal.rowan.sandbox.application.dagger.component.ApplicationComponent;
import personal.rowan.sandbox.application.dagger.component.DaggerApplicationComponent;
import personal.rowan.sandbox.application.dagger.module.ApplicationModule;

/**
 * Created by Rowan Hall
 */

public class App
        extends Application {

    private ApplicationComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent applicationComponent(Context context) {
        return ((App) context.getApplicationContext()).mAppComponent;
    }

}
