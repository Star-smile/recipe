package com.recipe.application.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.recipe.application.fragment.FindFragment;
import com.recipe.application.fragment.MeFragment;

public class MainFragmentAdapter extends FragmentPagerAdapter {
    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new MsgFragment();
                break;
            case 1:
                fragment = new FindFragment();
                break;
            case 2:
                fragment = new MeFragment();
                break;
            default:
                break;
        }
        assert fragment != null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

}