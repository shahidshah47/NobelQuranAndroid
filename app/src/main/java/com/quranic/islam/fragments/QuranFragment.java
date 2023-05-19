package com.quranic.islam.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.quranic.islam.R;
import com.quranic.islam.adapters.SurahParahAdapter;
import com.quranic.islam.base.BaseFragment;
import com.quranic.islam.databinding.FragmentQuranBinding;

public class QuranFragment extends BaseFragment {
    private FragmentQuranBinding binding;

    private SurahParahAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quran, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        setupTabs();
        setListener();
        setAdapter(ST.SURAH_TYPE);
    }

    private void setupTabs() {
        binding.surahParahTL.addTab(binding.surahParahTL.newTab().setText(getString(R.string.surah)));
        binding.surahParahTL.addTab(binding.surahParahTL.newTab().setText(getString(R.string.parah)));
    }

    private void setListener() {
        binding.surahParahTL.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setAdapter((tab.getPosition() + 1));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setAdapter(int type) {
        binding.surahParahRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new SurahParahAdapter(getActivity(), null, type, (position, model, status) -> {

        });

        binding.surahParahRV.setAdapter(mAdapter);
    }
}