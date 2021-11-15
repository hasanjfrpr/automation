package com.dayrayaneh.automation.adapter.pishkhan.pishkan_darsadKharidMoshtarian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DarsadkharidMoshtariModel;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidMoshtari.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DarsadKharidMoshtarianAdapter extends RecyclerView.Adapter<DarsadKharidMoshtarianAdapter.DarsadKharidMoshtarianViewHolder> {

    private List<DataItem> darsadKharidMoshtariModelList = new ArrayList<>();
    private Context context ;

    public DarsadKharidMoshtarianAdapter(List<DataItem> darsadKharidMoshtariModelList, Context context) {
        this.darsadKharidMoshtariModelList = darsadKharidMoshtariModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public DarsadKharidMoshtarianViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_darsad_kharid_moshtarian,parent,false);
        return new DarsadKharidMoshtarianViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarsadKharidMoshtarianViewHolder holder, int position) {


        holder.codeHesab.setText(darsadKharidMoshtariModelList.get(position).getCodeHesab());
        holder.client.setText(darsadKharidMoshtariModelList.get(position).getMoshtariName());
        holder.kharidKol.setText(Utils.formatPersianNumber(darsadKharidMoshtariModelList.get(position).getPercentTotalPriceSell()));
        holder.tedadAqlamKol.setText(String.valueOf(darsadKharidMoshtariModelList.get(position).getPercentTotalCountSell()));
        holder.darsadForooshMoshtari.setText(Utils.formatPersianNumber(darsadKharidMoshtariModelList.get(position).getPercentPercentOfAll()));

    }

    @Override
    public int getItemCount() {
        return darsadKharidMoshtariModelList.size();
    }



    public class DarsadKharidMoshtarianViewHolder extends RecyclerView.ViewHolder{

        TextView client , codeHesab , tedadAqlamKol , kharidKol , darsadForooshMoshtari;

        public DarsadKharidMoshtarianViewHolder(@NonNull View itemView) {
            super(itemView);

            client = itemView.findViewById(R.id.TV_item_darsadKharidMoshtarian_client);
            codeHesab = itemView.findViewById(R.id.TV_item_darsadKharidMoshtarian_codeHesab);
            kharidKol  = itemView.findViewById(R.id.TV_item_darsadKharidMoshtarian_kharidKol);
            tedadAqlamKol = itemView.findViewById(R.id.TV_item_darsadKharidMoshtarian_tedadAqlamKol);
            darsadForooshMoshtari = itemView.findViewById(R.id.TV_item_darsadKharidMoshtarian_darsadKharidMoshtariAzForooshKol);

        }
    }


}
