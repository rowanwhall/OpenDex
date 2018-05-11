package personal.rowan.sandbox.ui.main;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import personal.rowan.sandbox.ui.main.dagger.MainScope;
import rx.Observable;

@MainScope
public class MainRealmManager {

    private Realm mRealm;

    @Inject
    MainRealmManager(Realm realm) {
        mRealm = realm;
    }

    Observable<List<MainViewModel>> load() {
        return Observable.just(mRealm.copyFromRealm(mRealm.where(MainViewModel.class).findAll()));
    }

    public void update(List<MainViewModel> viewModels) {
        mRealm.beginTransaction();
        for(MainViewModel viewModel : viewModels) {
            mRealm.copyToRealmOrUpdate(viewModel);
        }
        mRealm.commitTransaction();
    }

    void clear() {
        mRealm.beginTransaction();
        mRealm.where(MainViewModel.class).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    void close() {
        mRealm.close();
    }

}
