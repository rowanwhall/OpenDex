package personal.rowan.sandbox.ui.main;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;

import java.util.List;

import javax.inject.Inject;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ActivityMainBinding;
import personal.rowan.sandbox.ui.base.BaseRecyclerViewAdapter;
import personal.rowan.sandbox.ui.base.BaseViewHolder;
import personal.rowan.sandbox.ui.base.presenter.BasePresenterActivity;
import personal.rowan.sandbox.ui.base.presenter.PresenterFactory;
import personal.rowan.sandbox.ui.detail.DetailActivity;
import personal.rowan.sandbox.ui.main.dagger.MainComponent;
import personal.rowan.sandbox.ui.main.dagger.MainScope;
import personal.rowan.sandbox.ui.main.recycler.MainListAdapter;
import personal.rowan.sandbox.ui.main.recycler.MainListViewHolder;

@MainScope
public class MainActivity
        extends BasePresenterActivity<MainPresenter, MainView>
        implements MainView, BaseRecyclerViewAdapter.OnItemClickListener {

    @Inject
    MainPresenterFactory mPresenterFactory;

    private MainPresenter mPresenter;
    private ActivityMainBinding mBinding;

    private MainListAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    @NonNull
    @Override
    protected PresenterFactory<MainPresenter> getPresenterFactory() {
        return mPresenterFactory;
    }

    @Override
    protected void beforePresenterPrepared() {
        setViews();
        MainComponent.injector.call(this);
    }

    private void setViews() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setToolbar(mBinding.activityMainTb, getString(R.string.activity_main_title));

        RecyclerView recyclerView = mBinding.activityMainRv;
        recyclerView.setItemAnimator(null);
        recyclerView.setLayoutManager(mLayoutManager = new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, mLayoutManager.getOrientation()));
        recyclerView.setAdapter(mAdapter = new MainListAdapter());
        mAdapter.setOnItemClickListener(this);

        SwipeRefreshLayout swipeRefreshLayout = mBinding.activityMainSrl;
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorSwipeRefresh));
        swipeRefreshLayout.setOnRefreshListener(() -> mPresenter.clearAndRefreshData());
    }

    @Override
    protected void onPresenterPrepared(@NonNull MainPresenter presenter) {
        mPresenter = presenter;
        mPresenter.bindRecyclerView(RxRecyclerView.scrollEvents(mBinding.activityMainRv));
    }

    @Override
    protected void onPresenterDestroyed() {
        mPresenter = null;
    }

    @Override
    public void displayPokemonList(List<MainViewModel> data) {
        hideProgress();
        mAdapter.paginateData(data);
    }

    @Override
    public void navigateToPokemonDetail(String name, Integer number, Bundle extras) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.ARGS_POKEMON_NUMBER, number);
        intent.putExtra(DetailActivity.ARGS_POKEMON_NAME, name);
        startActivity(intent, extras);
    }

    @Override
    public boolean shouldPaginate() {
        return mLayoutManager.findLastVisibleItemPosition() >= mAdapter.getItemCount() - 1;
    }

    @Override
    public void showErrorMessage(Throwable e) {
        hideProgress();
        showToastMessage(e.getMessage());
    }

    @Override
    public void showProgress() {
        mBinding.activityMainSrl.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mBinding.activityMainSrl.setRefreshing(false);
    }

    @Override
    public void showPaginationProgress() {
        mBinding.activityMainPaginationPb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePaginationProgress() {
        mBinding.activityMainPaginationPb.setVisibility(View.GONE);
    }

    @Override
    public boolean onItemClick(BaseRecyclerViewAdapter adapter, BaseViewHolder holder, View adapterView, int position) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                ((MainListViewHolder) holder).getModelView(),
                getString(R.string.activity_detail_transition_name));
        MainViewModel item = mAdapter.getItem(position);
        navigateToPokemonDetail(item.getName(), item.getNumber(), options.toBundle());
        return true;
    }

}
