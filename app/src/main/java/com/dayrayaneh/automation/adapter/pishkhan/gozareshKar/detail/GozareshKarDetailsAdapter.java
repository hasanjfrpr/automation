package com.dayrayaneh.automation.adapter.pishkhan.gozareshKar.detail;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.Gozareshkar.details.DataItem;

import java.util.ArrayList;
import java.util.List;

public class GozareshKarDetailsAdapter extends RecyclerView.Adapter<GozareshKarDetailsAdapter.GozareshKarDetailsViewHolder> {

    private Context context;
    private List<DataItem> dataItems = new ArrayList<>();
    private boolean isOpen  = false;

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
        holder.sendTo.setText(dataItems.get(position).getFullName());
        holder.noeKar.setText(dataItems.get(position).getFldKindWorkNameL1());
        holder.joziatKar.setText(dataItems.get(position).getFldReportSharhKarDetailsNameL1());
        holder.starttime.setText(dataItems.get(position).getFldReportTimeStart());
        holder.endTime.setText(dataItems.get(position).getFldReportTimeEnd());
        holder.totaltime.setText(String.valueOf(dataItems.get(position).getTotalTime()));

        if (dataItems.get(position).getFldReportSharhKarNameL1().length() > 35){
            holder.showMore.setVisibility(View.VISIBLE);
            holder.frameLayout.setVisibility(View.GONE);
            holder.sharhKar.setVisibility(View.GONE);
            holder.showMore.setOnClickListener(v->{
                if (isOpen){
                    holder.frameLayout.setVisibility(View.GONE);
                    holder.sharhKar2.setText(dataItems.get(position).getFldReportSharhKarNameL1());
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                    isOpen = false;

                }else {
                    holder.frameLayout.setVisibility(View.VISIBLE);
                    holder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.graylight));
                    holder.sharhKar2.setText(dataItems.get(position).getFldReportSharhKarNameL1());
                    holder.showMore.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                    isOpen = true;

                }            });

        }else {
            holder.showMore.setVisibility(View.VISIBLE);
            holder.showMore.setVisibility(View.GONE);
            holder.frameLayout.setVisibility(View.GONE);
            holder.sharhKar.setText(dataItems.get(position).getFldReportSharhKarNameL1());
        }
    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class GozareshKarDetailsViewHolder extends  RecyclerView.ViewHolder{

        TextView sendTo , sharhKar,sharhKar2 , noeKar , starttime, endTime , totaltime , joziatKar;
        ImageView showMore;
        FrameLayout frameLayout;

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
            frameLayout = itemView.findViewById(R.id.frame_more_GozareshKar_sharhkar);
            joziatKar = itemView.findViewById(R.id.TV_pishkhan_gozareshkar_detail_joziatKar);

        }
    }
}
