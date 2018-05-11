package personal.rowan.sandbox.ui.main.recycler;

import android.view.View;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.ui.base.BaseRecyclerViewAdapter;
import personal.rowan.sandbox.ui.base.BaseViewHolder;
import personal.rowan.sandbox.ui.main.MainViewModel;

/**
 * Created by Rowan Hall
 */

public class MainListAdapter
        extends BaseRecyclerViewAdapter<MainViewModel> {

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.listitem_pokemon;
    }

    @Override
    protected BaseViewHolder<MainViewModel> buildViewHolder(int viewType, View view) {
        return new MainListViewHolder(this, view);
    }

}
