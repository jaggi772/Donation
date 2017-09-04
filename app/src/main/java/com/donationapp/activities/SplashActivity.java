package com.donationapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donationapp.R;

/**
 * Created by tajinder on 6/6/17.
 */

public class SplashActivity extends BaseActivity {
    private ImageView mSplashImageView;
    private ImageView mLogoImageView;


    @Override
    protected int getLayoutResId() {
        return R.layout.splash;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        mSplashImageView = (ImageView) findViewById(R.id.splash_img);
        mLogoImageView = (ImageView) findViewById(R.id.logo_img);
        mSplashImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mLogoImageView.setScaleType(ImageView.ScaleType.FIT_XY);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LRActivity.class));
                finish();
            }
        }, 2000);
    }

    @Override
    protected void setupData() {
        //Glide.with(getContext()).load(topAdvertisement.getBannerImage()).placeholder(R.drawable.lfty_top_advertisement).error(R.drawable.lfty_top_advertisement).diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.imgThumb);
        Glide.with(getApplicationContext()).load(R.drawable.splash).into(mSplashImageView);
        mLogoImageView.setBackgroundResource(R.drawable.logo_splash);


    }


}
