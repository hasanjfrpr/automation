package com.dayrayaneh.automation.adapter.pishkhan.pishkan_darsadKharidMoshtarian.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.details.DataItem;

import java.util.ArrayList;
import java.util.List;

public class DarsadKharidMoshtarianDetalisAdapter extends RecyclerView.Adapter<DarsadKharidMoshtarianDetalisAdapter.DarsadKharidMoshtarianDetalisViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public DarsadKharidMoshtarianDetalisAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public DarsadKharidMoshtarianDetalisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_darsad_kharid_moshtarian_details,parent , false);
        return new DarsadKharidMoshtarianDetalisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarsadKharidMoshtarianDetalisViewHolder holder, int position) {
        if (position % 2 == 0){
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        holder.model.setText(dataItemList.get(position).getModel());
        holder.tedad.setText(String.valueOf(dataItemList.get(position).getFldOrderProductAmount()));
        holder.tarikhSefaresh.setText(dataItemList.get(position).getDateTimeShamsi());
        holder.shomareSefaresh.setText(String.valueOf(dataItemList.get(position).getFldOrderNumber()));
        holder.daste.setText(dataItemList.get(position).getFldKindOperationNameFarsi());


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class DarsadKharidMoshtarianDetalisViewHolder extends RecyclerView.ViewHolder{

        TextView shomareSefaresh , tarikhSefaresh , model , tedad  , daste;
        LinearLayout linearLayout;

        public DarsadKharidMoshtarianDetalisViewHolder(@NonNull View itemView) {
            super(itemView);

            shomareSefaresh = itemView.findViewById(R.id.TV_darsadKharidMoshtari_details_shomareSefaresh);
            tarikhSefaresh = itemView.findViewById(R.id.TV_darsadKharidMoshtari_details_tarikhSefaresh);
            model = itemView.findViewById(R.id.TV_darsadKharidMoshtari_details_model);
            tedad = itemView.findViewById(R.id.TV_darsadKharidMoshtari_details_tedad);
            daste = itemView.findViewById(R.id.TV_darsadKharidMoshtari_details_daste);
            linearLayout = itemView.findViewById(R.id.ss);
        }
    }
}
