package com.itjh.doushi.UI;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.itjh.doushi.R;
import com.itjh.doushi.UI.base.BaseActivity;
import com.itjh.doushi.UI.widget.SquareImageView;
import com.itjh.doushi.pojo.VideoEntity;

import butterknife.Bind;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-03-08
 * Time: 11:39
 * FIXME
 */
public class DetailActivity extends BaseActivity implements View.OnClickListener {

    public static final String EXTRA_IMAGE = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_VIDEO_URL = "videoUrl";
    public static final String EXTRA_PUSH_TIME = "pushTime";

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.sdv_image)
    SquareImageView simpleDraweeView;

    @Bind(R.id.svv_detail_video_view)
    VideoView squareVideoView;

    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.description)
    TextView description;

    public static void navigate(AppCompatActivity activity, View transitionImage, VideoEntity videoEntity) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, videoEntity.pic);
        intent.putExtra(EXTRA_TITLE, videoEntity.title);
        intent.putExtra(EXTRA_VIDEO_URL, videoEntity.videoUrl);
        intent.putExtra(EXTRA_PUSH_TIME, videoEntity.pushTime);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    @Override
    public int layoutID() {
        initActivityTransitions();
        return R.layout.activity_detail;
    }

    @Override
    public void init() {
        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> super.onBackPressed());

        String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        collapsingToolbarLayout.setTitle(itemTitle);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));


        Glide.with(this).load(Uri.parse(getIntent().getStringExtra(EXTRA_IMAGE))).listener(new RequestListener<Uri, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, Uri model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, Uri model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
                int primary = getResources().getColor(R.color.colorPrimary);
                Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.mipmap.top_bg)).getBitmap();
                Palette.from(bitmap).generate(palette -> {
                    collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
                    collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
                    updateBackground(floatingActionButton, palette);
                    supportStartPostponedEnterTransition();
                });
                return false;
            }
        }).into(simpleDraweeView);


        title.setText(itemTitle);
        description.setText(getIntent().getStringExtra(EXTRA_PUSH_TIME));


        floatingActionButton.animate().rotation(3600).setDuration(10000).setInterpolator(new LinearInterpolator()).start();

        simpleDraweeView.postDelayed(() -> {
            squareVideoView.setVisibility(View.VISIBLE);
            squareVideoView.setVideoURI(Uri.parse(getIntent().getStringExtra(EXTRA_VIDEO_URL)));
        }, 1500);

        squareVideoView.setMediaController(new MediaController(this));
        squareVideoView.setOnPreparedListener(mp -> {
            simpleDraweeView.setVisibility(View.GONE);
            floatingActionButton.animate().scaleX(0).scaleY(0).setDuration(200).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    floatingActionButton.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            }).start();
            squareVideoView.start();
        });

        squareVideoView.setOnCompletionListener(mp -> {
            squareVideoView.setVisibility(View.GONE);
            simpleDraweeView.setVisibility(View.VISIBLE);
            floatingActionButton.setVisibility(View.VISIBLE);
        });

//        floatingActionButton.setOnClickListener(this);

    }

    private void initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.colorAccent));
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                squareVideoView.setVideoURI(Uri.parse(getIntent().getStringExtra(EXTRA_VIDEO_URL)));
                squareVideoView.start();
                simpleDraweeView.setVisibility(View.GONE);
                v.setVisibility(View.GONE);

                break;
        }
    }
}
