package com.dayrayaneh.automation.adapter.pishkhan.hokmKar.followers;

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
import com.dayrayaneh.automation.model.pishkhan.HokmKar.followers.DataItem;

import java.util.ArrayList;
import java.util.List;

public class HokmKarFollowersAdapter extends RecyclerView.Adapter<HokmKarFollowersAdapter.HokmkarFollowerViewHolder> {

    private Context context;
    private List<DataItem> dataItems = new ArrayList<>();



    public HokmKarFollowersAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;

    }

    @NonNull
    @Override
    public HokmkarFollowerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_hokm_kar_detail_followers , parent , false);
        return new HokmkarFollowerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HokmkarFollowerViewHolder holder, int position) {
        boolean[] isOpenE = new boolean[dataItems.size()];
        boolean[] isOpenD = new boolean[dataItems.size()];
        for (int i = 0; i < dataItems.size(); i++) {
            isOpenE[i] = true;
            isOpenD[i] = true;
        }

        holder.status.setText(dataItems.get(position).getKindName());
        holder.personal.setText(dataItems.get(position).getPersonel());

        holder.shE.setVisibility(View.VISIBLE);
        holder.showSharhErja.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
        if ( dataItems.get(position).getDoDesc() == null ){
            holder.sharhErja.setText("ندارد");
        }else {
            holder.sharhErja.setText(dataItems.get(position).getDoDesc());
        }

        holder.shDe.setVisibility(View.VISIBLE);
        holder.showDescription.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
        if ( dataItems.get(position).getDesc() == null ){
            holder.description.setText("ندارد");
        }else {
            holder.description.setText(dataItems.get(position).getDesc());
        }


        holder.showSharhErja.setOnClickListener(v -> {
          if (isOpenE[position]){
              holder.shE.setVisibility(View.GONE);
              holder.showSharhErja.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
              isOpenE[position] = false;
          }else {

              holder.shE.setVisibility(View.VISIBLE);
              holder.showSharhErja.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
              if ( dataItems.get(position).getDoDesc() == null ){
                  holder.sharhErja.setText("ندارد");
              }else {
                  holder.sharhErja.setText(dataItems.get(position).getDoDesc());
              }
              isOpenE[position] = true;

          }
        });
        holder.showDescription.setOnClickListener(v -> {
            if (isOpenD[position]){
                holder.shDe.setVisibility(View.GONE);
                holder.showDescription.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                isOpenD[position] = false;
            }else {


                holder.shDe.setVisibility(View.VISIBLE);
                holder.showDescription.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                if ( dataItems.get(position).getDesc() == null ){
                    holder.description.setText("ندارد");
                }else {
                    holder.description.setText(dataItems.get(position).getDesc());
                }
                isOpenD[position] = true;

            }
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class HokmkarFollowerViewHolder extends RecyclerView.ViewHolder{

        TextView status , personal , sharhErja , description;
        ImageView showSharhErja , showDescription;
        FrameLayout shE , shDe;

        public HokmkarFollowerViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_detail_status);
            personal = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_detail_personal);
            sharhErja = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_detail_sharheErja);
            description = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_detail_description);
            showDescription = itemView.findViewById(R.id.IV_item_hokmKar_detail_showDescription);
            showSharhErja = itemView.findViewById(R.id.IV_item_hokmKar_detail_showSharhErja);
            shE  = itemView.findViewById(R.id.frame_show_sharhErja_hokmKar_detail_followers);
            shDe = itemView.findViewById(R.id.frame_show_description_hokmKar_detail_followers);
        }
    }
}
