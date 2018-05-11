package personal.rowan.sandbox.ui.main;

import javax.inject.Inject;

import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.main.dagger.MainScope;

/**
 * Created by Rowan Hall
 */

@MainScope
public class MainPresenterFactory
        implements PresenterFactory<MainPresenter> {

    private PokemonService mPokemonService;
    private MainRealmManager mRealmManager;

    @Inject
    MainPresenterFactory(PokemonService pokemonService, MainRealmManager realmManager) {
        mPokemonService = pokemonService;
        mRealmManager = realmManager;
    }

    @Override
    public MainPresenter create() {
        return new MainPresenter(mPokemonService, mRealmManager);
    }

}
