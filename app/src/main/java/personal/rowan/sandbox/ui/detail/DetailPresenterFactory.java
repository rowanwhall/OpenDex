package personal.rowan.sandbox.ui.detail;

import javax.inject.Inject;

import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail.dagger.DetailScope;

/**
 * Created by Rowan Hall
 */

@DetailScope
class DetailPresenterFactory
        implements PresenterFactory<DetailPresenter> {

    private PokemonService mPokemonService;
    private DetailRealmManager mRealmManager;

    @Inject
    DetailPresenterFactory(PokemonService pokemonService, DetailRealmManager detailRealmManager) {
        mPokemonService = pokemonService;
        mRealmManager = detailRealmManager;
    }

    @Override
    public DetailPresenter create() {
        return new DetailPresenter(mPokemonService, mRealmManager);
    }

}
