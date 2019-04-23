package com.abdo.hp.bank.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.abdo.hp.bank.ui.fragment.hom_fragment.ArticleFragment;
import com.abdo.hp.bank.ui.fragment.hom_fragment.RequestsFragment;

public class CustomViewAdapter extends FragmentPagerAdapter {
    public CustomViewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch (pos){
            case 0:
                return new ArticleFragment();
            case 1:
                return new RequestsFragment();
                default:
                    return null;
        }
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "مقالات";
            case 1:
                return "طلبات التبرع";
        }
        return super.getPageTitle(position);
    }
}