package personal.rowan.sandbox.ui.detail.coordinator;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import personal.rowan.sandbox.R;

/**
 * Created by Rowan Hall
 */

public class DetailImageBehavior
        extends CoordinatorLayout.Behavior<ImageView> {

    private Context mContext;

    private float mCustomFinalHeight;

    private int mStartXPosition;
    private float mStartToolbarPosition;
    private int mStartYPosition;
    private int mFinalYPosition;
    private int mStartHeight;
    private int mFinalXPosition;
    private float mChangeBehaviorPoint;

    public DetailImageBehavior(Context context, AttributeSet attrs) {
        mContext = context;

        if(attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DetailImageBehavior);
            mCustomFinalHeight = a.getDimension(R.styleable.DetailImageBehavior_finalHeight, 0);
            a.recycle();
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {
        initProperties(child, dependency);

        final int maxScrollDistance = (int) mStartToolbarPosition;
        float expandedPercentageFactor = dependency.getY() / maxScrollDistance;

        if(expandedPercentageFactor < mChangeBehaviorPoint) {
            float heightFactor = (mChangeBehaviorPoint - expandedPercentageFactor) / mChangeBehaviorPoint;

            float distanceXToSubtract = ((mStartXPosition - mFinalXPosition)
                    * heightFactor) + (child.getHeight() / 2);
            float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + (child.getHeight() / 2);

            child.setX(mStartXPosition - distanceXToSubtract);
            child.setY(mStartYPosition - distanceYToSubtract);

            float heightToSubtract = ((mStartHeight - mCustomFinalHeight) * heightFactor);

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            lp.width = (int) (mStartHeight - heightToSubtract);
            lp.height = (int) (mStartHeight - heightToSubtract);
            child.setLayoutParams(lp);
        } else {
            float distanceYToSubtract = ((mStartYPosition - mFinalYPosition)
                    * (1f - expandedPercentageFactor)) + (mStartHeight / 2);

            child.setX(mStartXPosition - child.getWidth() / 2);
            child.setY(mStartYPosition - distanceYToSubtract);

            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
            // noinspection SuspiciousNameCombination
            lp.width = mStartHeight;
            lp.height = mStartHeight;
            child.setLayoutParams(lp);
        }
        return true;
    }

    private void initProperties(ImageView child, View dependency) {
        if(mStartYPosition == 0) {
            mStartYPosition = (int) (dependency.getY());
        }

        if(mFinalYPosition == 0) {
            mFinalYPosition = (dependency.getHeight() / 2);
        }

        if(mStartHeight == 0) {
            mStartHeight = child.getHeight();
        }

        if(mStartXPosition == 0) {
            mStartXPosition = (int) (child.getX() + (child.getWidth() / 2));
        }

        if(mFinalXPosition == 0) {
            mFinalXPosition = mContext.getResources().getDimensionPixelOffset(R.dimen.spacing_normal) + ((int) mCustomFinalHeight / 2);
        }

        if(mStartToolbarPosition == 0) {
            mStartToolbarPosition = dependency.getY();
        }

        if(mChangeBehaviorPoint == 0) {
            mChangeBehaviorPoint = (child.getHeight() - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition));
        }
    }

}
