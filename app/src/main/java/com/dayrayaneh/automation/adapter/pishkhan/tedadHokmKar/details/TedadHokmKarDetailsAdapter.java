package com.dayrayaneh.automation.adapter.pishkhan.tedadHokmKar.details;

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
import com.dayrayaneh.automation.model.pishkhan.tedadHokmKarha.details.DataItem;

import java.util.ArrayList;
import java.util.List;

public class TedadHokmKarDetailsAdapter extends RecyclerView.Adapter<TedadHokmKarDetailsAdapter.TedadHokmKarDetailsViewHolder> {

    private Context context;
    private List<DataItem> dataItems = new ArrayList<>();
    private boolean isOpen = true;

    public TedadHokmKarDetailsAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }



    @NonNull
    @Override
    public TedadHokmKarDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_tedad_hokmkar_details , parent , false);
        return new TedadHokmKarDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TedadHokmKarDetailsViewHolder holder, int position) {

        holder.status.setText(dataItems.get(position).getFldKindOperationNameFarsi());
        holder.shomareHokm.setText(String.valueOf(dataItems.get(position).getFldHokmKarNumber()));
        holder.time.setText(dataItems.get(position).getFldHokmFollowTime());
        holder.date.setText(dataItems.get(position).getFldHokmFollowDateEnd());
        holder.serial.setText(String.valueOf(dataItems.get(position).getSerial()));
        holder.erjadahande.setText(dataItems.get(position).getErjaDahande());



            if (dataItems.get(position).getFldHokmFollowSharhErjaL1().length() < 35){
                holder.sharh.setText(dataItems.get(position).getFldHokmFollowSharhErjaL1());
                holder.moreBtn_sharh.setVisibility(View.GONE);
                holder.showHide_sharh.setVisibility(View.GONE);
            }else {
                holder.moreBtn_sharh.setVisibility(View.VISIBLE);
                holder.sharh.setVisibility(View.GONE);
                holder.moreBtn_sharh.setVisibility(View.VISIBLE);
                holder.showHide_sharh.setVisibility(View.VISIBLE);
                holder.moreBtn_sharh.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                holder.sharh2.setText(dataItems.get(position).getFldHokmFollowSharhErjaL1());
                holder.moreBtn_sharh.setOnClickListener(v -> {
                    if (isOpen){
                        holder.moreBtn_sharh.setVisibility(View.VISIBLE);
                        holder.showHide_sharh.setVisibility(View.GONE);
                        holder.moreBtn_sharh.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                        isOpen=false;

                    }else{

                        holder.moreBtn_sharh.setVisibility(View.VISIBLE);
                        holder.showHide_sharh.setVisibility(View.VISIBLE);
                        holder.moreBtn_sharh.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                        holder.sharh2.setText(dataItems.get(position).getFldHokmFollowSharhErjaL1());
                        isOpen = true;
                    }
                });


            }




    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class  TedadHokmKarDetailsViewHolder extends RecyclerView.ViewHolder{

        TextView shomareHokm , time , date , status ,serial, sharh , sharh2 , erjadahande ;
        ImageView moreBtn_sharh ;
        FrameLayout showHide_sharh;

        public TedadHokmKarDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            shomareHokm = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_shomareHokm);
            time = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_time);
            date = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_date);
            status = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_status);
            serial = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_serial);
            sharh = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_sharh);
            sharh2 = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_sharh2);
            erjadahande = itemView.findViewById(R.id.TV_item_pishkhan_tedadHokmKar_detail_erjadahande);
            moreBtn_sharh = itemView.findViewById(R.id.IV_item_pishkhan_tedadHokmKar_detail_sharh_more);
            showHide_sharh = itemView.findViewById(R.id.frame_more_tedadHokmkar_sharh);
        }
    }
}
