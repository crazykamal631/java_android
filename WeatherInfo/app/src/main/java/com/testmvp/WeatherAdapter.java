package com.testmvp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testmvp.weather.pojos.WeatherResList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private List<WeatherResList> mList = new ArrayList<>();
    private String mCity;

    public WeatherAdapter(List<WeatherResList> list) {
        this.mList = list;
    }

    public void updateList(List<WeatherResList> list, String city) {
        mCity = city;
        mList.addAll(list);
    }

    public void clearData(){
        mList.clear();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lyt_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WeatherResList weatherRes = mList.get(position);
        holder.place.setText(mCity);
        holder.date.setText(weatherRes.getDt_txt());
        holder.temp.setText(String.valueOf(weatherRes.getMain().getTemp()));
        if (weatherRes.getWeather() != null && weatherRes.getWeather().length > 0) {
            holder.title.setText(weatherRes.getWeather()[0].getMain());
            holder.desc.setText(weatherRes.getWeather()[0].getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.place)
        public TextView place;

        @BindView(R.id.date)
        public TextView date;

        @BindView(R.id.title)
        public TextView title;

        @BindView(R.id.desc)
        public TextView desc;

        @BindView(R.id.temp)
        public TextView temp;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
