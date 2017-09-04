package com.donationapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.donationapp.DonationApplication;
import com.donationapp.R;
import com.donationapp.activities.HomeActivity;
import com.donationapp.utils.Constant;
import com.donationapp.utils.Utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tajinder on 6/6/17.
 */

public class LoginFragment extends BaseFragment {

    LinearLayout login_btn;
    LinearLayout login_as_guest;

    public static LoginFragment newInstance(Bundle args) {
        LoginFragment myFragment = new LoginFragment();
        if (args != null) {
            myFragment.setArguments(args);
        }
        return myFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.login_fragment;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        login_btn = (LinearLayout) findViewById(R.id.login_btn);
        login_as_guest = (LinearLayout) findViewById(R.id.login_as_guest);

    }

    @Override
    protected void setupData() {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(RealLoginFragment.newInstance(null), R.id.fragment_l_conatiner);
            }
        });

        login_as_guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

    }





}
