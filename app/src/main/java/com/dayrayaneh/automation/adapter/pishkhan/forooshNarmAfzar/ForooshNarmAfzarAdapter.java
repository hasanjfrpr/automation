package com.dayrayaneh.automation.adapter.pishkhan.forooshNarmAfzar;



import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.forooshNarmAfzar.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ForooshNarmAfzarAdapter extends RecyclerView.Adapter<ForooshNarmAfzarAdapter.ForooshNarmAfzarViewHolder> {

    private List<DataItem> dataItemList = new ArrayList<>();
    private Context context;

    public ForooshNarmAfzarAdapter(List<DataItem> dataItemList, Context context) {
        this.dataItemList = dataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ForooshNarmAfzarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_foroosh_narmafzar_main,parent,false );
        return new ForooshNarmAfzarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForooshNarmAfzarViewHolder holder, int position) {


        holder.jamForoosh.setText(Utils.formatPersianNumber(dataItemList.get(position).getTotalPriceSellsoft()));
        holder.tedadForoosh.setText(Utils.formatPersianNumber(dataItemList.get(position).getTotalCountSellSoft()));
        holder.mianginQaymat.setText(Utils.formatPersianNumber(dataItemList.get(position).getAvgAmount()));
        holder.nameNarmAfzar.setText(dataItemList.get(position).getFldKindOperationNameFarsi());


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class ForooshNarmAfzarViewHolder extends RecyclerView.ViewHolder{


        TextView nameNarmAfzar , mianginQaymat , tedadForoosh , jamForoosh;

        public ForooshNarmAfzarViewHolder(@NonNull View itemView) {
            super(itemView);
            nameNarmAfzar = itemView.findViewById(R.id.TV_item_forooshNarmAfzar_nameNarmAfzar);
            mianginQaymat = itemView.findViewById(R.id.TV_item_forooshNarmAfzar_mianginQaymat);
            tedadForoosh = itemView.findViewById(R.id.TV_item_forooshNarmAfzar_tedadForoosh);
            jamForoosh = itemView.findViewById(R.id.TV_item_forooshNarmAfzar_jamForoosh);
        }
    }
}
