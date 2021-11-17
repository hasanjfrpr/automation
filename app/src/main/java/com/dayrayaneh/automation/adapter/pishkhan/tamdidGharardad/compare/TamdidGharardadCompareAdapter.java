package com.dayrayaneh.automation.adapter.pishkhan.tamdidGharardad.compare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.compare.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TamdidGharardadCompareAdapter extends RecyclerView.Adapter<TamdidGharardadCompareAdapter.TamdidGharardadCompareViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public TamdidGharardadCompareAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public TamdidGharardadCompareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_tamdid_gharardad_compare,parent,false);
        return new TamdidGharardadCompareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TamdidGharardadCompareViewHolder holder, int position) {

        if (dataItemList.get(position).getTbl1TypeNameGharardad() == null) {
            holder.nameNarmafzar.setText(dataItemList.get(position).getTbl2TypeNameGharardad());
        }else { holder.nameNarmafzar.setText(dataItemList.get(position).getTbl1TypeNameGharardad());}

        if (dataItemList.get(position).getTbl2FldKhadamatGharardadKindNameL1() == null) {
            holder.noeService1.setText(dataItemList.get(position).getTbl1FldKhadamatGharardadKindNameL1());
        }else {  holder.noeService1.setText(dataItemList.get(position).getTbl2FldKhadamatGharardadKindNameL1());;}

        holder.tedad1.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl1CountGharardad()));
        holder.tedad2.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl2CountGharardad()));



        holder.mablaq1.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl1MablaghGharardad()));
        holder.mablaq2.setText(Utils.formatPersianNumber(dataItemList.get(position).getTbl2MablaghGharardad()));

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class TamdidGharardadCompareViewHolder extends RecyclerView.ViewHolder{
        TextView nameNarmafzar , noeService1 , tedad1 ,tedad2, mablaq1 , mablaq2;
        public TamdidGharardadCompareViewHolder(@NonNull View itemView) {
            super(itemView);
            nameNarmafzar = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGharardad_compare_nameNarmAfzar);
            noeService1 = itemView.findViewById(R.id.TV_item_pishkhan_forooshNarmAfzar_compare_noeService1);
            tedad1 = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGhardad_compare_tedad1);
            tedad2 = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGharardad_compare_tedad2);
            mablaq1 = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGhardad_compare_mablaq1);
            mablaq2 = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGhardad_compare_mablaq2);
        }
    }
}
