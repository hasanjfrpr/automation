package com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.count.DataItem;

import java.util.ArrayList;
import java.util.List;

public class GozareshKarMainAdapter extends RecyclerView.Adapter<GozareshKarMainAdapter.GozareshKarMainViewHolder> {


    private Context context;
    private List<DataItem>  dataItems = new ArrayList<>();
    public ClickItemEvent event;

    public GozareshKarMainAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public GozareshKarMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_gozareshkar_main,parent, false  );
        return new GozareshKarMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GozareshKarMainViewHolder holder, int position) {

        holder.name.setText(dataItems.get(position).getFullNameWork());
        holder.tedadGozaresh.setText(String.valueOf(dataItems.get(position).getCountReport()));
        holder.time.setText(String.valueOf(dataItems.get(position).getTotalTimeWorkReport()));

        holder.itemView.setOnClickListener(v -> {
            event.onClickItem(dataItems.get(position).getUserCode() , dataItems.get(position).getFullNameWork());
        });
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class GozareshKarMainViewHolder extends RecyclerView.ViewHolder{
        TextView name , tedadGozaresh , time;
        public GozareshKarMainViewHolder(@NonNull View itemView) {
            super(itemView);

            tedadGozaresh = itemView.findViewById(R.id.TV_pishkhan_gozareshKar_count_tedadGozareshat);
            name =  itemView.findViewById(R.id.TV_pishkhan_gozareshKar_count_name);
            time = itemView.findViewById(R.id.TV_pishkhan_gozareshKar_count_majmoZaman);
        }
    }

    public interface ClickItemEvent {
        void onClickItem(int userCode , String name);
    }
}
