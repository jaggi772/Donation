package com.donationapp;

import android.os.Bundle;

import com.donationapp.activities.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {

    }

    @Override
    protected void setupData() {

    }
}
