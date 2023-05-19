package com.quranic.islam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import com.quranic.islam.R;
import com.quranic.islam.base.BaseFragment;
import com.quranic.islam.databinding.FragmentQiblaBinding;
import com.quranic.islam.databinding.FragmentQuranBinding;

public class QiblaFragment extends BaseFragment {
    private FragmentQiblaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_qibla, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {

    }
}