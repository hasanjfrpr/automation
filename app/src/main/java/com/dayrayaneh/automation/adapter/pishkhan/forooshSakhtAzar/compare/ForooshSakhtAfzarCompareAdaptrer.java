package com.dayrayaneh.automation.adapter.pishkhan.forooshSakhtAzar.compare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.compare.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ForooshSakhtAfzarCompareAdaptrer  extends RecyclerView.Adapter<ForooshSakhtAfzarCompareAdaptrer.ForooshSakhtAfzarCompareViewHolder> {


    private Context context;
    private List<com.dayrayaneh.automation.model.pishkhan.forooshSakhtAfzar.compare.DataItem> itemList = new ArrayList<>();

    public ForooshSakhtAfzarCompareAdaptrer(Context context, List<DataItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ForooshSakhtAfzarCompareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_foroosh_sakht_afzar_compare,parent , false);
        return new ForooshSakhtAfzarCompareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForooshSakhtAfzarCompareViewHolder holder, int position) {

        if (itemList.get(position).getTbl1FldModelCategoryNameL1() == null){
            holder.nameMahsol.setText(itemList.get(position).getTbl2FldModelCategoryNameL1());
        }else {
            holder.nameMahsol.setText(itemList.get(position).getTbl1FldModelCategoryNameL1());
        }

        if (itemList.get(position).getTbl1FldProductModelModel() == null){
            holder.modelMahsol.setText(itemList.get(position).getTbl2FldProductModelModel());
        }else{holder.modelMahsol.setText(itemList.get(position).getTbl1FldProductModelModel());}

        holder.mianginQaymat1.setText(Utils.formatPersianNumber(itemList.get(position).getTbl1AvgAmount()));
        holder.mianginQaymat2.setText(Utils.formatPersianNumber(itemList.get(position).getTbl2AvgAmount()));

        holder.tedadForoosh1.setText(Utils.formatPersianNumber(itemList.get(position).getTbl1TotalCountSellHard()));
        holder.tedadForoosh2.setText(Utils.formatPersianNumber(itemList.get(position).getTbl2TotalCountSellHard()));

        holder.forooshKol1.setText(Utils.formatPersianNumber(itemList.get(position).getTbl1TotalPriceSellHard()));
        holder.forooshKol2.setText(Utils.formatPersianNumber(itemList.get(position).getTbl2TotalPriceSellHard()));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ForooshSakhtAfzarCompareViewHolder extends RecyclerView.ViewHolder{

        TextView nameMahsol , modelMahsol , tedadForoosh1 , tedadForoosh2 , forooshKol1 , forooshKol2 , mianginQaymat1 , mianginQaymat2 ;
        public ForooshSakhtAfzarCompareViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMahsol = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_nameMahsool);
            modelMahsol = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_modelMahsol);
            tedadForoosh1 = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_tedadForoosh1);
            tedadForoosh2 = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_tedadForoosh2);
            forooshKol1 = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_jameForosh1);
            forooshKol2 = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_jameForosh2);
            mianginQaymat1 = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_mianginQayat1);
            mianginQaymat2 = itemView.findViewById(R.id.TV_item_pishkhan_forooshSakhtAfzar_compare_mianginQayat2);

        }
    }

}
