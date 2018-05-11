package personal.rowan.sandbox.ui.base.presenter;

/**
 * Created by Rowan Hall
 */

/**
 * Creates a Presenter object.
 * @param <P> presenter type
 */
public interface PresenterFactory<P extends BasePresenter> {
    P create();
}
