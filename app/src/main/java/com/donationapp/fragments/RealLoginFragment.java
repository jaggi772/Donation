package com.donationapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.donationapp.DonationApplication;
import com.donationapp.R;
import com.donationapp.activities.HomeActivity;
import com.donationapp.utils.Constant;
import com.donationapp.utils.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tajinder on 10/6/17.
 */

public class RealLoginFragment extends BaseFragment {

    TextView create_acc_btn;
    LinearLayout mLoginLayout;
    AppCompatEditText editUserName;
    AppCompatEditText editPwd;


    public static RealLoginFragment newInstance(Bundle args) {
        RealLoginFragment myFragment = new RealLoginFragment();
        if (args != null) {
            myFragment.setArguments(args);
        }


        return myFragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.real_login_layout;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        create_acc_btn = (TextView) findViewById(R.id.create_acc_btn);
        mLoginLayout = (LinearLayout) findViewById(R.id.login_btn);
        editUserName = (AppCompatEditText) findViewById(R.id.edit_username);
        editPwd = (AppCompatEditText) findViewById(R.id.edit_pwd);
    }

    @Override
    protected void setupData() {

        mLoginLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    _hitApiForLogin();
                }
            }
        });
        create_acc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(RegisterFragment.newInstance(null), R.id.fragment_l_conatiner);
            }
        });
    }

    private void _hitApiForLogin() {

        if (!Utilities.isConnectedToInternet(getActivity())) {
            showToast(getString(R.string.internet_connect));
            return;
        }
        Utilities.showProgressDialog(getActivity(), getString(R.string.logiing_in));
        final Map<String, String> params = new HashMap<String, String>();
        params.put("email", editUserName.getText().toString());
        params.put("User_Password", editPwd.getText().toString());

        String url = Constant.BASE_URL + Constant.API_LOGIN;
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST,
                url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("response", response);
                    Utilities.dissmissDialog();
                    Intent intent = new Intent(getContext(), HomeActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Utilities.dissmissDialog();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                return params;
            }

        };
        DonationApplication.getInstance().addToRequestQueue(jsonObjReq, LoginFragment.class.getName());
    }

    public boolean validate() {

        if (TextUtils.isEmpty(editUserName.getText().toString().trim()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(editUserName.getText().toString().trim()).matches()) {
            showToast(getString(R.string.enter_username));
            return false;

        } else if (TextUtils.isEmpty(editPwd.getText().toString())) {
            showToast(getString(R.string.enter_password));
            return false;
        }
        return true;
    }
}
