package com.donationapp.activities;

import android.os.Bundle;

import com.donationapp.R;
import com.donationapp.fragments.LoginFragment;
import com.donationapp.fragments.RSideImgFragment;

/**
 * Created by tajinder on 7/6/17.
 */

public class LRActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.lr_layout;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {

        push(LoginFragment.newInstance(null),R.id.fragment_l_conatiner);
        push(RSideImgFragment.newInstance(null),R.id.fragment_r_conatiner);


    }

    @Override
    protected void setupData() {
    }
    public void forceCrash() {
        throw new RuntimeException("This is a crash");
    }

}
