package personal.rowan.sandbox.ui.main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import personal.rowan.sandbox.model.PokemonList;
import personal.rowan.sandbox.model.Result;
import personal.rowan.sandbox.network.PokemonService;
import personal.rowan.sandbox.testutil.ImmediateSchedulersRule;
import rx.Observable;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

/**
 * Created by Rowan Hall
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Rule
    public final ImmediateSchedulersRule immediateSchedulersRule = new ImmediateSchedulersRule();

    private MainPresenter mPresenter;

    @Mock
    private MainView mView;
    @Mock
    private PokemonService mPokemonService;
    @Mock
    private MainRealmManager mRealmManager;

    @Before
    public void before() {
        when(mRealmManager.load()).thenReturn(Observable.just(Collections.emptyList()));
        mPresenter = new MainPresenter(mPokemonService, mRealmManager);
        mPresenter.attach(mView);
    }

    @Test
    public void testCacheValid() {
        List<MainViewModel> dataSet = fakeViewModels();
        when(mRealmManager.load()).thenReturn(Observable.just(dataSet));
        mPresenter.refreshData(null);

        // The service is called once in setup
        verify(mRealmManager, times(2)).load();
        verify(mPokemonService, times(1)).getPokemonList(anyObject());
        verify(mRealmManager).update(dataSet);
        verify(mView).displayPokemonList(dataSet);
        verify(mView).hidePaginationProgress();
        verify(mView).hideProgress();
    }

    @Test
    public void testCacheInvalid() {
        when(mRealmManager.load()).thenReturn(Observable.just(Collections.emptyList()));
        when(mPokemonService.getPokemonList(null)).thenReturn(Observable.just(fakePokemonList()));
        mPresenter.refreshData(null);

        verify(mRealmManager, times(2)).load();
        verify(mPokemonService, times(2)).getPokemonList(null);
        verify(mRealmManager).update(anyObject());
        verify(mView).displayPokemonList(anyObject());
        verify(mView).hidePaginationProgress();
        verify(mView).hideProgress();
    }

    @Test
    public void testClearAndRefresh() {
        when(mPokemonService.getPokemonList(null)).thenReturn(Observable.just(fakePokemonList()));
        mPresenter.clearAndRefreshData();

        verify(mPokemonService, times(2)).getPokemonList(null);
        verify(mRealmManager, times(2)).load();
        verify(mRealmManager).update(anyObject());
        verify(mView).displayPokemonList(anyObject());
        verify(mView).hideProgress();
    }

    private static List<MainViewModel> fakeViewModels() {
        List<MainViewModel> viewModels = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            viewModels.add(new MainViewModel("fake", i));
        }
        return viewModels;
    }

    private static PokemonList fakePokemonList() {
        PokemonList pokemonList = new PokemonList();
        pokemonList.setCount(100);
        List<Result> results = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Result result = new Result();
            result.setName("fake");
            results.add(result);
        }
        pokemonList.setResults(results);
        return pokemonList;
    }

}
