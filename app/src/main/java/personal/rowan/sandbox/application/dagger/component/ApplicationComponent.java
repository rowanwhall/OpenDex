package personal.rowan.sandbox.application.dagger.component;

import android.content.Context;

import dagger.Component;
import personal.rowan.sandbox.application.dagger.module.ApplicationModule;

/**
 * Created by Rowan Hall
 */

@Component( modules = { ApplicationModule.class })
public interface ApplicationComponent {

    Context context();

}
