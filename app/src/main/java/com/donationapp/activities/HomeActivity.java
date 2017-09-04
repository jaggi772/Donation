package com.donationapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.donationapp.R;
import com.donationapp.dialogs.AlertDialogFragment;

/**
 * Created by tajinder on 10/6/17.
 */

public class HomeActivity extends BaseActivity {
    private ImageView mSplashImageView;
    private ImageView mLogoImageView;

    LinearLayout mDonationButton;


    @Override
    protected int getLayoutResId() {
        return R.layout.home_activity;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        mSplashImageView = (ImageView) findViewById(R.id.splash_img);
        mLogoImageView = (ImageView) findViewById(R.id.logo_img);
        mSplashImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mLogoImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mDonationButton = (LinearLayout)findViewById(R.id.donation_btn);
    }

    @Override
    protected void setupData() {

        Glide.with(getApplicationContext()).load(R.drawable.home_bg).into(mSplashImageView);
        mLogoImageView.setBackgroundResource(R.drawable.logo_small);

        mDonationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AlertDialogFragment dialogFragment = AlertDialogFragment.newInstance(null);
                dialogFragment.show(getFragmentManager(), AlertDialogFragment.class.getName());
            }
        });

    }


}
