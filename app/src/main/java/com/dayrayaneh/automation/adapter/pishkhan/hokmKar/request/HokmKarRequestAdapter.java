package com.dayrayaneh.automation.adapter.pishkhan.hokmKar.request;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.request.DataItem;

import java.util.ArrayList;
import java.util.List;

public class HokmKarRequestAdapter extends RecyclerView.Adapter<HokmKarRequestAdapter.HokmKarRequestViewHolder> {


    private Context context;
    private List<DataItem> dataItems = new ArrayList<>();


    public HokmKarRequestAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public HokmKarRequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hokm_kar_detail_request,parent ,false);
        return new HokmKarRequestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HokmKarRequestViewHolder holder, int position) {



        holder.request.setText(dataItems.get(position).getRequest());

        if (dataItems.get(position).getRequest().isEmpty()){
            holder.request.setText("درخواست کننده ای وجود ندارد");
        }
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }


    public class HokmKarRequestViewHolder extends RecyclerView.ViewHolder{
        TextView request;
        public HokmKarRequestViewHolder(@NonNull View itemView) {
            super(itemView);
            request = itemView.findViewById(R.id.TV_item_pishkhan_hokmKar_detail_darkhastMoshtari);
        }

    }

}
