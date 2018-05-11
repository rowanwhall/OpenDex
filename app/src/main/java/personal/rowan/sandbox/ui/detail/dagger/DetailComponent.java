package personal.rowan.sandbox.ui.detail.dagger;

import dagger.Component;
import personal.rowan.sandbox.application.App;
import personal.rowan.sandbox.application.dagger.component.ApplicationComponent;
import personal.rowan.sandbox.application.dagger.module.PokeApiModule;
import personal.rowan.sandbox.application.dagger.module.RealmModule;
import personal.rowan.sandbox.ui.detail.DetailActivity;
import rx.functions.Action1;

/**
 * Created by Rowan Hall
 */

@DetailScope
@Component(modules = { PokeApiModule.class, RealmModule.class }, dependencies = { ApplicationComponent.class })
public interface DetailComponent {

    void inject(DetailActivity detailActivity);

    Action1<DetailActivity> injector = activity -> DaggerDetailComponent
            .builder()
            .applicationComponent(App.applicationComponent(activity))
            .build()
            .inject(activity);

}
