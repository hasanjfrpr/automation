package com.dayrayaneh.automation.adapter.pishkhan.khadamatPoshtibani.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.khadamatPoshtibani.mian.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class KhadamatPoshtibaniMainAdapter extends RecyclerView.Adapter<KhadamatPoshtibaniMainAdapter.KhadamatPoshtibaniMainViewHolder> {


    private Context context;
    private List<DataItem> dataItemList = new ArrayList<>();
    private int isEven=0;
    public ClickItemEvent event;

    public KhadamatPoshtibaniMainAdapter(Context context, List<DataItem> dataItemList) {
        this.context = context;
        this.dataItemList = dataItemList;
    }

    @NonNull
    @Override
    public KhadamatPoshtibaniMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_khadamat_poshtibani_main,parent,false);
        return new KhadamatPoshtibaniMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KhadamatPoshtibaniMainViewHolder holder, int position) {

        holder.namePoshtiban.setText(dataItemList.get(position).getName());
        holder.tedadTamas.setText((dataItemList.get(position).getCallCount()));
        holder.mianginMokaleme.setText(dataItemList.get(position).getAvgTime());
        holder.mianginEmtiaz.setText(String.valueOf(dataItemList.get(position).getPoint()));
        holder.shomareDakheli.setText(dataItemList.get(position).getDakheliNumber());
        holder.modatMokaleme.setText(dataItemList.get(position).getTotalCallTime());




        if (dataItemList.get(position).getKhadamatCount() == null){
            holder.tedadKhadamat.setText("0");
            holder.tedadKhadamat.setTextColor(context.getResources().getColor(R.color.redlight));
        }else {
            holder.tedadKhadamat.setText(dataItemList.get(position).getKhadamatCount());
        }

        if (dataItemList.get(position).getSdCount().equals("")){
            holder.tedadPaygiri.setText("0");
            holder.tedadPaygiri.setTextColor(context.getResources().getColor(R.color.redlight));
        }else {
            holder.tedadPaygiri.setText(dataItemList.get(position).getSdCount());
        }

        if (isEven == 0 ){
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.lightYellow));
            isEven = 1;
        }else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(R.color.graylight));
        }


        holder.itemView.setOnClickListener(v -> {
            event.onclickRecyclerItem(dataItemList.get(position).getUserId());
        });


    }

    @Override
    public int getItemCount() {
        return dataItemList.size();
    }

    public class KhadamatPoshtibaniMainViewHolder extends RecyclerView.ViewHolder{

        TextView namePoshtiban , tedadTamas , modatMokaleme , mianginEmtiaz  , mianginMokaleme, shomareDakheli,tedadKhadamat , tedadPaygiri;

        public KhadamatPoshtibaniMainViewHolder(@NonNull View itemView) {
            super(itemView);

            namePoshtiban = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_namePoshtibani);
            tedadTamas = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_tedadTamas);
            modatMokaleme = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_modatMokaleme);
            mianginEmtiaz = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_mianginEmtiaz);
            mianginMokaleme = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_mianginMokaleme);
            shomareDakheli = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_shomareDakheli);
            tedadKhadamat = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_tedadKhadamat);
            tedadPaygiri = itemView.findViewById(R.id.TV_item_Khadamat_poshtibani_main_ntedadPaygiri);
        }
    }


    public interface ClickItemEvent{
        void onclickRecyclerItem(int userId);
    }


}
