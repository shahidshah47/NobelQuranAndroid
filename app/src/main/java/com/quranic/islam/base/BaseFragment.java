package com.quranic.islam.base;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.quranic.islam.MyApplication;
import com.quranic.islam.SingletonClass;

public class BaseFragment extends Fragment {
    public Context mContext;

    public MyApplication app;
    public SingletonClass ST;

    public String mParam1;
    public String mParam2;
    public int mParam3;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        app = (MyApplication) getActivity().getApplication();
        ST = SingletonClass.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
