package com.dayrayaneh.automation.adapter.pishkhan.UndoneHokmKar;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dayrayaneh.automation.R;
import com.dayrayaneh.automation.model.pishkhan.UnDoneHokmKar.DataItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UnDoneHokmKarAdapter extends RecyclerView.Adapter<UnDoneHokmKarAdapter.UndoneHokmkarViewHolder> {

    private Context context;
    private List<DataItem> itemList = new ArrayList<>();
    private List<Double> hokmKarNumberList = new ArrayList<>();
    private List<Double> sameList = new ArrayList<>();
    double j = 1;
    double i;

    public UnDoneHokmKarAdapter(Context context, List<DataItem> itemList) {
        this.context = context;
        this.itemList = itemList;
        for (DataItem d :
                itemList) {
            hokmKarNumberList.add(d.getHokmNumber());
        }
        j = itemList.get(0).getHokmNumber();
    }

    @NonNull
    @Override
    public UndoneHokmkarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UndoneHokmkarViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pishkhan_undonehokm_kar_main, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UndoneHokmkarViewHolder holder, int position) {


//        for (int i = 0; i < hokmKarNumberList.size(); i++) {
//            for (int j = i+1; j < hokmKarNumberList.size(); j++) {
//                if (hokmKarNumberList.get(i) == hokmKarNumberList.get(j)){
//                    sameList.add(hokmKarNumberList.get(i));
//                }
//            }
//
//        }
        i =itemList.get(position).getHokmNumber();

        if (i != j){
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            holder.linearLayout.setBackgroundColor(color);
            j = i;
        }

        holder.khadamatType.setText(itemList.get(position).getKhadamatType());
        holder.sabtKonande.setText(itemList.get(position).getSabtKonande());
        holder.erjaShode.setText(itemList.get(position).getPersonel());
        holder.serial.setText(itemList.get(position).getSerial());
        holder.moshtari.setText(itemList.get(position).getMoshtari());
        holder.hokmNum.setText(String.valueOf(itemList.get(position).getHokmNumber()));
        holder.request.setText(String.valueOf(itemList.get(position).getRequests()));

        boolean[] show = new boolean[itemList.size()];
        for (int i = 0; i < itemList.size(); i++) {
            show[i] = false;
        }

        holder.imageView.setOnClickListener(view -> {
            if (show[position]) {
                holder.frameLayout.setVisibility(View.GONE);
                holder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_drop));
                show[position] = false;
            } else {
                holder.frameLayout.setVisibility(View.VISIBLE);
                holder.imageView.setImageDrawable(context.getDrawable(R.drawable.ic_top_arrow));
                show[position] = true;
            }
        });


//        if (position>0 && position==position-1){
//
//        }else {
//
//        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class UndoneHokmkarViewHolder extends RecyclerView.ViewHolder {

        TextView hokmNum, sabtKonande, erjaShode, moshtari, serial, khadamatType, request;
        LinearLayout linearLayout;
        FrameLayout frameLayout;
        ImageView imageView;


        public UndoneHokmkarViewHolder(@NonNull View itemView) {
            super(itemView);
            hokmNum = itemView.findViewById(R.id.TV_item_pishkhan_undoneHokmKar_kar_shomareundoneHokmKar);
            sabtKonande = itemView.findViewById(R.id.TV_item_pishkhan_undoneHokmKar_kar_sabtKonande);
            erjaShode = itemView.findViewById(R.id.TV_item_pishkhan_undoneHokmKar_kar_erjashodeBe);
            moshtari = itemView.findViewById(R.id.TV_item_pishkhan_undoneHokmKar_kar_moshtari);
            serial = itemView.findViewById(R.id.TV_item_pishkhan_undoneHokmKar_kar_serial);
            khadamatType = itemView.findViewById(R.id.TV_item_pishkhan_undoneHokmKar_kar_noeKhadamat);
            request = itemView.findViewById(R.id.TV_item_pishkhan_undoneHokmkar_request);
            imageView = itemView.findViewById(R.id.IV_item_pishkhan_UndoneHokmKar_show_request);
            frameLayout = itemView.findViewById(R.id.frame_more_undone_hokmkar);
            linearLayout = itemView.findViewById(R.id.lin_item_undoneHokmKarKar_main);
        }
    }
}
