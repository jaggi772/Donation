package com.donationapp.fragments;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.donationapp.R;

/**
 * Created by tajinder on 8/6/17.
 */

public class RSideImgFragment extends BaseFragment {


    ImageView mImageView;

    public static RSideImgFragment newInstance(Bundle args) {
        RSideImgFragment myFragment = new RSideImgFragment();
        if (args != null) {
            myFragment.setArguments(args);
        }
        return myFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.rsideimg_fragment;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {

        mImageView = (ImageView) findViewById(R.id.imageview);

        Glide.with(getApplicationContext()).load(R.drawable.login_right_img).into(mImageView);

    }

    @Override
    protected void setupData() {

    }

}
