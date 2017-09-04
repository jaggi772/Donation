package com.donationapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.donationapp.R;
import com.donationapp.activities.EmailActivity;
import com.donationapp.adapters.AmountRecyclerViewAdapter;

/**
 * Created by tajinder on 10/6/17.
 */

public class SelectAmountFragment extends BaseFragment implements AmountRecyclerViewAdapter.ItemClickListener {

    RecyclerView mRecyclerView;
    TextView mSelectedAmtTextView;
    TextView mPayBtn;


    public static SelectAmountFragment newInstance(Bundle args) {
        SelectAmountFragment myFragment = new SelectAmountFragment();
        if (args != null) {
            myFragment.setArguments(args);
        }


        return myFragment;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.select_amount_fragment;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState) {

        mRecyclerView = (RecyclerView) findViewById(R.id.amount_recyclerview);
        mSelectedAmtTextView = (TextView) findViewById(R.id.amount_textview);
        mPayBtn = (TextView) findViewById(R.id.payment_btn);
    }

    @Override
    protected void setupData() {
        mSelectedAmtTextView.setText(data[0]);
        setUpRecyclerView();
        mPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EmailActivity.class));
            }
        });
    }

    AmountRecyclerViewAdapter adapter;
    String[] data = {"$20", "$50", "$100", "$200", "$250", "$500"};

    void setUpRecyclerView() {
        // data to populate the RecyclerView with


        // set up the RecyclerView
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        adapter = new AmountRecyclerViewAdapter(getContext(), data);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
//        if (position==0){
//            startActivity(new Intent(getContext(), SelectAmountActivity.class));
//        }
        adapter.setSelectedPosition(position);
        mSelectedAmtTextView.setText(data[position]);
        adapter.notifyDataSetChanged();
    }

}
