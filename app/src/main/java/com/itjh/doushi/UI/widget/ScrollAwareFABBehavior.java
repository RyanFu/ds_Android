package com.itjh.doushi.UI.widget;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.itjh.doushi.Utils.ScreenUtils;

public class ScrollAwareFABBehavior extends CoordinatorLayout.Behavior<ImageView> {
    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;

    public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
        super();
    }

    @Override
    public boolean onStartNestedScroll(final CoordinatorLayout coordinatorLayout, final ImageView child,
                                       final View directTargetChild, final View target, final int nestedScrollAxes) {
        // Ensure we react to vertical scrolling
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(final CoordinatorLayout coordinatorLayout, final ImageView child,
                               final View target, final int dxConsumed, final int dyConsumed,
                               final int dxUnconsumed, final int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        if (dyConsumed > 0 && !this.mIsAnimatingOut && child.getVisibility() == View.VISIBLE) {
            // User scrolled down and the FAB is currently visible -> hide the FAB
            animateOut(child);
        } else if (dyConsumed < 0 && child.getVisibility() != View.VISIBLE) {
            // User scrolled up and the FAB is currently not visible -> show the FAB
            animateIn(child);
        }
    }

    // Same animation that FloatingActionImageView.Behavior uses to hide the FAB when the AppBarLayout exits
    private void animateOut(final ImageView button) {

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) button.getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenHeight(button.getContext());
        button.setLayoutParams(layoutParams);
//        if (Build.VERSION.SDK_INT >= 14) {
//            ViewCompat.animate(button).scaleX(0.0F).scaleY(0.0F).alpha(0.0F).setInterpolator(INTERPOLATOR).withLayer()
//                    .setListener(new ViewPropertyAnimatorListener() {
//                        public void onAnimationStart(View view) {
//                            ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
//                        }
//
//                        public void onAnimationCancel(View view) {
//                            ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
//                        }
//
//                        public void onAnimationEnd(View view) {
//                            ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
//                            view.setVisibility(View.GONE);
//                        }
//                    }).start();
//        } else {
//            Animation anim = AnimationUtils.loadAnimation(button.getContext(), android.R.anim.fade_out);
//            anim.setInterpolator(INTERPOLATOR);
//            anim.setDuration(200L);
//            anim.setAnimationListener(new Animation.AnimationListener() {
//                public void onAnimationStart(Animation animation) {
//                    ScrollAwareFABBehavior.this.mIsAnimatingOut = true;
//                }
//
//                public void onAnimationEnd(Animation animation) {
//                    ScrollAwareFABBehavior.this.mIsAnimatingOut = false;
//                    button.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onAnimationRepeat(final Animation animation) {
//                }
//            });
//            button.startAnimation(anim);
//        }
    }

    // Same animation that FloatingActionImageView.Behavior uses to show the FAB when the AppBarLayout enters
    private void animateIn(ImageView button) {
//        button.setVisibility(View.VISIBLE);
//        if (Build.VERSION.SDK_INT >= 14) {
//            ViewCompat.animate(button).scaleX(1.0F).scaleY(1.0F).alpha(1.0F)
//                    .setInterpolator(INTERPOLATOR).withLayer().setListener(null)
//                    .start();
//        } else {
//            Animation anim = AnimationUtils.loadAnimation(button.getContext(), android.R.anim.fade_in);
//            anim.setDuration(200L);
//            anim.setInterpolator(INTERPOLATOR);
//            button.startAnimation(anim);
//        }
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) button.getLayoutParams();
        layoutParams.height = ScreenUtils.getScreenWidth(button.getContext());
        button.setLayoutParams(layoutParams);
    }
}