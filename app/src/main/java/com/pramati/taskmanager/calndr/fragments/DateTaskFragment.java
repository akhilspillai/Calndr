package com.pramati.taskmanager.calndr.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.pramati.taskmanager.calndr.R;
import com.pramati.taskmanager.calndr.adapters.DateTaskAdapter;
import com.pramati.taskmanager.calndr.adapters.RecyclerTabAdapter;
import com.pramati.taskmanager.calndr.utils.RecyclerViewDisabler;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by akhil on 8/3/16.
 * This fragment displays the task list for a particular day
 */

public class DateTaskFragment extends Fragment {

    @Bind(R.id.view_pager)
    ViewPager viewPager;
    @Bind(R.id.recycler_tab)
    RecyclerTabLayout recyclerTabView;

    private DateTaskAdapter viewPagerAdapter;
    private RecyclerTabAdapter recyclerAdapter;

    public static DateTaskFragment newInstance() {

        Bundle args = new Bundle();

        DateTaskFragment fragment = new DateTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_task, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager();
    }

    private void setupViewPager() {
        viewPagerAdapter = new DateTaskAdapter(getChildFragmentManager());
        recyclerAdapter = new RecyclerTabAdapter(viewPager);
        viewPager.setAdapter(viewPagerAdapter);
        recyclerTabView.setUpWithAdapter(recyclerAdapter);
        viewPager.setCurrentItem(2500, false);
        recyclerTabView.addOnItemTouchListener(new RecyclerViewDisabler());
    }
}
