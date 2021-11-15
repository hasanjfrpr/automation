package com.dayrayaneh.automation.adapter.pishkhan.darsadThakhfifAzHarSefaresh;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.darsadThakhfifAzHarSefaresh.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class DarsadTakhfifAzHarSefareshAdapter extends RecyclerView.Adapter<DarsadTakhfifAzHarSefareshAdapter.DarsadTakhfifAzHarSefareshViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public DarsadTakhfifAzHarSefareshAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public DarsadTakhfifAzHarSefareshViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_darsad_thakhfif_az_har_sefaresh,parent , false);
        return new DarsadTakhfifAzHarSefareshViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarsadTakhfifAzHarSefareshViewHolder holder, int position) {

        holder.majmoTakhfif.setText(Utils.formatPersianNumber(dataItemList.get(position).getTotalDiscount()));
        holder.darsadTakhfif.setText(Utils.formatPersianNumber(dataItemList.get(position).getPercentDiscount()));
        holder.nameMahsol.setText(dataItemList.get(position).getFldKindOperationNameFarsi());
        holder.majmoForosh.setText(Utils.formatPersianNumber(dataItemList.get(position).getTotalSell()));

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class DarsadTakhfifAzHarSefareshViewHolder extends RecyclerView.ViewHolder{

        TextView nameMahsol , darsadTakhfif , majmoTakhfif , majmoForosh;

        public DarsadTakhfifAzHarSefareshViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMahsol = itemView.findViewById(R.id.TV_item_darsadKharidAzHarSefaresh_nameMahsol);
            majmoTakhfif = itemView.findViewById(R.id.TV_item_darsadThakhfifAzHarSefaresh_mjmoTakhfif);
            darsadTakhfif = itemView.findViewById(R.id.TV_item_darsadTakhfifAzHarSefaresh_darsadTakhfif);
            majmoForosh = itemView.findViewById(R.id.TV_item_darsadTakhfifAzHarSefaresh_majmoForoosh);
        }
    }
}
