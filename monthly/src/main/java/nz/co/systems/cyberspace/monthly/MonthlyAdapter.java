package nz.co.systems.cyberspace.monthly;

/**
 * Created by Peter on 20/01/2015.
 * 
 * 
 * 
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MonthlyAdapter extends RecyclerView.Adapter<MonthlyAdapter.MonthlyViewHolder> {

    private List<MonthlyInfo> monthlyList;

    public MonthlyAdapter(List<MonthlyInfo> monthlyList) {
        this.monthlyList = monthlyList;
    }


    @Override
    public int getItemCount() {
        return monthlyList.size();
    }

    @Override
    public void onBindViewHolder(MonthlyViewHolder MonthlyViewHolder, int i) {
       MonthlyInfo monthlyInfo = monthlyList.get(i);
       MonthlyViewHolder.weekLabel.setText(monthlyInfo.weekLabel);
       MonthlyViewHolder.mCycleLabel.setText(monthlyInfo.mcycleLabel);
       MonthlyViewHolder.volumeLabel.setText(monthlyInfo.volumeLabel);
       MonthlyViewHolder.intensityLabel.setText(monthlyInfo.intensityLabel);
       MonthlyViewHolder.totalsLabel.setText(monthlyInfo.totalsLabel);
       MonthlyViewHolder.sessionLabel.setText(monthlyInfo.totalsSessionsLabel);
       MonthlyViewHolder.hoursLabel.setText(monthlyInfo.totalsHoursLabel);
       MonthlyViewHolder.kmsLabel.setText(monthlyInfo.totalsKmsLabel);
       MonthlyViewHolder.mCycleTextView.setText(monthlyInfo.mcycle);
       MonthlyViewHolder.volumeTextView.setText(monthlyInfo.mcycle);
       MonthlyViewHolder.intensityTextView.setText(monthlyInfo.mcycle);
       MonthlyViewHolder.sessionTextView.setText(monthlyInfo.mcycle);
       MonthlyViewHolder.hoursTextView.setText(monthlyInfo.mcycle);
       MonthlyViewHolder.kmsTextView.setText(monthlyInfo.mcycle);
       MonthlyViewHolder.goalsLabel.setText(monthlyInfo.goalsLabel);
       MonthlyViewHolder.commentsLabel.setText(monthlyInfo.commentLabel);


    }

    @Override
    public MonthlyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.week_in_a_month, viewGroup, false);

        return new MonthlyViewHolder(itemView);
    }

    public static class MonthlyViewHolder extends RecyclerView.ViewHolder {

        protected TextView weekLabel;
        protected TextView mCycleLabel;
        protected TextView volumeLabel;
        protected TextView intensityLabel;
        protected TextView totalsLabel;
        protected TextView sessionLabel;
        protected TextView hoursLabel;
        protected TextView kmsLabel;
        protected TextView mCycleTextView;
        protected TextView volumeTextView;
        protected TextView intensityTextView;
        protected TextView sessionTextView;
        protected TextView hoursTextView;
        protected TextView kmsTextView;
        protected TextView goalsLabel;
        protected TextView commentsLabel;
        protected ListView goalsListView;
        protected ListView commentsListView;




        public MonthlyViewHolder(View v) {
            super(v);
            weekLabel = (TextView) v.findViewById(R.id.weekLabel);
            mCycleLabel = (TextView) v.findViewById(R.id.mCycleLabel);
            volumeLabel = (TextView) v.findViewById(R.id.volumeLabel);
            intensityLabel = (TextView) v.findViewById(R.id.intensityLabel);
            totalsLabel = (TextView) v.findViewById(R.id.totalsLabel);
            sessionLabel = (TextView) v.findViewById(R.id.sessionLabel);
            hoursLabel = (TextView) v.findViewById(R.id.hoursLabel);
            kmsLabel = (TextView) v.findViewById(R.id.kmsLabel);
            mCycleTextView = (TextView) v.findViewById(R.id.mCycleTextView);
            volumeTextView = (TextView) v.findViewById(R.id.volumeTextView);
            intensityTextView = (TextView) v.findViewById(R.id.intensityTextView);
            sessionTextView = (TextView) v.findViewById(R.id.sessionTextView);
            hoursTextView = (TextView) v.findViewById(R.id.hoursTextView);
            kmsTextView = (TextView) v.findViewById(R.id.kmsTextView);
            goalsLabel = (TextView) v.findViewById(R.id.goalsLabel);
            commentsLabel = (TextView) v.findViewById(R.id.commentsLabel);
            goalsListView = (ListView) v.findViewById(R.id.goalsListView);
            commentsListView = (ListView) v.findViewById(R.id.commentsListView);
        } }
    }
