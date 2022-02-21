package com.dayrayaneh.automation.adapter.pishkhan.UndoneHokmKar.count;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count.DataItem;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.Count.UnDoneHokmkarCountMode;

import java.util.ArrayList;
import java.util.List;

public class UnDoneHokmkarCountAdapter extends RecyclerView.Adapter<UnDoneHokmkarCountAdapter.UnDoneHokmkarCountViewHolder> {

    private Context context;
    private List<DataItem> modeList = new ArrayList<>();
    public OnItemClick onItemClick;


    public UnDoneHokmkarCountAdapter(Context context, List<DataItem> modeList) {
        this.context = context;
        this.modeList = modeList;
    }

    @NonNull
    @Override
    public UnDoneHokmkarCountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UnDoneHokmkarCountViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main_undon_hokm_kar_count , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UnDoneHokmkarCountViewHolder holder, int position) {

        if (position %2 == 0){
            holder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.whiteYellow));

        }else {
            holder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.graylight));
        }
        holder.hokmCount.setText(String.valueOf(modeList.get(position).getCount()));
        holder.name.setText(String.valueOf(modeList.get(position).getPersonel()));

        holder.itemView.setOnClickListener(view -> {
            onItemClick.OnItemClicked(modeList.get(position).getPCode() , modeList.get(position).getPersonel());
        });
    }

    @Override
    public int getItemCount() {
        return modeList.size();
    }

    class UnDoneHokmkarCountViewHolder extends RecyclerView.ViewHolder{
        TextView name , hokmCount;
        FrameLayout frameLayout;
        public UnDoneHokmkarCountViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.TV_item_UndonHokmkar_count_name);
            hokmCount = itemView.findViewById(R.id.TV_item_UndonHokmkar_count);
            frameLayout=itemView.findViewById(R.id.frame_item_undoneHokm_count);
        }
    }

    public interface  OnItemClick{
        void OnItemClicked(int personId , String name);
    }
}

