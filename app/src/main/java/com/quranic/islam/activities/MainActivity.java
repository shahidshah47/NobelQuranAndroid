package com.quranic.islam.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.manager.SupportRequestManagerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.quranic.islam.R;
import com.quranic.islam.adapters.MenuAdapter;
import com.quranic.islam.base.BaseActivity;
import com.quranic.islam.base.BaseModel;
import com.quranic.islam.databinding.ActivityMainBinding;
import com.quranic.islam.fragments.AccountFragment;
import com.quranic.islam.fragments.DashboardFragment;
import com.quranic.islam.fragments.FavFragment;
import com.quranic.islam.fragments.NamazTimingFragment;
import com.quranic.islam.fragments.QiblaFragment;
import com.quranic.islam.fragments.QuranFragment;
import com.quranic.islam.models.MenuDrawerModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private ActivityMainBinding binding;

    private Fragment currentFragment;
    private MenuAdapter mAdapter;

    private List<BaseModel> menuList;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        init();
    }

    private void init() {
        setDrawerLayout();
        setListener();
        loadFragment(new DashboardFragment(), null, "DashboardFragment", false);
    }

    private void setListener() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this);
        binding.toolbarIN.drawerMenuIV.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        onBackButtonPressed();
    }

    public void onBackButtonPressed() {
        if (currentFragment != null && (currentFragment instanceof DashboardFragment)) {
            if (doubleBackToExitPressedOnce) {
                getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                super.onBackPressed();
                finishAffinity();
                System.exit(0);
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            List<Fragment> fragments = fragmentManager.getFragments();
            if (fragments != null && fragments.size() > 0) {
                Fragment mFragment = getSupportFragmentManager().getFragments().get((fragments.size() - 1));

                if (mFragment != null) {
                    if (mFragment instanceof SupportRequestManagerFragment) {

                    } else {
                        getSupportFragmentManager().beginTransaction().remove(mFragment).commit();
                        getSupportFragmentManager().popBackStack();

                        if (fragments.size() > 1) {
                            currentFragment = fragments.get(fragments.size() - 2);
                        } else
                            super.onBackPressed();
                    }
                } else
                    super.onBackPressed();
            } else
                super.onBackPressed();
        }
    }

    public void setDrawerLayout() {
        menuList = new ArrayList<>();
        menuList.add(new MenuDrawerModel(getString(R.string.home), R.drawable.ic_home_tab));
        menuList.add(new MenuDrawerModel(getString(R.string.quran), R.drawable.ic_quran_tab));
        menuList.add(new MenuDrawerModel(getString(R.string.favorite), R.drawable.ic_fav_tab));
        menuList.add(new MenuDrawerModel(getString(R.string.namaz_time), R.drawable.ic_namaz_time_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.qibla), R.drawable.ic_qibla_menu));
        menuList.add(new MenuDrawerModel(getString(R.string.account), R.drawable.ic_profile_tab));
        menuList.add(new MenuDrawerModel(getString(R.string.exit), R.drawable.ic_exit_menu));

        setAdapter();
    }

    private void setAdapter() {
        binding.menuLV.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MenuAdapter(mAct, menuList, (position, model, status) -> {
            MenuDrawerModel drawerModel = (MenuDrawerModel) menuList.get(position);
            if (drawerModel.getTitle().equals(getString(R.string.home))) {
                loadFragment(new DashboardFragment(), null, "DashboardFragment", false);
            } else if (drawerModel.getTitle().equals(getString(R.string.quran))) {
                loadFragment(new QuranFragment(), null, "QuranFragment", false);
            } else if (drawerModel.getTitle().equals(getString(R.string.favorite))) {
                loadFragment(new FavFragment(), null, "FavFragment", false);
            } else if (drawerModel.getTitle().equals(getString(R.string.namaz_time))) {
                loadFragment(new NamazTimingFragment(), null, "NamazTimingFragment", false);
            } else if (drawerModel.getTitle().equals(getString(R.string.qibla))) {
                loadFragment(new QiblaFragment(), null, "QiblaFragment", false);
            } else if (drawerModel.getTitle().equals(getString(R.string.account))) {
                loadFragment(new AccountFragment(), null, "AccountFragment", false);
            } else if (drawerModel.getTitle().equals(getString(R.string.exit))) {
                finishAffinity();
                System.exit(0);
            }

            hideSideMenu();
        });

        binding.menuLV.setAdapter(mAdapter);
    }

    private void hideSideMenu() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void loadFragment(Fragment fragment, Bundle bundle, String nameStr, boolean isAdd) {
        currentFragment = fragment;
        if (bundle != null)
            currentFragment.setArguments(bundle);

        if (isAdd) {
            getSupportFragmentManager().beginTransaction().addToBackStack(nameStr).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).add(R.id.containerFL, currentFragment).commit();
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.containerFL, currentFragment, nameStr).addToBackStack(null);
            transaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.homeIT:
                loadFragment(new DashboardFragment(), null, "DashboardFragment", false);
                break;
            case R.id.quranIT:
                loadFragment(new QuranFragment(), null, "QuranFragment", false);
                break;
            case R.id.favIT:
                loadFragment(new FavFragment(), null, "FavFragment", false);
                break;
            case R.id.accountIT:
                loadFragment(new AccountFragment(), null, "AccountFragment", false);
                break;
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        if (view.equals(binding.toolbarIN.drawerMenuIV)) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}