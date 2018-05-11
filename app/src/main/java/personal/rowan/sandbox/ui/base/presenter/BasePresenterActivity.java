package personal.rowan.sandbox.ui.base.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import personal.rowan.sandbox.ui.base.BaseActivity;

/**
 * Created by Rowan Hall
 */

public abstract class BasePresenterActivity<P extends BasePresenter<V>, V>
        extends BaseActivity {

    private static final int LOADER_ID = 101;
    private BasePresenter<V> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        beforePresenterPrepared();
        getSupportLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public final Loader<P> onCreateLoader(int id, Bundle args) {
                return new PresenterLoader<>(BasePresenterActivity.this, getPresenterFactory());
            }

            @Override
            public final void onLoadFinished(Loader<P> loader, P presenter) {
                BasePresenterActivity.this.mPresenter = presenter;
                onPresenterPrepared(presenter);
            }

            @Override
            public final void onLoaderReset(Loader<P> loader) {
                BasePresenterActivity.this.mPresenter = null;
                onPresenterDestroyed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.attach(getPresenterView());
    }

    @Override
    protected void onStop() {
        mPresenter.detach();
        super.onStop();
    }

    /**
     * Instance of {@link PresenterFactory} use to create a Presenter when needed. This instance should
     * not contain {@link android.app.Activity} context reference since it will be keep on rotations.
     */
    @NonNull
    protected abstract PresenterFactory<P> getPresenterFactory();

    /**
     * Hook for subclasses for before the {@link BasePresenter} is instantiated.
     * Primarily used to construct or inject dependencies for the Presenter.
     */
    protected void beforePresenterPrepared() {

    }

    /**
     * Hook for subclasses that deliver the {@link BasePresenter} before its View is attached.
     * Can be use to initialize the Presenter or simple hold a reference to it.
     */
    protected void onPresenterPrepared(@NonNull P presenter) {

    }

    /**
     * Hook for subclasses before the screen gets destroyed.
     */
    protected void onPresenterDestroyed() {

    }

    /**
     * Override in case of activity not implementing Presenter<View> interface
     */
    @SuppressWarnings("unchecked")
    @NonNull
    protected V getPresenterView() {
        return (V) this;
    }

    /**
     * Use this method in case you want to specify a specific ID for the {@link PresenterLoader}.
     * By default its value would be {@link #LOADER_ID}.
     */
    protected int loaderId() {
        return LOADER_ID;
    }

}
