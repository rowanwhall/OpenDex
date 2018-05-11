package personal.rowan.sandbox.ui.detail;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import personal.rowan.sandbox.model.pokemon.Ability;
import personal.rowan.sandbox.model.pokemon.Ability_;
import personal.rowan.sandbox.model.pokemon.Pokemon;
import personal.rowan.sandbox.model.pokemon.Sprites;
import personal.rowan.sandbox.model.pokemon.Stat;
import personal.rowan.sandbox.model.pokemon.Type;
import personal.rowan.sandbox.model.pokemon.Type_;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.testutil.ImmediateSchedulersRule;
import rx.Observable;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Created by Rowan Hall
 */

@RunWith(MockitoJUnitRunner.class)
public class DetailPresenterTest {

    @Rule
    public final ImmediateSchedulersRule immediateSchedulersRule = new ImmediateSchedulersRule();

    private DetailPresenter mPresenter;

    @Mock
    private DetailView mView;
    @Mock
    private PokemonService mPokemonService;
    @Mock
    private DetailRealmManager mRealmManager;

    @Before
    public void before() {
        mPresenter = new DetailPresenter(mPokemonService, mRealmManager);
        mPresenter.attach(mView);
    }

    @Test
    public void testSetInitialData() {
        String actualName = "name";
        DetailViewModel data = fakeViewModel(actualName);
        when(mRealmManager.load(actualName)).thenReturn(Observable.just(data));
        mPresenter.setInitialData(actualName);

        verify(mRealmManager).load(actualName);
        verify(mView, never()).abort();
    }

    @Test
    public void testInvalidNumberArgument() {
        mPresenter.refreshData(null);
        mPresenter.refreshData(-1);

        verify(mView, times(2)).abort();
        verify(mPokemonService, never()).getPokemon(anyObject());
    }

    @Test
    public void testRefreshData() {
        String actualName = "name";
        Integer actualNumber = 1;
        DetailViewModel data = fakeViewModel(actualName);
        when(mRealmManager.load(actualName)).thenReturn(Observable.just(data));
        when(mPokemonService.getPokemon(String.valueOf(actualNumber))).thenReturn(Observable.just(fakePokemon()));
        mPresenter.setInitialData(actualName);
        mPresenter.refreshData(actualNumber);

        verify(mRealmManager).load(actualName);
        verify(mView, never()).abort();
        verify(mPokemonService).getPokemon(String.valueOf(actualNumber));
        verify(mRealmManager).update(data);
        verify(mView).hideProgress();
        verify(mView).bindViewModel(data);
        verify(mView, never()).showErrorMessage(anyObject());
        verify(mView, never()).onDisplayPokedexEntry();
        verify(mView, never()).showPokedexEntryError(anyObject());
        verify(mView, never()).showPokedexEntryProgress();
    }

    private static DetailViewModel fakeViewModel(String name) {
        DetailViewModel detailViewModel = new DetailViewModel(name, "artworkUrl", "modelUrl");
        detailViewModel.setValues("type", "weight", "height", "abilities",
                "frontSpriteUrl", "backSpriteUrl",
                "HP", "ATK", "DEF", "SPATK", "SPDEF", "SPD");
        return detailViewModel;
    }

    private static Pokemon fakePokemon() {
        Pokemon pokemon = new Pokemon();

        List<Type> types = new ArrayList<>();
        Type type = new Type();
        Type_ type_ = new Type_();
        type_.setName("type");
        type.setType(type_);
        types.add(type);
        pokemon.setTypes(types);

        pokemon.setWeight(1.0);
        pokemon.setHeight(2.0);

        List<Ability> abilities = new ArrayList<>();
        Ability ability = new Ability();
        Ability_ ability_ = new Ability_();
        ability_.setName("ability");
        ability.setAbility(ability_);
        ability.setIsHidden(false);
        abilities.add(ability);
        pokemon.setAbilities(abilities);

        Sprites sprites = new Sprites();
        sprites.setFrontDefault("frontSpriteUrl");
        sprites.setBackDefault("backSpriteUrl");
        pokemon.setSprites(sprites);

        List<Stat> stats = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            Stat stat = new Stat();
            stat.setBaseStat(i * 10);
            stats.add(stat);
        }
        pokemon.setStats(stats);

        return pokemon;
    }

}
