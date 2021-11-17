package com.dayrayaneh.automation.adapter.pishkhan.forooshSakhtAzar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ForooshSakhtAfzarAdapter extends RecyclerView.Adapter<ForooshSakhtAfzarAdapter.ForooshSakhtAfzarViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public ForooshSakhtAfzarAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public ForooshSakhtAfzarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_foroosh_sakht_afzar,parent,false);
        return new ForooshSakhtAfzarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForooshSakhtAfzarViewHolder holder, int position) {

        holder.nameMahsol.setText(dataItemList.get(position).getFldModelCategoryNameL1());
        holder.modelMahsol.setText(dataItemList.get(position).getFldProductModelModel());
        holder.mablaq.setText(Utils.formatPersianNumber(dataItemList.get(position).getTotalPriceSellHard()));
        holder.tedad.setText(Utils.formatPersianNumber(dataItemList.get(position).getTotalCountSellHard()));
        holder.mianginQaymat.setText(Utils.formatPersianNumber(dataItemList.get(position).getAvgAmount()));

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class ForooshSakhtAfzarViewHolder extends RecyclerView.ViewHolder{
        TextView nameMahsol , tedad , mianginQaymat ,modelMahsol , mablaq;
        public ForooshSakhtAfzarViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMahsol = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_nameMahsool);
            tedad = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_tedadForoosh);
            mianginQaymat = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_mianginQaymat);
            modelMahsol = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_modelMahsol);
            mablaq = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_mablaq);
        }
    }
}
