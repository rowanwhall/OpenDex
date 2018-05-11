package personal.rowan.sandbox.ui.detail;

/**
 * Created by Rowan Hall
 */

interface DetailView {

    Integer getNumberArgument();

    String getNameArgument();

    void bindViewModel(DetailViewModel viewModel);

    void showErrorMessage(Throwable e);

    void showProgress();

    void hideProgress();

    void abort();

    void onDisplayPokedexEntry();

    void showPokedexEntryProgress();

    void showPokedexEntryError(Throwable e);

}
