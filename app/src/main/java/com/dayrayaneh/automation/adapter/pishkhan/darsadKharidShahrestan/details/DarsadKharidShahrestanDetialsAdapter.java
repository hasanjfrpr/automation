package com.dayrayaneh.automation.adapter.pishkhan.darsadKharidShahrestan.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.darsadKharidShahrestan.details.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DarsadKharidShahrestanDetialsAdapter extends RecyclerView.Adapter<DarsadKharidShahrestanDetialsAdapter.DarsadKharidShahrestanDetialsViewHolder>{

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public DarsadKharidShahrestanDetialsAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public DarsadKharidShahrestanDetialsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_darsadkharid_shahrestan_details , parent , false);
        return new DarsadKharidShahrestanDetialsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarsadKharidShahrestanDetialsViewHolder holder, int position) {
        if (position % 2 == 0){
            holder.ll.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }else {
            holder.ll.setBackgroundColor(context.getResources().getColor(R.color.white));
        }


        holder.shomersefaresh.setText(String.valueOf(dataItemList.get(position).getOrderNumber()));
        holder.moshtari.setText(dataItemList.get(position).getFullname());
        holder.model.setText(dataItemList.get(position).getModel());
        holder.tedad.setText(String.valueOf(dataItemList.get(position).getCount()));
        holder.mablaqkol.setText(Utils.formatPersianNumber(dataItemList.get(position).getTotal()));
        holder.qaymat.setText(Utils.formatPersianNumber(dataItemList.get(position).getPrice()));
        holder.group.setText(dataItemList.get(position).getCategory());
        holder.noeMahsol.setText(dataItemList.get(position).getKind());

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class DarsadKharidShahrestanDetialsViewHolder extends RecyclerView.ViewHolder{

        TextView moshtari , shomersefaresh , qaymat , mablaqkol,noeMahsol , group , tedad , model;
        LinearLayout ll;
        public DarsadKharidShahrestanDetialsViewHolder(@NonNull View itemView) {
            super(itemView);

            moshtari = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_moshtari);
            shomersefaresh = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_shomerSefaresh);
            qaymat = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_qaymat);
            mablaqkol = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_mablaqKol);
            noeMahsol = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_noeMahsol);
            group = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_group);
            tedad = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_tedad);
            model = itemView.findViewById(R.id.TV_item_darsadkhardi_shahrestan_detail_model);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
