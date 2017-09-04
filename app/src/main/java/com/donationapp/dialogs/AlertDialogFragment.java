package com.donationapp.dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.donationapp.R;
import com.donationapp.activities.SelectAmountActivity;
import com.donationapp.adapters.MyRecyclerViewAdapter;


public class AlertDialogFragment extends DialogFragment implements MyRecyclerViewAdapter.ItemClickListener , View.OnClickListener {


    ImageView mCross;
    RecyclerView mRecyclerView;

    public static AlertDialogFragment newInstance(Bundle args) {
        AlertDialogFragment fd = new AlertDialogFragment();

        fd.setArguments(args);

        return fd;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        setUpRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_select_fund, container, false);

        return view;

    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCross = (ImageView) view.findViewById(R.id.cross);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.fund_recyclerview);
        mCross.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {



            case R.id.cross:
                getDialog().dismiss();
                break;

            default:
                break;

        }


    }


    void openBrowser(String url) {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setUpRecyclerView(){
        // data to populate the RecyclerView with
        String[] data = {"General Offerings", "Tithe", "Building Fund", "Mission Fund"};

        // set up the RecyclerView
        mRecyclerView.setLayoutManager(new GridLayoutManager(getC(), 2));
        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(getC(), data);
        adapter.setClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        if (position==0){
            startActivity(new Intent(getC(), SelectAmountActivity.class));
        }
        Toast.makeText(getC(),"Clicked",Toast.LENGTH_LONG).show();
    }


   Context getC(){
       return  getActivity().getApplicationContext();
   }

}