package com.dayrayaneh.automation.view.pishkhanItemView.hokmKarha.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.base.BaseFragment;

public class HokmKarDetailFragment extends BaseFragment {

    private int userId;


    public HokmKarDetailFragment(int userId) {
        this.userId = userId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hokm_kar_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Toast.makeText(getContext(), ""+userId, Toast.LENGTH_SHORT).show();
    }
}