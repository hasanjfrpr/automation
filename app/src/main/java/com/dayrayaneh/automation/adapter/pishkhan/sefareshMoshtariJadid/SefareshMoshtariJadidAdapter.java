package com.dayrayaneh.automation.adapter.pishkhan.sefareshMoshtariJadid;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.sefareshMoshtariJadid.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class SefareshMoshtariJadidAdapter extends RecyclerView.Adapter<SefareshMoshtariJadidAdapter.SefareshMoshtariJadidViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();
    private boolean isOpen =true;

    public SefareshMoshtariJadidAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public SefareshMoshtariJadidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sefaresh_moshtari_jadid,parent,false);
        return new SefareshMoshtariJadidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SefareshMoshtariJadidViewHolder holder, int position) {


        holder.nameMoshtari.setText(dataItemList.get(position).getNewCustomerFullName());
        holder.codeHesab.setText(dataItemList.get(position).getNewCustomerMoshtarianCodeHesab());
        holder.mablaqSefaresh.setText(Utils.formatPersianNumber(dataItemList.get(position).getNewCustomerOrderPrice()));
        holder.shomareSefaresh.setText(Utils.formatPersianNumber(dataItemList.get(position).getNewcustomerOrderNumber()));

        if(dataItemList.get(position).getNewCustomerKindKhadamatNameL1().length() < 35){
            holder.showMore.setVisibility(View.GONE);
            holder.frameLayout.setVisibility(View.GONE);
            holder.status.setText(dataItemList.get(position).getNewCustomerKindKhadamatNameL1());
        }else {
            holder.showMore.setVisibility(View.VISIBLE);
            holder.status.setVisibility(View.GONE);
            holder.frameLayout.setVisibility(View.VISIBLE);
            holder.status2.setText(dataItemList.get(position).getNewCustomerKindKhadamatNameL1());
            holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
            holder.showMore.setOnClickListener(v -> {
               if (isOpen){
                   holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                   holder.frameLayout.setVisibility(View.GONE);
                   isOpen = false;

               }else {
                   holder.frameLayout.setVisibility(View.VISIBLE);
                   holder.status2.setText(dataItemList.get(position).getNewCustomerKindKhadamatNameL1());
                   holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                   isOpen = true;
               }
            });


        }




    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }


    public class SefareshMoshtariJadidViewHolder extends RecyclerView.ViewHolder{

        TextView nameMoshtari, codeHesab , mablaqSefaresh , shomareSefaresh , status , status2;
        FrameLayout frameLayout;
        ImageView showMore;

        public SefareshMoshtariJadidViewHolder(@NonNull View itemView) {
            super(itemView);

            nameMoshtari = itemView.findViewById(R.id.TV_item_pishkhan_sefareshMoshtariJadid_nameMoshtari);
            codeHesab = itemView.findViewById(R.id.TV_item_pishkhan_sefareshMoshtariJadid_codeHesab);
            mablaqSefaresh = itemView.findViewById(R.id.TV_item_pishkhan_sefareshMoshtariJadid_mablaqSefaresh);
            shomareSefaresh = itemView.findViewById(R.id.TV_item_pishkhan_sefareshMoshtariJadid_shomareHesab);
            status = itemView.findViewById(R.id.TV_item_pishkhan_sefareshMoshtariJadid_status);
            status2 = itemView.findViewById(R.id.TV_item_pishkhan_sefareshMoshtariJadid_status2);
            frameLayout = itemView.findViewById(R.id.frame_moreContent_sefareshmoshtari_status);
            showMore = itemView.findViewById(R.id.IV_showMore_sefareshMoshtarijadid);


        }
    }
}
