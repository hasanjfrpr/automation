package com.dayrayaneh.automation.adapter.pishkhan.darsadSefareshat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.darsadSefareshat.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DarsadSefareshatAdapter  extends RecyclerView.Adapter<DarsadSefareshatAdapter.DarsadSefareshatViewHolder> {

    private List<DataItem> dataItemList = new ArrayList<>();
    private Context context;

    public DarsadSefareshatAdapter(List<DataItem> dataItemList, Context context) {
        this.dataItemList = dataItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public DarsadSefareshatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_darsad_sefareshat,parent,false);
        return new DarsadSefareshatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DarsadSefareshatViewHolder holder, int position) {

        holder.vaziatSefaresh.setText(dataItemList.get(position).getFldKindOperationNameFarsi());
        holder.darsad.setText(Utils.formatPersianNumber(dataItemList.get(position).getPercentOf()));

    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class DarsadSefareshatViewHolder extends RecyclerView.ViewHolder{

        TextView darsad , vaziatSefaresh;

        public DarsadSefareshatViewHolder(@NonNull View itemView) {
            super(itemView);

            darsad = itemView.findViewById(R.id.TV_item_darsadSefareshat_darsad);
            vaziatSefaresh = itemView.findViewById(R.id.TV_item_darsadSefareshat_vaziatSefaresh);
        }
    }
}
