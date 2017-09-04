package com.donationapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResId(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUI(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupData();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected abstract int getLayoutResId();

    protected abstract void setupUI(Bundle savedInstanceState);
    protected abstract void setupData();


    protected Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }


    /*protected void push(BaseFragment fragment, int resId) {
        getChildFragmentManager().beginTransaction().replace(resId, fragment).commitAllowingStateLoss();
    }*/
    protected void push(BaseFragment fragment, int resId) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(resId, fragment).commitAllowingStateLoss();
    }

    protected void pop(BaseFragment fragment) {
        getChildFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
    }

    protected void show(BaseFragment fragment) {
        if (getActivity() != null && !getActivity().isFinishing())
            getChildFragmentManager().beginTransaction().show(fragment).commitAllowingStateLoss();
    }

    protected void hide(BaseFragment fragment) {
        if (getActivity() != null && !getActivity().isFinishing())
            getChildFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

    public final View findViewById(int id) {
        return getView().findViewById(id);
    }

    public final void showToast(String message) {
        Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public final void showToast(int resId) {
        Toast.makeText(getActivity().getApplicationContext(), getResources().getString(resId), Toast.LENGTH_SHORT).show();
    }


}