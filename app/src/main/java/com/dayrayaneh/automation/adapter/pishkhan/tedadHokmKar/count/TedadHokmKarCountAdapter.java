package com.dayrayaneh.automation.adapter.pishkhan.tedadHokmKar.count;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.count.DataItem;

import java.util.ArrayList;
import java.util.List;

public class TedadHokmKarCountAdapter extends RecyclerView.Adapter<TedadHokmKarCountAdapter.TedadHokmKarCountViewHolder> {

    private Context context;
    private List<DataItem> dataItems = new ArrayList<>();
    public Event event;

    public TedadHokmKarCountAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public TedadHokmKarCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tedad_hokmkar_count,parent,false);
        return new TedadHokmKarCountViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TedadHokmKarCountViewHolder holder, int position) {
        holder.name.setText(dataItems.get(position).getFullName());
        holder.tedadHokm.setText(String.valueOf(dataItems.get(position).getHokmKarCount()));
        holder.tedadErja.setText(String.valueOf(dataItems.get(position).getErjaCount()));


        holder.itemView.setOnClickListener(v -> {
            event.clickEvent(dataItems.get(position).getPersonCode() , dataItems.get(position).getFullName());
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class TedadHokmKarCountViewHolder extends  RecyclerView.ViewHolder{
        TextView name , tedadHokm , tedadErja;
        public TedadHokmKarCountViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.TV_pishkhan_tedadHokmKar_count_name);
            tedadErja = itemView.findViewById(R.id.TV_pishkhan_tedadHokmKar_count_tedadErja);
            tedadHokm = itemView.findViewById(R.id.TV_pishkhan_tedadHokmKar_count_tedadHokm);
        }
    }



    public interface Event{
        void clickEvent(int personalCode , String name);
    }

}
