package com.pramati.taskmanager.calndr.adapters;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nshmura.recyclertablayout.RecyclerTabLayout;
import com.pramati.taskmanager.calndr.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecyclerTabAdapter extends RecyclerTabLayout.Adapter<RecyclerTabAdapter.ViewHolder> {

    private static final int SIZE = 5000;

    public class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.txt_date)
        TextView txtDate;

        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }

        @SuppressWarnings("unused")
        @OnClick(R.id.fl_date)
        public void onItemClicked(View view) {

        }

    }

    public RecyclerTabAdapter(ViewPager viewPager) {
        super(viewPager);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, position - 2500);
//        holder.txtDate.setText(String.format("%1$tA %1$tb %1$td %1$tY at %1$tI:%1$tM %1$Tp", cal));
        holder.txtDate.setText(String.format("%1$tb %1$td", cal));
        if (position == getCurrentIndicatorPosition()) {
            holder.itemView.setBackgroundResource(R.color.date_selected_color);
        } else {
            holder.itemView.setBackgroundResource(R.color.date_unselected_color);
        }
    }

    @Override
    public int getItemCount() {
        return SIZE;
    }

}