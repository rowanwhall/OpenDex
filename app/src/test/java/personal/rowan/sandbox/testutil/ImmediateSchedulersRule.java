package personal.rowan.sandbox.testutil;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.android.schedulers.AndroidSchedulers;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;

/**
 * Created by Rowan Hall
 */

public class ImmediateSchedulersRule
        implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                RxJavaHooks.setOnIOScheduler(current -> Schedulers.immediate());
                RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidImmediateSchedulersHook());

                try {
                    base.evaluate();
                } finally {
                    RxJavaHooks.clear();
                    RxAndroidPlugins.getInstance().reset();
                    AndroidSchedulers.reset();
                }
            }
        };
    }

    private class RxAndroidImmediateSchedulersHook extends RxAndroidSchedulersHook {

        @Override
        public Scheduler getMainThreadScheduler() {
            return Schedulers.immediate();
        }

    }

}
