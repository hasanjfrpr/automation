package com.dayrayaneh.automation.adapter.pishkhan.sefareshMoshtariJadid;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.vaziatSefareshat.DataItem;

import java.util.ArrayList;
import java.util.List;

public class SefareshMoshtariJadidAdapter extends RecyclerView.Adapter<SefareshMoshtariJadidAdapter.SefareshMoshtariJadidViewHolder> {

    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();

    public SefareshMoshtariJadidAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public SefareshMoshtariJadidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_vaziat_sefareshat,parent,false);
        return new SefareshMoshtariJadidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SefareshMoshtariJadidViewHolder holder, int position) {






    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }


    public class SefareshMoshtariJadidViewHolder extends RecyclerView.ViewHolder{



        public SefareshMoshtariJadidViewHolder(@NonNull View itemView) {
            super(itemView);



        }
    }
}
