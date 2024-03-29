package com.dayrayaneh.automation.adapter.pishkhan.hokmKar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.HokmKar.DataItem;
import com.dayrayaneh.automation.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class HokmKarMainAdapter extends RecyclerView.Adapter<HokmKarMainAdapter.HokmKarMainVieHolder> {


    private Context context;
    private List<DataItem> dataItems = new ArrayList<>();
    public HokmKarMainEvent event;


    public HokmKarMainAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = dataItems;
    }

    @NonNull
    @Override
    public HokmKarMainVieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pishkhan_hokm_kar_main , parent , false);
        return new HokmKarMainVieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HokmKarMainVieHolder holder, int position) {

        if (position%2==0){
            holder.lin_hokmKar.setBackgroundColor(context.getResources().getColor(R.color.lightYellow));
        }else {
            holder.lin_hokmKar.setBackgroundColor(context.getResources().getColor(R.color.gray));

        }

        holder.noeKhadamat.setText(dataItems.get(position).getKind());
        holder.moshtari.setText(dataItems.get(position).getCustomer());
        holder.sabtKonande.setText(dataItems.get(position).getName());
        holder.serial.setText(dataItems.get(position).getSerial());
        holder.shomareHokm.setText(String.valueOf(dataItems.get(position).getNumber()));


        holder.itemView.setOnClickListener(v -> {
            event.getHokmKarMainId(dataItems.get(position).getId() , dataItems.get(position).getCustomer() , dataItems.get(position).getName());
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class HokmKarMainVieHolder extends RecyclerView.ViewHolder{


        TextView shomareHokm , sabtKonande , moshtari , serial , noeKhadamat;
        LinearLayout lin_hokmKar;

        public HokmKarMainVieHolder(@NonNull View itemView) {
            super(itemView);
            shomareHokm = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_shomareHokm);
            sabtKonande = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_sabtKonande);
            moshtari = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_moshtari);
            serial = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_serial);
            noeKhadamat = itemView.findViewById(R.id.TV_item_pishkhan_hokm_kar_noeKhadamat);
            lin_hokmKar = itemView.findViewById(R.id.lin_item_hokmKar_main);
        }
    }

    public interface HokmKarMainEvent{
        void getHokmKarMainId(int userId, String moshtari , String sabtKonande);
    }

}
