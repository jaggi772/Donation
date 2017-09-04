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
 * Created by tajinder on 11/6/17.
 */

public class RegisterFragment extends BaseFragment {

    LinearLayout register_btn;
    TextView mLoginBtn;

    AppCompatEditText editFirstName;
    AppCompatEditText editLastName;
    AppCompatEditText editEmail;
    AppCompatEditText editPwd;
    AppCompatEditText editConfirmPwd;

    public static RegisterFragment newInstance(Bundle args) {
        RegisterFragment myFragment = new RegisterFragment();
        if (args != null) {
            myFragment.setArguments(args);
        }


        return myFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.register_layout;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {
        register_btn = (LinearLayout) findViewById(R.id.register_btn);
        mLoginBtn = (TextView) findViewById(R.id.login_buttton);
        editFirstName = (AppCompatEditText) findViewById(R.id.edit_firstName);
        editLastName = (AppCompatEditText) findViewById(R.id.edit_lastname);
        editEmail = (AppCompatEditText) findViewById(R.id.edit_email);
        editPwd = (AppCompatEditText) findViewById(R.id.editpassword);
        editConfirmPwd = (AppCompatEditText) findViewById(R.id.edit_confirm_pwd);
    }

    @Override
    protected void setupData() {
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()){
                    hitRegisterApi();
                }

            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push(RealLoginFragment.newInstance(null), R.id.fragment_l_conatiner);
            }
        });
    }


    public void hitRegisterApi() {


        if (!Utilities.isConnectedToInternet(getActivity())) {
            showToast(getString(R.string.internet_connect));
            return;
        }
        Utilities.showProgressDialog(getActivity(), getString(R.string.logiing_in));
        final Map<String, String> params = new HashMap<String, String>();
        params.put("email", editEmail.getText().toString());
        params.put("User_Password", editPwd.getText().toString());
        params.put("first_name", editFirstName.getText().toString().trim());
        params.put("last_name", editLastName.getText().toString().trim());
        String url = Constant.BASE_URL + Constant.API_SIGNUP;
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

        if (TextUtils.isEmpty(editFirstName.getText().toString())) {
            showToast(getString(R.string.enter_first_name));
            return false;
        } else if (TextUtils.isEmpty(editLastName.getText().toString())) {
            showToast(getString(R.string.enter_last_name));
            return false;
        } else if (TextUtils.isEmpty(editEmail.getText().toString().trim()) || !android.util.Patterns.EMAIL_ADDRESS.matcher(editEmail.getText().toString().trim()).matches()) {
            showToast(getString(R.string.enter_username));
            return false;

        } else if (TextUtils.isEmpty(editPwd.getText().toString()) || TextUtils.isEmpty(editConfirmPwd.getText().toString().trim()) || !editPwd.getText().toString().trim().equals(editConfirmPwd.getText().toString().trim())) {

            showToast(getString(R.string.enter_valid_pasword));
            return false;
        }
        return true;


    }
}
