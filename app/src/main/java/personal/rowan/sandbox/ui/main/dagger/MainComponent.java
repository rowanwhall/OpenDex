package personal.rowan.sandbox.ui.main.dagger;

import dagger.Component;
import personal.rowan.sandbox.application.App;
import personal.rowan.sandbox.application.dagger.component.ApplicationComponent;
import personal.rowan.sandbox.application.dagger.module.PokeApiModule;
import personal.rowan.sandbox.application.dagger.module.RealmModule;
import personal.rowan.sandbox.ui.main.MainActivity;
import rx.functions.Action1;

/**
 * Created by Rowan Hall
 */

@MainScope
@Component(modules = { PokeApiModule.class, RealmModule.class }, dependencies = { ApplicationComponent.class })
public interface MainComponent {

    void inject(MainActivity mainActivity);

    Action1<MainActivity> injector = activity -> DaggerMainComponent
            .builder()
            .applicationComponent(App.applicationComponent(activity))
            .build()
            .inject(activity);

}
