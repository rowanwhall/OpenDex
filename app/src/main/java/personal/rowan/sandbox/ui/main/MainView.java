package personal.rowan.sandbox.ui.main;

import android.os.Bundle;

import java.util.List;

/**
 * Created by Rowan Hall
 */

interface MainView {

    void displayPokemonList(List<MainViewModel> data);

    void navigateToPokemonDetail(String name, Integer number, Bundle extras);

    boolean shouldPaginate();

    void showErrorMessage(Throwable e);

    void showProgress();

    void hideProgress();

    void showPaginationProgress();

    void hidePaginationProgress();

}
