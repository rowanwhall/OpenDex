package personal.rowan.sandbox.ui.detail;

import java.util.List;

import personal.rowan.sandbox.model.pokemon.Pokemon;
import personal.rowan.sandbox.model.pokemon.Sprites;
import personal.rowan.sandbox.model.pokemon.Stat;
import personal.rowan.sandbox.model.species.PokemonSpecies;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.ui.base.presenter.BasePresenter;
import personal.rowan.sandbox.util.PokemonUtil;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Rowan Hall
 */

class DetailPresenter
        extends BasePresenter<DetailView> {

    private PokemonService mPokemonService;
    private DetailRealmManager mRealmManager;
    private String mNameArgument;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    private DetailViewModel mViewModel;
    private Throwable mError;

    private Subscription mPokedexEntriesSubscription;
    private Throwable mPokedexEntriesError;

    DetailPresenter(PokemonService pokemonService, DetailRealmManager detailRealmManager) {
        super(DetailView.class);
        mPokemonService = pokemonService;
        mRealmManager = detailRealmManager;
    }

    void setInitialData(String name) {
        mNameArgument = name;
        mRealmManager.load(name)
                .subscribe(viewModel -> {
                    mViewModel = viewModel;
                }, e -> {
                    if(mView != null) {
                        mView.abort();
                    }
                });
    }

    void refreshData(Integer number) {
        if(number == null || number <= 0) {
            mView.abort();
            return;
        }

        Observable<Pokemon> pokemon = mPokemonService.getPokemon(String.valueOf(number));
        mCompositeSubscription.add(pokemon
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Pokemon>() {
                    @Override
                    public void onCompleted() {
                        if(mView != null) {
                            mView.hideProgress();
                        }
                        publish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mError = e;
                    }

                    @Override
                    public void onNext(Pokemon result) {
                        setValues(result);
                        mRealmManager.update(mViewModel);
                    }
                }));
    }

    void bindPokedexEntriesButton(Observable<Void> onPokedexEntriesClicked) {
        mCompositeSubscription.add(onPokedexEntriesClicked
                .subscribe(aVoid -> {
                    if(isPokedexEntriesSubscriptionActive()) {
                        return;
                    }

                    Observable<PokemonSpecies> species = mPokemonService.getPokemonSpecies(mNameArgument);
                    mCompositeSubscription.add(mPokedexEntriesSubscription = species
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe(() -> {
                                if(mView != null) {
                                    mView.showPokedexEntryProgress();
                                }
                            })
                            .subscribe(new Subscriber<PokemonSpecies>() {
                                @Override
                                public void onCompleted() {
                                    publish();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    mPokedexEntriesError = e;
                                }

                                @Override
                                public void onNext(PokemonSpecies result) {
                                    mViewModel.setPokedexEntries(PokemonUtil.getPokedexEntriesString(result));
                                }
                            })
                    );
                })
        );
    }

    private boolean isPokedexEntriesSubscriptionActive() {
        return mPokedexEntriesSubscription != null && !mPokedexEntriesSubscription.isUnsubscribed();
    }

    @Override
    protected void publish() {
        if(mView != null) {
            if(mViewModel != null) {
                mView.bindViewModel(mViewModel);
            }
            if(mError != null) {
                mView.showErrorMessage(mError);
            } else if(mViewModel == null || !mViewModel.hasValues()){
                mView.showProgress();
            }

            if(mViewModel != null && mViewModel.hasPokedexEntries()) {
                mView.onDisplayPokedexEntry();
            } else if(mPokedexEntriesError != null) {
                mView.showPokedexEntryError(mPokedexEntriesError);
            } else if(isPokedexEntriesSubscriptionActive()){
                mView.showPokedexEntryProgress();
            }
        }
    }

    @Override
    protected void onDestroyed() {
        mPokemonService = null;
        mRealmManager.close();
        mRealmManager = null;
        mNameArgument = null;
        if(mCompositeSubscription != null) {
            if(!mCompositeSubscription.isUnsubscribed()) {
                mCompositeSubscription.unsubscribe();
            }
            mCompositeSubscription = null;
        }
        mError = null;

        mPokedexEntriesSubscription = null;
        mPokedexEntriesError = null;

        mViewModel = null;
    }

    private void setValues(Pokemon pokemon) {
        String type = PokemonUtil.getFormattedType(pokemon.getTypes());
        String weight = PokemonUtil.getFormattedWeight(pokemon.getWeight());
        String height = PokemonUtil.getFormattedHeight(pokemon.getHeight());
        String abilities = PokemonUtil.getFormattedAbilities(pokemon.getAbilities());
        Sprites sprites = pokemon.getSprites();
        List<Stat> stats = pokemon.getStats();
        String HP = String.valueOf(stats.get(5).getBaseStat());
        String ATK = String.valueOf(stats.get(4).getBaseStat());
        String DEF = String.valueOf(stats.get(3).getBaseStat());
        String SPATK = String.valueOf(stats.get(2).getBaseStat());
        String SPDEF = String.valueOf(stats.get(1).getBaseStat());
        String SPD = String.valueOf(stats.get(0).getBaseStat());

        mViewModel.setValues(type, weight, height, abilities,
                sprites.getFrontDefault(), sprites.getBackDefault(),
                HP, ATK, DEF, SPATK, SPDEF, SPD);
    }

}
