package com.dayrayaneh.automation.adapter.pishkhan.tamdidGharardad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.tamdidQarardad.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TamdidGharardadAdapter extends RecyclerView.Adapter<TamdidGharardadAdapter.TamdidGharardadViewHoler> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public TamdidGharardadAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public TamdidGharardadViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_tamdid_gharardad,parent,false);
        return new TamdidGharardadViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TamdidGharardadViewHoler holder, int position) {

        holder.mablagh_gharadad.setText(Utils.formatPersianNumber(dataItemList.get(position).getMablaghGharardad()));
        holder.noeGharardad.setText(dataItemList.get(position).getFldKhadamatGharardadKindNameL1());
        holder.tedadGharardad.setText(Utils.formatPersianNumber(dataItemList.get(position).getCountGharardad()));
        holder.nameMahsol.setText(dataItemList.get(position).getTypeNameGharardad());
    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class TamdidGharardadViewHoler extends RecyclerView.ViewHolder{

        TextView nameMahsol , tedadGharardad , noeGharardad , mablagh_gharadad;
        public TamdidGharardadViewHoler(@NonNull View itemView) {
            super(itemView);

            nameMahsol = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGharardad_nameMahsol);
            tedadGharardad = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGharardad_tedadGharardad);
            noeGharardad = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGharardad_noeGharardad);
            mablagh_gharadad = itemView.findViewById(R.id.TV_item_pishkhan_tamdidGharardad_mablagh_Gharardad);
        }
    }
}
