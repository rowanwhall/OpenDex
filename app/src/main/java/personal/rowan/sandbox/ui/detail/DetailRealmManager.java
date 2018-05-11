package personal.rowan.sandbox.ui.detail;

import javax.inject.Inject;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmObject;
import personal.rowan.sandbox.ui.detail.dagger.DetailScope;
import personal.rowan.sandbox.util.PokemonUtil;
import rx.Observable;

@DetailScope
class DetailRealmManager {

    private Realm mRealm;

    @Inject
    DetailRealmManager(Realm realm) {
        mRealm = realm;
    }

    Observable<DetailViewModel> load(String name) {
        RealmObject realmResult = mRealm.where(DetailViewModel.class)
                .beginsWith("mName", name, Case.INSENSITIVE).findFirst();

        if(realmResult == null) {
            return Observable.just(new DetailViewModel(PokemonUtil.formatName(name),
                    PokemonUtil.buildPokemonArtworkUrl(name),
                    PokemonUtil.buildPokemonModelUrl(name)));
        }

        return realmResult
                .<DetailViewModel>asObservable()
                .map(realmViewModel -> mRealm.copyFromRealm(realmViewModel));
    }

    void update(DetailViewModel viewModel) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(viewModel);
        mRealm.commitTransaction();
    }

    void close() {
        mRealm.close();
    }

}
