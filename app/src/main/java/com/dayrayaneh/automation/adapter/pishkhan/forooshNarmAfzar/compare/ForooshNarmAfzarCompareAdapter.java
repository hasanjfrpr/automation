package com.dayrayaneh.automation.adapter.pishkhan.forooshNarmAfzar.compare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.compare.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ForooshNarmAfzarCompareAdapter extends RecyclerView.Adapter<ForooshNarmAfzarCompareAdapter.ForooshNarmAfzarCompareViewHolder>{

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public ForooshNarmAfzarCompareAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public ForooshNarmAfzarCompareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_foroosh_narm_afzar_compare  , parent , false);
        return new ForooshNarmAfzarCompareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForooshNarmAfzarCompareViewHolder holder, int position) {

        if(dataItemList.get(position).getTbl1FldKindOperationNameFarsi() == null){
            holder.nameMasol.setText(dataItemList.get(position).getTbl2FldKindOperationNameFarsi());
        }else {
            holder.nameMasol.setText(dataItemList.get(position).getTbl1FldKindOperationNameFarsi());
        }


        holder.tedad1.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl1TotalCountSellSoft()));
        holder.tedad2.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl2TotalCountSellSoft()));
        holder.jameforoosh1.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl1TotalPriceSellsoft()));
        holder.Jameforoosh2.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl2TotalPriceSellsoft()));
        holder.mianginQaymat1.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl1AvgAmount()));
        holder.mianginQaymat2.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl2AvgAmount()));

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class ForooshNarmAfzarCompareViewHolder extends RecyclerView.ViewHolder{
        TextView nameMasol , tedad1 , tedad2 , jameforoosh1 , Jameforoosh2 , mianginQaymat1 , mianginQaymat2;
        public ForooshNarmAfzarCompareViewHolder(@NonNull View itemView) {
            super(itemView);
            nameMasol = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_nameMahsool);
            tedad1 = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_tedadForoosh1);
            tedad2 = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_tedadForoosh2);
            jameforoosh1 = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_jameForosh1);
            Jameforoosh2 = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_jameForosh2);
            mianginQaymat1 = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_mianginQayat1);
            mianginQaymat2 = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_mianginQayat2);
        }
    }
}
