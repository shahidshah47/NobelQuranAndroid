package com.quranic.islam.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.quranic.islam.R;
import com.quranic.islam.adapters.HomeMenuAdapter;
import com.quranic.islam.adapters.MenuAdapter;
import com.quranic.islam.base.BaseFragment;
import com.quranic.islam.base.BaseModel;
import com.quranic.islam.databinding.FragmentDashboardBinding;
import com.quranic.islam.interfaces.ApiCallListener;
import com.quranic.islam.models.MenuDrawerModel;
import com.quranic.islam.networks.factory.NetworkDataManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class DashboardFragment extends BaseFragment {
    private FragmentDashboardBinding binding;
    private HomeMenuAdapter mMenuAdapter;

    private List<BaseModel> menuList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        init();
        return binding.getRoot();
    }

    private void init() {
        menuList = new ArrayList<>();
        menuList.add(new MenuDrawerModel(getString(R.string.quran), R.drawable.ic_quran_tab));
        menuList.add(new MenuDrawerModel(getString(R.string.favorite), R.drawable.ic_fav_tab));
        menuList.add(new MenuDrawerModel(getString(R.string.namaz_time), R.drawable.ic_namaz_time_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.qibla), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.hadith), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.six_kalma), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.prayer), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.masnoon_duain), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.reminder), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.donate_us), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.settings), R.drawable.ic_profile_tab));
        menuList.add(new MenuDrawerModel(getString(R.string.exit), R.drawable.ic_exit_menu));

        setMenuAdapter();
        makeGetIslamicDateDetailApiCall();
    }

    private void setData() {
        binding.todayEnglishDateTV.setText(ST.getIslamicCurrentDate());
    }

    private void setMenuAdapter() {
        binding.homeItemRV.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mMenuAdapter = new HomeMenuAdapter(getActivity(), menuList, (position, model, status) -> {
            MenuDrawerModel drawerModel = (MenuDrawerModel) menuList.get(position);
            if (drawerModel.getTitle().equals(getString(R.string.home))) {

            } else if (drawerModel.getTitle().equals(getString(R.string.quran))) {

            } else if (drawerModel.getTitle().equals(getString(R.string.favorite))) {

            } else if (drawerModel.getTitle().equals(getString(R.string.namaz_time))) {

            } else if (drawerModel.getTitle().equals(getString(R.string.qibla))) {

            } else if (drawerModel.getTitle().equals(getString(R.string.account))) {

            } else if (drawerModel.getTitle().equals(getString(R.string.exit))) {

            }
        });

        binding.homeItemRV.setAdapter(mMenuAdapter);
    }

    public void makeGetIslamicDateDetailApiCall() {
        ST.showProgressDialog(getActivity());
        ST.BASE_URL = "https://api.aladhan.com/v1/";
        ST.refreshClient(getActivity(), ST.SERVER_TIMEOUT);

        String currentDate = ST.getCurrentDate();
        Call<Object> call = ST.getApiInterface(getActivity()).getCall("gToH?date=" + currentDate);
        ApiCallListener listener = (response, message, errors, isSuccess) -> {
            Log.d("list", "getList: " + response);
            if (isSuccess) {
                try {
                    JSONObject successOBJ = new JSONObject(response);
                    JSONObject dataOBJ = successOBJ.optJSONObject("data");
                    if (dataOBJ != null) {
                        JSONObject hijriOBJ = dataOBJ.optJSONObject("hijri");
                        if (hijriOBJ != null) {
                            String dayStr = hijriOBJ.optString("day", "");
                            String yearStr = hijriOBJ.optString("year", "");
                            JSONObject monthOBJ = hijriOBJ.optJSONObject("month");
                            if (monthOBJ != null) {
                                String enName = monthOBJ.optString("en", "");
                                binding.todayIslamicDateTV.setText(enName + " " + dayStr + ", " + yearStr + " AH");
                            }
                        }
                    }

                    setData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ST.dismissProgress();
        };
        NetworkDataManager dataManager = new NetworkDataManager(getActivity());
        dataManager.setRetrofit(ST.getClient(getActivity()));
        dataManager.setCall(call);
        dataManager.setListener(listener);
        dataManager.setTokenNeedToCheck(false);
        dataManager.setError(getString(R.string.api_failure));
        ST.makePostCall(dataManager);
    }
}