package com.dayrayaneh.automation.adapter.pishkhan.pishkhan_bazaryabi;

import android.content.Context;
import android.icu.text.Edits;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.BazaryabiMainModel;
import com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.count.DataItem;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BazaryabiMainAdapter extends RecyclerView.Adapter<BazaryabiMainAdapter.BazaryabiMainViewHolder> {

    private Context context;
    private List<DataItem> bazaryabiMainModelList = new ArrayList<>();
    public BazarabiMainEvent event;

    public BazaryabiMainAdapter(Context context, List<DataItem> bazaryabiMainModelList) {
        this.context = context;
        this.bazaryabiMainModelList = bazaryabiMainModelList;
    }

    @NonNull
    @Override
    public BazaryabiMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bazaryabi_main_list,parent,false);
        return new BazaryabiMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BazaryabiMainViewHolder holder, int position) {


        if(position%2==0){
            holder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.gray));

        }else {
            holder.frameLayout.setBackgroundColor(context.getResources().getColor(R.color.graylight));
        }


        holder.personalName.setText(bazaryabiMainModelList.get(position).getPersonel());
        holder.sellNumber.setText(String.valueOf(bazaryabiMainModelList.get(position).getProformaCount())+" / "+String.valueOf(bazaryabiMainModelList.get(position).getDetailsCount()));

        holder.itemView.setOnClickListener(v -> {
            event.onclickItemMainBazaryabi(bazaryabiMainModelList.get(position).getPersonCode() , bazaryabiMainModelList.get(position).getPersonel());
        });

    }

    @Override
    public int getItemCount() {
        return bazaryabiMainModelList.size();
    }

    public class BazaryabiMainViewHolder extends RecyclerView.ViewHolder{

        TextView personalName , sellNumber;
        FrameLayout frameLayout;

        public BazaryabiMainViewHolder(@NonNull View itemView) {
            super(itemView);
            personalName = itemView.findViewById(R.id.TV_item_bazaryabi_main_personal);
            sellNumber = itemView.findViewById(R.id.TV_item_bazaryabi_main_tedadBazaryabi);
            frameLayout = itemView.findViewById(R.id.frame_item_bazaryabi);
        }
    }

public interface BazarabiMainEvent{
        void onclickItemMainBazaryabi(int personelCode , String personalName);
}

}
