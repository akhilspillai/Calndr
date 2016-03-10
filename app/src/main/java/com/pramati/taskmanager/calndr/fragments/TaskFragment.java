package com.pramati.taskmanager.calndr.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pramati.taskmanager.calndr.R;

import java.security.PolicySpi;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by akhil on 8/3/16.
 * This fragment displays the task list for a particular day
 */

public class TaskFragment extends Fragment{

    private static final String POSITION_KEY = "position_key";

    @Bind(R.id.txt_fragment_date)
    TextView txtDate;

    public static TaskFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(POSITION_KEY, position);
        TaskFragment fragment = new TaskFragment();
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
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int position = getArguments().getInt(POSITION_KEY);
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, position - 2500);

        txtDate.setText("Today's date is : ");
        txtDate.append(String.format("%1$tA %1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp", cal));
    }
}
