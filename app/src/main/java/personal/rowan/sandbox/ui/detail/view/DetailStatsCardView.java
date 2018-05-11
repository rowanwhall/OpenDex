package personal.rowan.sandbox.ui.detail.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import personal.rowan.sandbox.R;
import personal.rowan.sandbox.databinding.ViewDetailStatsCardBinding;

/**
 * Created by Rowan Hall
 */

public class DetailStatsCardView extends LinearLayout {

    private ViewDetailStatsCardBinding mBinding;

    public DetailStatsCardView(Context context) {
        super(context);
        init(context);
    }

    public DetailStatsCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.view_detail_stats_card, this, true);
    }

    public void setViewModel(DetailStatsCardViewModel viewModel) {
        mBinding.setViewModel(viewModel);
    }

}
