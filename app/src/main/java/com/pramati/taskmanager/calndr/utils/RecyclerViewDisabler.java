package com.pramati.taskmanager.calndr.utils;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by akhil on 9/3/16.
 * This class disables manual scrolling for recyclerview
 */
public class RecyclerViewDisabler implements RecyclerView.OnItemTouchListener {

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return true;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
