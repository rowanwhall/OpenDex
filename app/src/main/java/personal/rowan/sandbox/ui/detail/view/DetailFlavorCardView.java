package personal.rowan.sandbox.ui.detail.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.view.RxView;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ViewDetailFlavorCardBinding;
import rx.Observable;

/**
 * Created by Rowan Hall
 */

public class DetailFlavorCardView
        extends LinearLayout {

    private ViewDetailFlavorCardBinding mBinding;

    public DetailFlavorCardView(Context context) {
        super(context);
        init(context);
    }

    public DetailFlavorCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_detail_flavor_card, this, true);
    }

    public void setViewModel(DetailFlavorCardViewModel viewModel) {
        mBinding.setViewModel(viewModel);
    }

    public Observable<Void> onPokedexEntriesClicked() {
        return RxView.clicks(mBinding.activityDetailLoadFlavorBtn);
    }

    public void showPokedexEntriesProgress() {
        mBinding.activityDetailLoadFlavorBtn.setVisibility(View.INVISIBLE);
        mBinding.activityDetailLoadFlavorPb.setVisibility(View.VISIBLE);
    }

    public void onPokedexEntriesSuccess() {
        mBinding.activityDetailLoadFlavorBtn.setVisibility(View.GONE);
        mBinding.activityDetailLoadFlavorPb.setVisibility(View.GONE);
        mBinding.activityDetailPokedexEntriesLl.setVisibility(View.VISIBLE);
    }

    public void onPokedexEntriesFailure() {
        mBinding.activityDetailLoadFlavorBtn.setVisibility(View.VISIBLE);
        mBinding.activityDetailLoadFlavorPb.setVisibility(View.GONE);
    }

}
