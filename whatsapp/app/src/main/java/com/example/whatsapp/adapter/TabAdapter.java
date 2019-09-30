package com.example.whatsapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.whatsapp.fragments.ChamadasFragment;
import com.example.whatsapp.fragments.ConversasFragment;
import com.example.whatsapp.fragments.StatusFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    int countTabs;
    public TabAdapter(FragmentManager fm, int countTabs) {
        super(fm);
        this.countTabs = countTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new ConversasFragment();
            case 1:
                return new StatusFragment();
            case 2:
                return new ChamadasFragment();
            default:
                return new ConversasFragment();
        }
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}
