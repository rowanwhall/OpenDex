package personal.rowan.sandbox.ui.base.presenter;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by Rowan Hall
 */

class PresenterLoader<P extends BasePresenter>
        extends Loader<P> {

    private P mPresenter;
    private PresenterFactory<P> mPresenterFactory;

    PresenterLoader(Context context, PresenterFactory<P> presenterFactory) {
        super(context);
        this.mPresenterFactory = presenterFactory;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();

        if (mPresenter == null) {
            forceLoad();
        } else {
            deliverResult(mPresenter);
        }
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        mPresenter = mPresenterFactory.create();

        deliverResult(mPresenter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        if(mPresenter != null) {
            mPresenter.onDestroyed();
            mPresenter = null;
        }
    }

}
