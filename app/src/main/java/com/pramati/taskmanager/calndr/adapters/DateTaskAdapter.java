package com.pramati.taskmanager.calndr.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.pramati.taskmanager.calndr.fragments.TaskFragment;


public class DateTaskAdapter extends FragmentStatePagerAdapter {

    private static final int SIZE = 5000;

    public DateTaskAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return SIZE;
    }

    @Override
    public Fragment getItem(int position) {
        return TaskFragment.newInstance(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position + 1);
    }

}
