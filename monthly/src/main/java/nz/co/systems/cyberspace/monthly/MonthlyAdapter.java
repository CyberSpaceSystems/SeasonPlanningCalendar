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
import android.widget.ImageView;
import android.widget.LinearLayout;
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

        MonthlyViewHolder.mcycle.setText(monthlyInfo.mcycle);
        MonthlyViewHolder.volume.setText(monthlyInfo.volume);
        MonthlyViewHolder.intensity.setText(monthlyInfo.intensity);

    }

    @Override
    public MonthlyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.week_in_a_month, viewGroup, false);

        return new MonthlyViewHolder(itemView);
    }

    public static class MonthlyViewHolder extends RecyclerView.ViewHolder {

        private TextView mcycle;
        private TextView volume;
        private TextView intensity;
        private LinearLayout thumbnail;
        private ImageView listImage;


        public MonthlyViewHolder(View v) {
            super(v);
            mcycle = (TextView) v.findViewById(R.id.mcycle);
            volume = (TextView) v.findViewById(R.id.volume);
            intensity = (TextView) v.findViewById(R.id.intensity);
            listImage = (ImageView) v.findViewById(R.id.list_image);
            thumbnail = (LinearLayout) v.findViewById(R.id.thumbnail);

        }
    }
}


