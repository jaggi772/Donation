package com.donationapp.activities;

import android.os.Bundle;

import com.donationapp.R;
import com.donationapp.fragments.SelectAmountFragment;

/**
 * Created by tajinder on 10/6/17.
 */

public class SelectAmountActivity extends BaseActivity {
    @Override
    protected int getLayoutResId() {
        return R.layout.select_amount_activity;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {

    }

    @Override
    protected void setupData() {

        push(SelectAmountFragment.newInstance(null),R.id.f_container);
    }
}
