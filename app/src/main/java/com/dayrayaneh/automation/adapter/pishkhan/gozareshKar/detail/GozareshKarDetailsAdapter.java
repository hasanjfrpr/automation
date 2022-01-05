package com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.detail;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.DataItem;

import java.util.ArrayList;
import java.util.List;

public class GozareshKarDetailsAdapter extends RecyclerView.Adapter<GozareshKarDetailsAdapter.GozareshKarDetailsViewHolder>  {

    private Context context;
    private List<DataItem> dataItems = new ArrayList<>();
    private List<DataItem> dataItemsFull = new ArrayList<>();


    public GozareshKarDetailsAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public GozareshKarDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gozareshkar_details , parent , false);
        return new GozareshKarDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GozareshKarDetailsViewHolder holder, int position) {
        final boolean[] isOpen = new boolean[dataItems.size()];
        final boolean[] isOpenJoz = new boolean[dataItems.size()];

        for (int i = 0; i < dataItems.size(); i++) {
            isOpen[i] = true;
            isOpenJoz[i] = true;

        }
        holder.sendTo.setText(dataItems.get(position).getFullName());
        holder.noeKar.setText(dataItems.get(position).getFldKindWorkNameL1());

        holder.starttime.setText(dataItems.get(position).getFldReportTimeStart());
        holder.endTime.setText(dataItems.get(position).getFldReportTimeEnd());
        holder.totaltime.setText(String.valueOf(dataItems.get(position).getTotalTime()));



        if (position % 2 == 0) {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.lightYellow));
        } else {
            holder.linearLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));
        }

        if (dataItems.get(position).getFldReportSharhKarNameL1().length() > 35) {
            holder.showMore.setVisibility(View.VISIBLE);
            holder.sharhKar.setVisibility(View.GONE);
            holder.frameLayout.setVisibility(View.VISIBLE);
            holder.sharhKar2.setText(dataItems.get(position).getFldReportSharhKarNameL1());
            holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
            holder.showMore.setOnClickListener(v -> {
                if (isOpen[position]) {
                    holder.frameLayout.setVisibility(View.GONE);
                    holder.sharhKar2.setText(dataItems.get(position).getFldReportSharhKarNameL1());
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                    isOpen[position] = false;

                } else {
                    holder.frameLayout.setVisibility(View.VISIBLE);
                    holder.sharhKar2.setText(dataItems.get(position).getFldReportSharhKarNameL1());
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                    isOpen[position] = true;

                }
            });

        } else {
            holder.showMore.setVisibility(View.VISIBLE);
            holder.showMore.setVisibility(View.GONE);
            holder.frameLayout.setVisibility(View.GONE);
            holder.sharhKar.setText(dataItems.get(position).getFldReportSharhKarNameL1());
        }


        ////////////

        if (dataItems.get(position).getFldReportSharhKarDetailsNameL1() == null || dataItems.get(position).getFldReportSharhKarDetailsNameL1().equals("")){
            holder.joziatKar.setText("ندارد");
            holder.frameJoziat.setVisibility(View.GONE);
            holder.joziatMore.setVisibility(View.GONE);
        }else {
            if (dataItems.get(position).getFldReportSharhKarDetailsNameL1().length() > 35) {
                holder.joziatMore.setVisibility(View.VISIBLE);
                holder.joziatKar.setVisibility(View.GONE);
                holder.frameJoziat.setVisibility(View.VISIBLE);
                holder.joziatKar2.setText(dataItems.get(position).getFldReportSharhKarDetailsNameL1());
                holder.joziatMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                holder.joziatMore.setOnClickListener(v -> {
                    if (isOpenJoz[position]) {
                        holder.frameJoziat.setVisibility(View.GONE);
                        holder.joziatKar2.setText(dataItems.get(position).getFldReportSharhKarDetailsNameL1());
                        holder.joziatMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                        isOpenJoz[position] = false;

                    } else {
                        holder.frameJoziat.setVisibility(View.VISIBLE);
                        holder.joziatKar2.setText(dataItems.get(position).getFldReportSharhKarDetailsNameL1());
                        holder.joziatMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                        isOpenJoz[position] = true;

                    }
                });

            } else {
                holder.joziatMore.setVisibility(View.VISIBLE);
                holder.joziatMore.setVisibility(View.GONE);
                holder.frameJoziat.setVisibility(View.GONE);
                holder.joziatKar.setText(dataItems.get(position).getFldReportSharhKarDetailsNameL1());
            }

        }



    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }



    public class GozareshKarDetailsViewHolder extends  RecyclerView.ViewHolder{

        TextView sendTo , sharhKar,sharhKar2 , noeKar , starttime, endTime , totaltime , joziatKar , joziatKar2;
        ImageView showMore , joziatMore;
        FrameLayout frameLayout , frameJoziat;
        LinearLayout linearLayout;

        public GozareshKarDetailsViewHolder(@NonNull View itemView) {
            super(itemView);

            sendTo = itemView.findViewById(R.id.TV_pishkhan_gozareshkar_detail_sendTo);
            sharhKar = itemView.findViewById(R.id.TV_item_pishkhan_gozareshkar_detail_sharhKar);
            sharhKar2 = itemView.findViewById(R.id.TV_item_pishkhan_gozareshKar_detail_sharhKar2);
            noeKar = itemView.findViewById(R.id.TV_pishkhan_gozareshkar_detail_noeKar);
            starttime = itemView.findViewById(R.id.TV_gozareshKar_details_startTime);
            endTime = itemView.findViewById(R.id.TV_gozareshKar_details_endTime);
            totaltime = itemView.findViewById(R.id.TV_gozareshKar_details_totalTime);
            showMore = itemView.findViewById(R.id.IV_item_pishkhan_gozareshkar_detail_sharh_more);
            joziatMore = itemView.findViewById(R.id.IV_item_pishkhan_gozareshkar_detail_joziat_more);
            frameLayout = itemView.findViewById(R.id.frame_more_GozareshKar_sharhkar);
            frameJoziat = itemView.findViewById(R.id.frame_more_GozareshKar_joziat);
            joziatKar = itemView.findViewById(R.id.TV_pishkhan_gozareshkar_detail_joziatKar);
            joziatKar2 = itemView.findViewById(R.id.TV_pishkhan_gozareshkar_detail_joziatKar2);
            linearLayout = itemView.findViewById(R.id.linearLayout_item_gozareshKar);

        }
    }


}
