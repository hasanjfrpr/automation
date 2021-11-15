package com.dayrayaneh.automation.adapter.pishkhan.darsadKharidShahrestan;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.DataItem;
import com.dayrayaneh.automation.utils.Utils;
import com.dayrayaneh.automation.viewModel.pishkhan.darsadKharidShahrestan.DarsadKharidShahrestanViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DarsadKharidShahrestanAdapter extends RecyclerView.Adapter<DarsadKharidShahrestanAdapter.DarsadKharidShahrestanViewHolder>  {

    private List<DataItem> dataItemList = new ArrayList<>();
    private Context context ;

    public DarsadKharidShahrestanAdapter(List<DataItem> dataItemList, Context context) {
        this.dataItemList = dataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public DarsadKharidShahrestanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_darsad_kharid_shahrestan,parent,false);
        return new DarsadKharidShahrestanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarsadKharidShahrestanViewHolder holder, int position) {



        holder.darsadForoosh.setText(Utils.formatPersianNumber(dataItemList.get(position).getPercentPercentOfAll()));
        holder.tedadKol.setText(String.valueOf(dataItemList.get(position).getPercentTotalCountSell()));
        holder.kharidKol.setText(Utils.formatPersianNumber(dataItemList.get(position).getPercentTotalPriceSell()));
        holder.shahr.setText(dataItemList.get(position).getCity());

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class DarsadKharidShahrestanViewHolder extends RecyclerView.ViewHolder{

        TextView shahr , kharidKol , tedadKol , darsadForoosh;
        public DarsadKharidShahrestanViewHolder(@NonNull View itemView) {
            super(itemView);
            shahr = itemView.findViewById(R.id.TV_item_darsadKharidShahrtestan_shahr);
            kharidKol = itemView.findViewById(R.id.TV_item_darsadKharidShahrestan_kharidKol);
            tedadKol = itemView.findViewById(R.id.TV_item_darsadKharidShahrestan_tedadKol);
            darsadForoosh = itemView.findViewById(R.id.TV_item_darsadKharidShahrestan_darsadKharidMoshtariAzForooshKol);
        }
    }


}
