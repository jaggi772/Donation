package com.donationapp.fragments;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.donationapp.R;

/**
 * Created by subir on 29/7/17.
 */

public class ForgotPasswordFragment extends BaseFragment{

    AppCompatEditText editEmail;
    LinearLayout layoutForgotPwd;
    TextView txtLogin;

    @Override
    protected int getLayoutResId() {
        return R.layout.layout_forgot_pwd;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {

    }

    @Override
    protected void setupData() {

    }
}
